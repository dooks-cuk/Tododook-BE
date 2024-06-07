package dooks.tododook.domain.todo.exception;

import dooks.tododook.domain.member.exception.MemberException;
import dooks.tododook.global.exception.CommonException;
import dooks.tododook.global.exception.ErrorCode;

public class TodoException extends CommonException {
    public TodoException(final ErrorCode errorCode){
        super(errorCode);
    }

    public static class TodoNotFoundException extends TodoException {
        public TodoNotFoundException() {
            super(ErrorCode.TODO_NOT_FOUND);
        }
    }
}
