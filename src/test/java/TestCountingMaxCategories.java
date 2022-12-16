import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestCountingMaxCategories {

    Purchase purchase1 = new Purchase("булка", "2022.07.18", 200);
    Purchase purchase2 = new Purchase("колбаса", "2021.01.01", 300);
    Purchase purchase3 = new Purchase("сухарики", "2022.12.31", 50);
    Purchase purchase4 = new Purchase("курица", "2022.02.14", 350);
    Purchase purchase5 = new Purchase("тапки", "2022.08.24", 1000);
    Purchase purchase6 = new Purchase("шапка", "2020.10.17", 2000);
    Purchase purchase7 = new Purchase("мыло", "2019.03.19", 50);

    List<Purchase> basket = new ArrayList<>();

    {
        basket.add(purchase1);
        basket.add(purchase2);
        basket.add(purchase3);
        basket.add(purchase4);
        basket.add(purchase5);
        basket.add(purchase6);
        basket.add(purchase7);
    }

    @Test
    public void testCountingMaxCategories() throws ParseException, JsonProcessingException {
        String str = CountingMaxCategories.getMaxCategory(basket);

        JSONObject json = new JSONObject();
        json.put("одежда", 3000);

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(str), mapper.readTree(String.valueOf(json)));
    }

    @Test
    public void testOtherCategory() throws ParseException, JsonProcessingException {
        Purchase other = new Purchase("Лыжи", "2022.12.15", 20_000);
        basket.add(other);
        String str = CountingMaxCategories.getMaxCategory(basket);
        System.out.println(str);

        JSONObject json = new JSONObject();
        json.put("Другое", 20_000);

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(str), mapper.readTree(String.valueOf(json)));

    }
}
