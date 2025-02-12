package ChungComiServer.dot.api.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;

@Service
public class CafeteriaMealScraper {

    private final String URL = "https://www.cbnucoop.com/service/restaurant/";

    public Map<String, Object> getCafeteriaMenu(String cafeteria) throws IOException {
            Document doc = Jsoup.connect(URL)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                    .get();

            Map<String, Map<String,Object>> cafeteriaMeals = new HashMap<>();
            cafeteriaMeals.put("hanbit", Map.of(
                    "아침", extractCafeteriaMeal(doc, "18-9-17-"),
                    "점심", extractCafeteriaMeal(doc, "18-8-16-"),
                    "석식", extractCafeteriaMeal(doc, "18-10-18-")
            ));
            cafeteriaMeals.put("byulbit", Map.of(
                    "점심", extractCafeteriaMeal(doc, "19-7-14-")
            ));
            cafeteriaMeals.put("enhasu", Map.of(
                    "점심", extractCafeteriaMeal(doc, "20-6-12-"),
                    "석식", extractCafeteriaMeal(doc, "20-13-25-")
            ));
            return cafeteriaMeals.get(cafeteria);
    }

    private List<Map<Integer, String>> extractCafeteriaMeal(Document doc, String idFront) {
        List<Map<Integer, String>> meals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Elements elements = doc.select("div[data-table=" + idFront + i + "]");
            for (Element element : elements) {
                meals.add(Map.of(i, element.text().trim()));
            }
        }
        return meals;
    }
}
