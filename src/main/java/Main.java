
import com.google.gson.Gson;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        List<Purchase> basket = new ArrayList<>();


        // Стартуем сервер один(!) раз
        try (ServerSocket serverSocket = new ServerSocket(8989);) {
            // В цикле(!) принимаем подключения
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    // Принимаем от клиента строку
                    String getAnswer = in.readLine();
                    // Преобразуем строку формата json в Объект покупки
                    Gson gson = new Gson();
                    Purchase purchase = gson.fromJson(getAnswer, Purchase.class);
                    //Добавляем объект покупки в корзину
                    basket.add(purchase);
                    //Получаем
                    String str = CountingMaxCategories.getMaxCategory(basket);
                    out.println(str);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}