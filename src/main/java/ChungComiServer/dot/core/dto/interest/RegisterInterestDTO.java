package ChungComiServer.dot.core.dto.interest;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterInterestDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
}
