package ChungComiServer.dot.global.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Success<T> implements Result {
    private T data;
}
