import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountingMaxCategories {

    //Создать геттер получения объекта типа стрингов
    public static String getMaxCategory(List<Purchase> basket) {
        //Мапа Товар:Категория
        Map<String, String> categories = Categories.getCategories();
        //Мапа Категория:Сумма
        Map<String, Integer> sum = new HashMap<>();

        // Проходимся циклом по корзине товаров
        for (Purchase purchase : basket) {
            String category;
            String title = purchase.getTitle();

            // Если ключ (товар) имеет значение (категорию), то определяем категорию
            if (categories.containsKey(title)) {
                category = categories.get(title);
            } else {
                //Если по ключу (товару) нет категории - присваиваем категорию "Другое"
                category = "Другое";
            }

            // Если в списке покупок есть вносимая категория, то увеличиваем сумму
            if (sum.containsKey(category)) {
                int amount = sum.get(category);
                sum.replace(category, amount + purchase.getSum());
            } else {
                // Если в списке покупок нет вносимой категории -> добавляем категорию и сумму
                sum.put(category, purchase.getSum());
            }
        }

        //Находим категорию с максимальной суммой
        int maxSum = 0;
        String key = null;
        for (Map.Entry<String, Integer> entryMap : sum.entrySet()) {
            if (entryMap.getValue() > maxSum) {
                key = entryMap.getKey();
                maxSum = entryMap.getValue();
            }
        }

        JSONObject keyMaxSum = new JSONObject();
        keyMaxSum.put(key, maxSum);
        String MaxCategory = keyMaxSum.toJSONString();

        return MaxCategory;
    }
}