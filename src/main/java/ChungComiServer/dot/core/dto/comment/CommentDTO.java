package ChungComiServer.dot.core.dto.comment;

import ChungComiServer.dot.api.dto.MemberDTO;
import ChungComiServer.dot.core.entity.Comment;
import ChungComiServer.dot.core.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private MemberDTO member;
    private String content;
    private Long likes;
    private List<CommentDTO> childrenComments;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.member = new MemberDTO(comment.getMember());
        this.content = comment.getContent();
        this.likes = comment.getLikes();
        List<CommentDTO> children = comment.getChild().stream().map(child -> new CommentDTO(child.getId(), child.getContent(), child.getLikes(), child.getMember())).toList();
        childrenComments = children;
    }

    public CommentDTO(Long id, String content, Long likes, Member member) {
        this.id = id;
        this.member = new MemberDTO(member);
        this.content = content;
        this.likes = likes;
    }
}
