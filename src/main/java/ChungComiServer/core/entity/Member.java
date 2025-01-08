package ChungComiServer.core.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "MEMBER")
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "MEMBER_NAME")
    String name;

    @Column(name = "LOGIN_ID")
    String loginId;

    @Column(name = "LOGIN_PW")
    String loginPw;

    @OneToMany(fetch = FetchType.LAZY)
    List<Post> posts;

    @OneToMany(fetch = FetchType.LAZY)
    List<MemberCompany> memberCompanies;

    @OneToMany(fetch = FetchType.LAZY)
    List<MemberTechStack> memberTechStacks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_ID")
    School school;
}
