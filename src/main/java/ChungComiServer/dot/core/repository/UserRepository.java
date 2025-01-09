package ChungComiServer.dot.core.repository;

import ChungComiServer.dot.core.entity.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public Member findByLoginId(String loginID) {
        return em.createQuery("select m from Member m where loginId = :loginId", Member.class)
                .setParameter("loginId", loginID)
                .getSingleResult();
    }

}
