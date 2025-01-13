package ChungComiServer.dot.global.response;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Success<T> implements Result {
    private T data;
}
