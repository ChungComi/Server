package ChungComiServer.dot.core.repository;

import ChungComiServer.dot.core.dto.auth.LoginResponseDTO;
import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.entity.MemberCompany;
import ChungComiServer.dot.core.entity.MemberTechStack;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRepository {

    private final EntityManager em;

    /** 회원 ID로 DB에서 찾는 메서드 **/
    public LoginResponseDTO findByLoginId(String loginID) {
        return em.createQuery("select new ChungComiServer.dot.core.dto.auth.LoginResponseDTO(m.id,m.loginPw) from Member m where m.loginId = :loginId", LoginResponseDTO.class)
                .setParameter("loginId", loginID)
                .getSingleResult();
    }

    /** 회원 DB 저장 메서드 **/
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    /** @Param: x
     * @Return: 모든 회원 객체 **/
    public List<Member> findAll() {
        return em.createQuery("select m from Member m left join fetch m.school", Member.class)
                .getResultList();
    }

    /** @Param: 회원 ID
     * @Return: 회원 객체 **/
    public Member findById(Long memberId) {
        return em.createQuery("select m from Member m left join fetch m.school where m.id =:memberId",Member.class)
                .setParameter("memberId",memberId)
                .getSingleResult();
    }

    /** @Param: 회원 이름
     * @Return: 해당 이름을 가진 회원객체 list
     * */
    public List<Member> findByName(String memberName) {
        return em.createQuery("select m from Member m left join fetch m.school where m.name =: name",Member.class)
                .setParameter("name",memberName)
                .getResultList();
    }


    public List<MemberCompany> findMemberCompanyByMemberId(Long memberId) {
        return em.createQuery("select mc from MemberCompany mc left join mc.member where mc.member.id =: memberId", MemberCompany.class)
                .setParameter("memberId",memberId)
                .getResultList();
    }

    public List<MemberTechStack> findMemberTechStackByMemberId(Long memberId){
        return em.createQuery("select mt from MemberTechStack mt left join mt.member where mt.member.id =: memberId", MemberTechStack.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
