package ChungComiServer.dot.core.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDTO {
    public LoginDTO(){}
    @NotEmpty(message = "id must be filled")
    private String loginId;
    @NotEmpty(message = "pw must be filled")
    private String loginPw;
}
