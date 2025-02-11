package ChungComiServer.dot.api.repository;

import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.entity.MemberCompany;
import ChungComiServer.dot.core.entity.MemberTechStack;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberApiRepository {

    private final EntityManager em;

    public Member findMemberCompanies(Long memberId) {
        return em.createQuery("select m from Member m where m.id =:memberId",Member.class)
                .setParameter("memberId",memberId)
                .getSingleResult();
    }

    public Long saveMemberInterest(MemberCompany memberCompany){
        em.persist(memberCompany);
        return memberCompany.getId();
    }

    public Long saveMemberInterest(MemberTechStack memberCompany){
        em.persist(memberCompany);
        return memberCompany.getId();
    }
}
