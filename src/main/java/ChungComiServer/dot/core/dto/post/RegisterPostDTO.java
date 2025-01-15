package ChungComiServer.dot.core.dto.post;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterPostDTO {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
}
