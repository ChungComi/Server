package ChungComiServer.dot.core.dto.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginResponseDTO {

    private final Long memberId;
    private final String loginPw;
}
