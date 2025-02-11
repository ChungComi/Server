package ChungComiServer.dot.core.entity;

import ChungComiServer.dot.core.entity.interest.Company;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "MEMBERCOMPANY")
public class MemberCompany {

    @Id @GeneratedValue
    @Column(name = "MEMBERCOMPANY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INTEREST_ID")
    private Company company;

    @Column(name = "PREFERENCE")
    private Long preference;

    // == 연관관계 편의 메서드 ==//
    public MemberCompany adjustInterestCompanyInMember(Member member, Company company){
        this.member = member;
        this.company = company;
        member.getMemberCompanies().add(this);
        company.getMemberCompanies().add(this);
        return this;
    }

}
