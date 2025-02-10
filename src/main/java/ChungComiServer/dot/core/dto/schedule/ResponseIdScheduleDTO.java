package ChungComiServer.dot.core.dto.schedule;

import ChungComiServer.dot.core.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseIdScheduleDTO {
    private Long id;
    private String content;
    private LocalDateTime date;

    public ResponseIdScheduleDTO(Schedule schedule){
        this.id = schedule.getId();
        this.content = schedule.getContent();
        this.date = schedule.getDate();
    }
}
