package ChungComiServer.dot.core.repository;

import ChungComiServer.dot.core.entity.TimeTable;
import ChungComiServer.dot.core.enums.DayOfWeek;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;

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

    public Long addClass(TimeTable timeTable) {
        em.persist(timeTable);
        return timeTable.getId();
    }

    public TimeTable findById(Long timeTableId) {
        return em.find(TimeTable.class, timeTableId);
    }

    public void delete(Long timeTableId) {
        em.createQuery("delete from TimeTable t where t.id =:timeTableId")
                .setParameter("timeTableId",timeTableId)
                .executeUpdate();
        em.clear();
    }

    public boolean findIfItsAlreadyRegistered(Long userId, DayOfWeek dayOfWeek, LocalDateTime startTime, LocalDateTime endTime, String className) {
        List<TimeTable> resultList = em.createQuery(
                        "SELECT t FROM TimeTable t " +
                                "left JOIN FETCH t.member " +
                                "WHERE t.member.id = :userId " +
                                "AND t.dayOfWeek = :dayOfWeek " +
                                "AND ( " +
                                "  (t.startTime < :endTime AND t.endTime > :startTime) " + // 기존 시간과 겹치는 경우
                                "  OR t.className = :className " +
                                ")"
                        , TimeTable.class)
                .setParameter("userId", userId)
                .setParameter("dayOfWeek", dayOfWeek)
                .setParameter("startTime", startTime)
                .setParameter("endTime", endTime)
                .setParameter("className", className)
                .getResultList();

        return !resultList.isEmpty();
    }

}
