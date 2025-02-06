package ChungComiServer.dot.core.entity;

import ChungComiServer.dot.core.enums.DayOfWeek;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.InvalidPropertiesFormatException;

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
    public void modifyTimeTable(String className, String professor, DayOfWeek dayOfWeek, LocalDateTime startTime, LocalDateTime endTime) throws InvalidPropertiesFormatException {
        validateClassName(className);
        validateProfessor(professor);
        validateDayOfWeek(dayOfWeek);
        validateTime(startTime,endTime);
        this.className = className;
        this.professor = professor;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //==비즈니스 로직 ==//
    /** 수업 이름 유효성 검사를 위한 메서드 **/
    private void validateClassName(String className) throws InvalidPropertiesFormatException {
        if (className == null || className.isBlank())
                throw new InvalidPropertiesFormatException("수업 이름은 비어있을 수 없습니다.");
    }

    /** 교수님 여부에 대한 유효성 검사를 위한 메서드 **/
    private void validateProfessor(String professor) throws InvalidPropertiesFormatException {
        if (professor == null || professor.isBlank())
            throw new InvalidPropertiesFormatException("교수님 이름은 비어있을 수 없습니다.");
    }

    /** 수업 날짜에 대한 유효성 검사를 위한 메서드 **/
    private void validateDayOfWeek(DayOfWeek dayOfWeek) throws InvalidPropertiesFormatException {
        if (dayOfWeek == null)
            throw new InvalidPropertiesFormatException("날짜는 비어있을 수 없습니다.");
    }

    /** 수업 시간에 대한 유효성 검사를 위한 메서드 **/
    private void validateTime(LocalDateTime startTime, LocalDateTime endTime) throws InvalidPropertiesFormatException {
        if (startTime == null || endTime == null)
            throw new InvalidPropertiesFormatException("시간은 비어있을 수 없습니다.");
    }

}
