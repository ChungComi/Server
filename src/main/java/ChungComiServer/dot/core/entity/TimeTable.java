package ChungComiServer.dot.core.entity;

import ChungComiServer.dot.core.enums.DayOfWeek;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "TIMETABLE")
public class TimeTable {

    @Id @GeneratedValue
    @Column(name = "TIMETABLE_ID")
    private Long id;

    @Column(name = "CLASS_NAME")
    private String className;

    @Column(name = "PROFESSOR")
    private String professor;

    @Column(name = "DAY_OF_WEEK")
    private DayOfWeek dayOfWeek;

    @Column(name = "START_TIME")
    private LocalDateTime startTime;

    @Column(name = "END_TIME")
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
