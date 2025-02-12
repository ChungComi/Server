package ChungComiServer.dot.api.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;

@Service
public class DevEventScraper {

    private final String URL = "https://github.com/brave-people/Dev-Event/blob/master/README.md";

    public Map<String, Object> getDevEvents() throws IOException {
        Document doc = Jsoup.connect(URL).userAgent("Mozilla/5.0").get();

        List<Map<String, String>> events = new ArrayList<>();
        Elements liTags = doc.select("article.markdown-body.entry-content.container-lg li");

        for (Element li : liTags) {
            Element aTag = li.selectFirst("strong > a");
            if (aTag == null) continue;

            String title = aTag.text().trim();
            String link = aTag.absUrl("href");

            // 하위 <ul>에서 "분류", "주최", "접수" 정보 가져오기
            Element subUl = li.selectFirst("ul");
            String categoryStr = "";
            String hostStr = "";
            String receptionStr = "";

            if (subUl != null) {
                Elements subLis = subUl.select("li");
                for (Element subLi : subLis) {
                    String text = subLi.text().trim();

                    if (text.startsWith("분류:")) {
                        Elements codeTags = subLi.select("code");
                        List<String> categories = new ArrayList<>();
                        for (Element code : codeTags) {
                            categories.add(code.text().trim());
                        }
                        categoryStr = String.join(", ", categories);
                    } else if (text.startsWith("주최:")) {
                        hostStr = text.replace("주최:", "").trim();
                    } else if (text.startsWith("접수:")) {
                        receptionStr = text.replace("접수:", "").trim();
                    }
                }
            }

            // 이벤트 정보 저장
            Map<String, String> event = new HashMap<>();
            event.put("제목", title);
            event.put("링크", link);
            event.put("분류", categoryStr);
            event.put("주최", hostStr);
            event.put("접수", receptionStr);

            events.add(event);
        }

        return Map.of("events", events);
    }
}
