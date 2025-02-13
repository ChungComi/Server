package ChungComiServer.dot.core.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ModifyPostDTO {
    private String title;
    private String content;
    private LocalDateTime modifiedAt;
}
