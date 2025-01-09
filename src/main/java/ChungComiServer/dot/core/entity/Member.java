package ChungComiServer.dot.core.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member" )
    List<Post> posts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    List<MemberCompany> memberCompanies = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    List<MemberTechStack> memberTechStacks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_ID")
    School school;
}
