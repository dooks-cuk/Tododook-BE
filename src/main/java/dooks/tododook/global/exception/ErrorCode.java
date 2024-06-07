package dooks.tododook.global.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "400", "값이 올바르지 않습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "405", "지원하지 않는 Http Method 입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "서버 에러"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "400", "입력 값의 타입이 올바르지 않습니다."),
    UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED, "401", "잘못된 접근입니다."),
    NOT_EXIST_TOKEN_INFO(HttpStatus.FORBIDDEN, "403", "토큰 정보가 존재하지 않습니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "403", "접근이 거부 되었습니다."),
    FEIGN_CLIENT_ERROR(HttpStatus.BAD_REQUEST, "400", "정보를 가져올 수 없습니다."),

    // Authorization
    SOCIAL_ACCESS_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "401", "유효하지 않은 소셜 로그인 토큰입니다."),

    // Member
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "404", "존재하지 않는 사용자입니다."),
    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "400", "이미 존재하는 이메일 입니다."),
    EMAIL_OR_PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "400", "이메일 혹은 비밀번호가 일치하지 않습니다."),

    // Todolist
    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "404", "존재하지 않는 투두리스트입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;


    public int getStatus() {
        return status.value();
    }

    ErrorCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}