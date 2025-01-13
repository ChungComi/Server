package ChungComiServer.dot.global.response;

import lombok.Getter;

@Getter
public class ErrorCode {
    private final Integer code;
    private final String message;

    // 기본 생성자
    public ErrorCode() {
        this(400, "error"); // 기본값 설정
    }

    public ErrorCode(String message) {
        this(400, message); // 기본값 설정
    }

    // 매개변수를 받는 생성자
    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
