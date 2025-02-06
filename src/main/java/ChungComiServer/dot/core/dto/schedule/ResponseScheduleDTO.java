package ChungComiServer.dot.core.dto.schedule;

import ChungComiServer.dot.core.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseScheduleDTO {
    private String content;
    private LocalDateTime date;

    public ResponseScheduleDTO(Schedule schedule){
        this.content = schedule.getContent();
        this.date = schedule.getDate();
    }
}
