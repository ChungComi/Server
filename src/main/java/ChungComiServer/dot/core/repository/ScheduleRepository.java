package ChungComiServer.dot.core.repository;

import ChungComiServer.dot.core.entity.Schedule;
import ChungComiServer.dot.core.enums.Month;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ScheduleRepository {

    private final EntityManager em;

    public List<Schedule> getAllSchedulesOfTheMonth(Long userId, int month) {
        return em.createQuery("select s from Schedule s where function('MONTH',s.date) =:month and s.member.id =:userId",Schedule.class)
                .setParameter("month", month)
                .setParameter("userId",userId)
                .getResultList();
    }

    public Long registerSchedule(Schedule schedule) {
        em.persist(schedule);
        return schedule.getId();
    }
}
