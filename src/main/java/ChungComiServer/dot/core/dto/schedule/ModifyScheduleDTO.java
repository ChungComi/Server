package ChungComiServer.dot.core.dto.schedule;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ModifyScheduleDTO {
    private String content;
    private LocalDateTime date;
}
