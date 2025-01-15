package ChungComiServer.dot.core.dto.post;

import ChungComiServer.dot.core.dto.comment.CommentDTO;
import ChungComiServer.dot.core.entity.Comment;
import ChungComiServer.dot.core.entity.Post;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponsePostDTO {

    private String title;
    private String content;
    private List<CommentDTO> comments = new ArrayList<>();
    private Long likes;
    private Long views;

    public ResponsePostDTO(Post post){
        this.title = post.getTitle();
        this.content = post.getContent();
        this.comments = post.getComments().stream().map(CommentDTO::new).toList();
        this.likes = post.getLikes();
        this.views = post.getViews();
    }

}
