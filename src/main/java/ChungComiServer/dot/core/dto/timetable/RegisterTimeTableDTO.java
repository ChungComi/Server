package ChungComiServer.dot.core.dto.timetable;

import ChungComiServer.dot.core.enums.DayOfWeek;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private DayOfWeek dayOfWeek;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
}
