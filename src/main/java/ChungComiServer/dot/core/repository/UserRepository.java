package ChungComiServer.dot.core.repository;

import ChungComiServer.dot.core.entity.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

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

}
