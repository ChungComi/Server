package ChungComiServer.dot.core.repository;

import ChungComiServer.dot.core.entity.Schedule;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ScheduleRepository {

    private final EntityManager em;

    public List<LocalDateTime> getAllSchedulesOfTheMonth(Long userId, int year, int month) {
        return em.createQuery("select s.date from Schedule s join s.member where function('MONTH',s.date) =:month and function('YEAR',s.date) =:year and s.member.id =:userId", LocalDateTime.class)
                .setParameter("year",year)
                .setParameter("month", month)
                .setParameter("userId",userId)
                .getResultList();
    }

    public Long registerSchedule(Schedule schedule) {
        em.persist(schedule);
        return schedule.getId();
    }

    public Schedule findById(Long scheduleId) {
        return em.find(Schedule.class,scheduleId);
    }

    public void deleteSchedule(Schedule schedule) {
        em.remove(schedule);
    }

    public List<Schedule> findByDate(Long userId, LocalDateTime date) {
        return em.createQuery("select s from Schedule s where s.date =:date and member.id =:userId",Schedule.class)
                .setParameter("date",date)
                .setParameter("userId",userId)
                .getResultList();
    }
}
