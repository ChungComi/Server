package ChungComiServer.dot.api.repository;

import ChungComiServer.dot.core.entity.MemberCompany;
import ChungComiServer.dot.core.entity.MemberTechStack;
import ChungComiServer.dot.core.entity.interest.TechStack;
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

    public void deleteMemberCompany(MemberCompany memberCompany){
        em.remove(memberCompany);
    }

    public void deleteMemberTechStackByName(MemberTechStack techStack){
        em.remove(techStack);
    }

    public MemberCompany findMemberCompanyByCompanyName(Long userId, String companyName) {
        return em.createQuery("select mc from MemberCompany mc where mc.company.name =: companyName and mc.member.id =: userId",MemberCompany.class)
                .setParameter("companyName",companyName)
                .setParameter("userId",userId)
                .getSingleResult();
    }

    public MemberTechStack findMemberTechStackByName(Long userId, String techStackName) {
        return em.createQuery("select mt from MemberTechStack mt where mt.techStack.name =: techStackName and mt.member.id =: userId", MemberTechStack.class)
                .setParameter("techStackName",techStackName)
                .setParameter("userId",userId)
                .getSingleResult();
    }
}
