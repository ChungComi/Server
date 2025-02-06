package ChungComiServer.dot.core.dto.schedule;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegisterScheduleDTO {
    @NotEmpty
    private String content;
    @NotEmpty
    private LocalDateTime date;
}
