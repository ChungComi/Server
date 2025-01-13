package ChungComiServer.dot.core.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDTO {
    @NotEmpty(message = "id must be filled")
    private String loginID;
    @NotEmpty(message = "pw must be filled")
    private String loginPW;
}
