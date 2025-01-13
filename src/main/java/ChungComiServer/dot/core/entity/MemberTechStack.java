package ChungComiServer.dot.core.entity;

import ChungComiServer.dot.core.entity.interest.TechStack;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "MEMBERTECHSTACK")
public class MemberTechStack {

    @Id @GeneratedValue
    @Column(name = "MEMBERTECHSTACK_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TECHSTACK_ID")
    private TechStack techStack;

    @Column(name = "PREFERENCE")
    private Long preference;
}
