import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;

public class Client {
    public static void main(String[] args) throws IOException, ParseException {
        String host = "127.0.0.1";
        int port = 8989;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            // Объекты покупок
            //Purchase purchase = new Purchase("булка", "2022.07.18", 200);
            //Purchase purchase = new Purchase("мыло", "2022.12.14", 100);
            Purchase purchase = new Purchase("сухарики", "2022.09.30", 150);


            // Перевод объекта в json-формат
            Gson gson = new Gson();
            String purchaseToJson = gson.toJson(purchase);

            // Отправка покупки в json-формате на сервер
            out.println(purchaseToJson);

            //Вывод в консоль результата
            String getMaxCategory = in.readLine();
            System.out.println("Максимальная по абсолютным тратам категория за весь период: " + getMaxCategory);

        }
    }
}