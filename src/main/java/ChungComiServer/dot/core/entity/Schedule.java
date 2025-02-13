package ChungComiServer.dot.core.entity;

import ChungComiServer.dot.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.InvalidPropertiesFormatException;

@Entity
@Slf4j
@Getter
@Table(name = "SCHEDULE")
public class Schedule extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "SCHEDULE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "DATE")
    private LocalDateTime date;

    protected Schedule(){}

    public Schedule(String content, LocalDateTime date){
        this.content = content;
        this.date = date;
    }

    // == 연관관계 편의 메서드 == //
    public void addMember(Member member) {
        this.member = member;
        member.getSchedules().add(this);
    }

    /** 일정 수정을 위한 메서드 **/
    public void modifySchedule(Long userId, String content, LocalDateTime date) throws IllegalAccessException, InvalidPropertiesFormatException {
        if(!member.getId().equals(userId))
            throw new IllegalAccessException("일정 작성자만 일정 수정이 가능합니다.");
        validateContent(content);
        validateDate(date);
        this.content = content;
        this.date = date;
    }

    /** 일정 내용 유효성 검사 메서드 **/
    private void validateContent(String content) throws InvalidPropertiesFormatException {
        if(content==null || content.isBlank())
            throw new InvalidPropertiesFormatException("일정 내용은 비어있을 수 없습니다.");
    }

    /** 날짜 유효성 검사 메서드 **/
    private void validateDate(LocalDateTime date) throws InvalidPropertiesFormatException {
        if (date == null)
            throw new InvalidPropertiesFormatException("날짜는 비어있을 수 없습니다.");
    }
}
