package ChungComiServer.dot.core.repository;

import ChungComiServer.dot.core.entity.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    /** 회원 ID로 DB에서 찾는 메서드 **/
    public Member findByLoginId(String loginID) {
        return em.createQuery("select m from Member m where loginId = :loginId", Member.class)
                .setParameter("loginId", loginID)
                .getSingleResult();
    }

    /** 회원 DB 저장 메서드 **/
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Member findById(String memberId) {
        return em.find(Member.class,memberId);
    }

    public List<Member> findByName(String memberName) {
        return em.createQuery("select m from Member m where m.name =: name",Member.class)
                .setParameter("name",memberName)
                .getResultList();
    }
}
