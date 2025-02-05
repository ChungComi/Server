package ChungComiServer.dot.core.dto.timetable;

import ChungComiServer.dot.core.enums.DayOfWeek;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegisterTimeTableDTO {

    @NotEmpty
    private String className;
    @NotEmpty
    private String professor;
    @NotEmpty
    private DayOfWeek dayOfWeek;
    @NotEmpty
    private LocalDateTime startTime;
    @NotEmpty
    private LocalDateTime endTime;
}
