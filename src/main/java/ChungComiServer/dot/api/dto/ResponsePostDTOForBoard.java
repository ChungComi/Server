package ChungComiServer.dot.api.dto;

import ChungComiServer.dot.core.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponsePostDTOForBoard {

    private Long id;
    private String title;
    private String memberName;
    private Long likes;
    private Long views;
    private LocalDateTime registeredAt;

    public ResponsePostDTOForBoard(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.likes = post.getLikes();
        this.views = post.getViews();
        this.registeredAt = post.getRegisterDate();
        this.memberName = post.getMember().getName();
    }
}
