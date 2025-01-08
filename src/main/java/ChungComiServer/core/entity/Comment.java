package ChungComiServer.core.entity;

import ChungComiServer.core.entity.interest.Interest;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "COMMENT")
public class Comment {

    @Id @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "LIKE")
    private Integer like;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INTEREST_ID")
    private Interest company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INTEREST_ID")
    private Interest techStack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMENT_ID")
    private Comment parent;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> child;
}
