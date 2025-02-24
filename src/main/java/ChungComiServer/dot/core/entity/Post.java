package ChungComiServer.dot.core.entity;

import ChungComiServer.dot.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

@Entity
@Getter
@Table(name = "POST")
public class Post extends BaseEntity {

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 5)
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "LIKES")
    private Long likes;

    @Column(name = "VIEWS")
    private Long views;

    /** 기본 생성자 **/
    public Post(){}

    /** 게시글 생성을 위한 생성자 **/
    @Builder
    public Post(String title, String content) throws InvalidPropertiesFormatException {
        validateTitle(title);
        validateContent(content);
        this.title = title;
        this.content = content;
        this.likes = 0L;
        this.registerDate = LocalDateTime.now();
    }

    /** 게시글 수정을 위한 메서드 **/
    public void modifyPost(Long userId, String title, String content, LocalDateTime modifiedAt) throws InvalidPropertiesFormatException, IllegalAccessException {
        if(!member.getId().equals(userId))
            throw new IllegalAccessException("게시물 작성자만 수정이 가능합니다.");
        validateTitle(title);
        validateContent(content);
        this.title = title;
        this.content = content;
        this.modifiedDate = modifiedAt;
    }

    //== 비즈니스 로직 ==//
    /** 제목 유효성 검사를 위한 메서드 **/
    private void validateTitle(String title) throws InvalidPropertiesFormatException {
        if(title == null || title.isBlank())
            throw new InvalidPropertiesFormatException("제목은 비어있을 수 없습니다.");
    }

    private void validateContent(String content) throws InvalidPropertiesFormatException {
        if(content == null || content.isBlank())
            throw new InvalidPropertiesFormatException("본문은 비어있을 수 없습니다.");
    }

    //== 연관관계 편의 메서드 ==//
    public void addMemberRelationship(Member member){
        this.member = member;
        member.getPosts().add(this);
    }

}
