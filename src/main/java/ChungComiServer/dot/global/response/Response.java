package ChungComiServer.dot.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response {
    private Boolean success;
    private Integer size;
    private Result result;
}
