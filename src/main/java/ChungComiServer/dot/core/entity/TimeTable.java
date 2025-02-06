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
    @Enumerated(value = EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "START_TIME")
    private LocalDateTime startTime;

    @Column(name = "END_TIME")
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // == 연관관계 편의 메서드 == //
    public void addMember(Member member){
        this.member = member;
        member.getTimeTables().add(this);
    }

    protected TimeTable(){}

    public TimeTable(String className,String professor, DayOfWeek dayOfWeek, LocalDateTime startTime, LocalDateTime endTime){
        this.className = className;
        this.professor = professor;
        this.dayOfWeek = dayOfWeek;
        this.startTime= startTime;
        this.endTime = endTime;
    }

    /**
     * 시간표 수정을 위한 메서드
     */
    public void modifyTimeTable(String className, String professor, DayOfWeek dayOfWeek, LocalDateTime startTime, LocalDateTime endTime) {
        if(className!=null)
            this.className = className;
        if(professor!=null)
            this.professor = professor;
        if(dayOfWeek!=null)
            this.dayOfWeek = dayOfWeek;
        if(startTime!=null)
            this.startTime = startTime;
        if(endTime!=null)
            this.endTime = endTime;
    }
}
