package ChungComiServer.dot.api.repository;

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

    public MemberCompany findMemberCompanyById(Long id){
        return em.find(MemberCompany.class, id);
    }

    public Long saveMemberInterest(MemberCompany memberCompany){
        em.persist(memberCompany);
        return memberCompany.getId();
    }

    public Long saveMemberInterest(MemberTechStack memberCompany){
        em.persist(memberCompany);
        return memberCompany.getId();
    }

    public void deleteMemberCompanyByName(Long userId, String companyName){
        em.createQuery("delete from MemberCompany mc where mc.member.id =:userId and mc.company.name=: companyName")
                .setParameter("userId",userId)
                .setParameter("companyName",companyName)
                .executeUpdate();
        em.clear();
    }

    public void deleteMemberTechStackByName(Long userId, String techStackName){
        em.createQuery("delete from MemberTechStack mt where mt.member.id =: userId and mt.techStack.name =: techStackName")
                .setParameter("userId",userId)
                .setParameter("techStackName", techStackName)
                .executeUpdate();
        em.clear();
    }
}
