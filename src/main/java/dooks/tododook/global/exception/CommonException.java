package dooks.tododook.global.exception;

import dooks.tododook.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public abstract class CommonException extends RuntimeException {

    private final ErrorCode errorCode;

    protected CommonException(final ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
