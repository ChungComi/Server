package ChungComiServer.dot.core.dto.comment;

import ChungComiServer.dot.core.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String memberName;
    private String content;
    private Long likes;
    private List<CommentDTO> childrenComments;

    public CommentDTO(Comment comment){
        this.id = comment.getId();
        this.memberName = comment.getMember().getName();
        this.content = comment.getContent();
        this.likes = comment.getLikes();
        List<CommentDTO> children = comment.getChild().stream().map(child -> new CommentDTO(child.getId(),child.getContent(), child.getLikes())).toList();
        childrenComments = children;
    }

    public CommentDTO(Long id, String content, Long likes){
        this.id = id;
        this.content = content;
        this.likes = likes;
    }
}
