package dooks.tododook.global.exception;

import lombok.Getter;

@Getter
public abstract class CommonException extends RuntimeException {

    private final ErrorCode errorCode;

    protected CommonException(final ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
