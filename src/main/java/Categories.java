import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Categories {
    //Геттер для возвращения набора Товар:категория из файла categories.tsv
    public static Map<String, String> getCategories() {

        return categories;
    }

    //Мапа категорий
    static Map<String, String> categories;

    static {
        //Наполнить мапу данными из файла (загрузить категории)
        try {
            categories = loadCategories();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Метод заполнения мапы
    static Map<String, String> loadCategories() throws IOException {
        Map<String, String> forCategories = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("categories.tsv"))) {

            while (true) {
                String str = br.readLine();
                if (str == null) {
                    break;
                }
                String[] data = str.split("\t");
                forCategories.put(data[0], data[1]);
            }
        }
        return forCategories;
    }
}
