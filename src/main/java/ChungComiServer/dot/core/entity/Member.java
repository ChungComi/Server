package ChungComiServer.dot.core.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    List<Post> posts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    List<MemberCompany> memberCompanies = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    List<MemberTechStack> memberTechStacks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_ID")
    School school;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    List<TimeTable> timeTables = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL,orphanRemoval = true)
    List<Schedule> schedules = new ArrayList<>();

    //== 연관관계 편의 메서드 ==//
    public void adjustSchool(School school){
        this.school = school;
        school.getMembers().add(this);
    }

    //== 생성 메서드 ==//
    /** 기본 생성자 메서드 **/
    public Member() {}

    /** 회원 가입 생성자 메서드 **/
    @Builder
    public Member(String name, String loginId, String loginPw) throws InvalidPropertiesFormatException {
        /* 회원가입 시 사용할 생성자 메서드 */
        if(!validatePw(loginPw)) throw new InvalidPropertiesFormatException("유효하지 않은 비밀번호");
        this.name = name;
        this.loginId = loginId;
        this.loginPw = loginPw;
    }

    //== 비즈니스 로직 ==//
    /** 비밀번호 유효성 확인 **/
    public Boolean validatePw(String loginPw){
        /* 비밀번호가 7자리보다 작을 경우 체크 */
        return loginPw.length() >= 7;
    }

    public void changePwToEncrypt(String encryptedLoginPw) {
        this.loginPw = encryptedLoginPw;
    }
}
