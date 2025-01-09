package ChungComiServer.core.entity;

import ChungComiServer.core.entity.interest.Company;
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
    private Integer preference;

}
