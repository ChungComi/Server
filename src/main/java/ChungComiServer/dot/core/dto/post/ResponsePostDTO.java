package ChungComiServer.dot.core.dto.post;

import ChungComiServer.dot.core.dto.comment.CommentDTO;
import ChungComiServer.dot.core.entity.Comment;
import ChungComiServer.dot.core.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponsePostDTO {

    private String title;
    private String content;
    private String memberName;
    private LocalDateTime registeredAt;
    private List<CommentDTO> comments = new ArrayList<>();
    private Long likes;
    private Long views;

    public ResponsePostDTO(Post post){
        this.title = post.getTitle();
        this.content = post.getContent();
        this.memberName = post.getMember().getName();
        this.registeredAt = post.getRegisterDate();
        this.comments = post.getComments().stream().map(CommentDTO::new).toList();
        this.likes = post.getLikes();
        this.views = post.getViews();
    }

}
