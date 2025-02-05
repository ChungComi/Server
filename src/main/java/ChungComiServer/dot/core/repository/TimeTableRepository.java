package ChungComiServer.dot.core.repository;

import ChungComiServer.dot.core.entity.TimeTable;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TimeTableRepository {

    private final EntityManager em;

    public List<TimeTable> findMyAllTimeTables(Long userId) {
        return em.createQuery("select t from TimeTable t join fetch t.member where t.member.id =:userId", TimeTable.class)
                .setParameter("userId",userId)
                .getResultList();
    }
}
