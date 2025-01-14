package ChungComiServer.dot.core.dto;


import lombok.Getter;

@Getter
public class PostDTO {
    private String title;
    private String content;
    private Long likes;
    private Long views;

    public PostDTO(){}

    public PostDTO(String title, String content, Long likes, Long views){
        this.title = title;
        this.content = content;
        this.likes = likes;
        this.views = views;
    }
}
