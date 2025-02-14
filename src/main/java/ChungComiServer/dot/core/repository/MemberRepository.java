package ChungComiServer.dot.core.repository;

import ChungComiServer.dot.core.entity.Member;
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
    public Member findByLoginId(String loginID) {
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
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
        return em.find(Member.class,memberId);
    }

    /** @Param: 회원 이름
     * @Return: 해당 이름을 가진 회원객체 list
     * */
    public List<Member> findByName(String memberName) {
        return em.createQuery("select m from Member m left join fetch m.school where m.name =: name",Member.class)
                .setParameter("name",memberName)
                .getResultList();
    }


}
