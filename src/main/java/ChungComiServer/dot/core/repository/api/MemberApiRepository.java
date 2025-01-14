package ChungComiServer.dot.core.repository.api;

import ChungComiServer.dot.core.entity.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberApiRepository {

    private final EntityManager em;

    public Member findMemberCompanies(String memberId) {
        return em.createQuery("select m from Member m where m.id =:memberId",Member.class)
                .setParameter("memberId",memberId)
                .getSingleResult();
    }
}
