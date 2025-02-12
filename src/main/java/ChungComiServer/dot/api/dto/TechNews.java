package ChungComiServer.dot.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class TechNews {
    private String title;
    private String link;
    private String source;
    private String date;

    public TechNews(String title, String link, String source, String date) {
        this.title = title;
        this.link = link;
        this.source = source;
        this.date = date;
    }

}

