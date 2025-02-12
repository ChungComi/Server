package ChungComiServer.dot.api.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;

@Service
public class DormitoryMealScraper {

    private final Map<String, String> dormUrls = Map.of(
            "bongwan", "https://dorm.chungbuk.ac.kr/home/sub.php?menukey=20041&type=1",
            "yangsungjae", "https://dorm.chungbuk.ac.kr/home/sub.php?menukey=20041&type=2",
            "yangjinjae", "https://dorm.chungbuk.ac.kr/home/sub.php?menukey=20041&type=3"
    );

    public Map<String, Object> getDormitoryMeal(String dormitory) throws IOException {
        if (!dormUrls.containsKey(dormitory)) {
            return Collections.singletonMap("error", "기숙사 정보를 찾을 수 없습니다.");
        }

            Document doc = Jsoup.connect(dormUrls.get(dormitory)).userAgent("Mozilla/5.0").get();
            List<String> dateList = extractDateList(doc);
            List<String> morningList = extractMealData(doc, "morning");
            List<String> lunchList = extractMealData(doc, "lunch");
            List<String> eveningList = extractMealData(doc, "evening");

            Map<String, Object> meals = new HashMap<>();
            for (int i = 0; i < dateList.size(); i++) {
                meals.put(dateList.get(i), Map.of(
                        "아침", morningList.get(i),
                        "점심", lunchList.get(i),
                        "저녁", eveningList.get(i)
                ));
            }
            return meals;
    }

    private List<String> extractMealData(Document doc, String mealType) {
        Elements elements = doc.select("td." + mealType);
        List<String> meals = new ArrayList<>();
        for (Element element : elements) {
            meals.add(element.text().trim());
        }
        return meals;
    }

    private List<String> extractDateList(Document doc) {
        Elements elements = doc.select("td.foodday");
        List<String> dates = new ArrayList<>();
        for (Element element : elements) {
            dates.add(element.text().split(" ")[1]);
        }
        return dates;
    }
}
