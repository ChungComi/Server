package ChungComiServer.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class Response {
    private Boolean success;
    private Integer size;
    private Result result;
}
