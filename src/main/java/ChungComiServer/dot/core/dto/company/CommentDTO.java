package ChungComiServer.dot.core.dto.company;

import ChungComiServer.dot.core.entity.Comment;
import lombok.Getter;

@Getter
public class CommentDTO {
    private String content;
    private Long likes;

    public CommentDTO(Comment comment){
        this.content = comment.getContent();
        this.likes = comment.getLikes();
    }
}
