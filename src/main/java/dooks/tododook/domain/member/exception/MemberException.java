package dooks.tododook.domain.member.exception;

import dooks.tododook.global.exception.CommonException;
import dooks.tododook.global.exception.ErrorCode;

public class MemberException extends CommonException {
    public MemberException(final ErrorCode errorCode){
        super(errorCode);
    }

    public static class MemberNotFoundException extends MemberException {
        public MemberNotFoundException() {
            super(ErrorCode.MEMBER_NOT_FOUND);
        }
    }
    public static class EmailAlreadyExistException extends MemberException {
        public EmailAlreadyExistException() {
            super(ErrorCode.EMAIL_ALREADY_EXIST);
        }
    }
    public static class EmailOrPasswordNotMatch extends MemberException {
        public EmailOrPasswordNotMatch() {
            super(ErrorCode.EMAIL_OR_PASSWORD_NOT_MATCH);
        }
    }
}
