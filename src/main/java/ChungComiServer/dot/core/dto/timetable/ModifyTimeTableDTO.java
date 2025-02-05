package ChungComiServer.dot.core.dto.timetable;

import ChungComiServer.dot.core.enums.DayOfWeek;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ModifyTimeTableDTO {
    private String className;
    private String professor;
    private DayOfWeek dayOfWeek;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
