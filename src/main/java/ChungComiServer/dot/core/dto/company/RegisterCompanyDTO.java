package ChungComiServer.dot.core.dto.company;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCompanyDTO {
    @NotEmpty
    private String name;
}
