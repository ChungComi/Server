package ChungComiServer.dot.core.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "POST")
public class Post {

    @Id @GeneratedValue
    @Column(name = "POST_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "LIKES")
    private Long likes;

    @Column(name = "VIEWS")
    private Long views;

    /** 기본 생성자 **/
    public Post(){}

    /** 게시글 생성을 위한 생성자 **/
    @Builder
    public Post(String title, String content){
        this.title = title;
        this.content = content;
    }

    /** 게시글 수정을 위한 메서드 **/
    public void modifyPost(String title, String content){
        this.title = title;
        this.content = content;
    }
}
