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

    // == 연관관계 편의 메서드 ==//
    public MemberTechStack adjustInterestTechStackMember(Member member, TechStack techStack){
        this.member = member;
        this.techStack = techStack;
        member.getMemberTechStacks().add(this);
        techStack.getMemberTechStacks().add(this);
        return this;
    }
}
