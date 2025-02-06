package ChungComiServer.dot.core.dto.comment;

import ChungComiServer.dot.core.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentDTO {
    private String content;
    private Long likes;
    private List<CommentDTO> childrenComments;

    public CommentDTO(Comment comment){
        this.content = comment.getContent();
        this.likes = comment.getLikes();
        List<CommentDTO> children = comment.getChild().stream().map(child -> new CommentDTO(child.getContent(), child.getLikes())).toList();
        childrenComments = children;
    }

    public CommentDTO(String content, Long likes){
        this.content = content;
        this.likes = likes;
    }
}
