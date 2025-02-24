package ChungComiServer.dot.core.entity;

import ChungComiServer.dot.core.entity.interest.Company;
import ChungComiServer.dot.core.entity.interest.TechStack;
import ChungComiServer.dot.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "COMMENT")
public class Comment extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "LIKES")
    private Long likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "INTEREST_ID")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TECHSTACK_ID", referencedColumnName = "INTEREST_ID")
    private TechStack techStack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Comment parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Comment> child = new ArrayList<>();


    public Comment(){}

    public void verifyAuthorization(Long userId) throws IllegalAccessException {
        if(!this.member.getId().equals(userId))
            throw new IllegalAccessException("댓글 수정, 삭제에 권한이 없습니다.");
    }

    // == 게시글에 새로운 댓글 추가할 때 사용할 메서드 == //
    public Comment(String content){
        this.likes=0L;
        this.content = content;
    }

    // == 연관관계 편의 메서드 == //
    public void addRelationship(Post post,Member member){
        this.post = post;
        post.getComments().add(this);
        this.member = member;
        member.getComments().add(this);
    }

    public void addMember(Member member){
        this.member = member;
        member.getComments().add(this);
    }

    public void addChildComment(Comment childComment){
        childComment.parent = this;
        this.child.add(childComment);
    }

    public void plusLike(){
        this.likes++;
    }

    public void modifyContent(String content) {
        this.content = content;
    }
}
