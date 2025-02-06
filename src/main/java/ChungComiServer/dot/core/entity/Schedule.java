package ChungComiServer.dot.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Entity
@Slf4j
@Getter
@Table(name = "SCHEDULE")
public class Schedule {

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
}
