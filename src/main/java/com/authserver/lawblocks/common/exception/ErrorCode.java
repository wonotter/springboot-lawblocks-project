package com.authserver.lawblocks.common.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_ID_DUPLICATED(BAD_REQUEST, "E001", "중복된 아이디입니다."),
    NICKNAME_DUPLICATED(BAD_REQUEST, "E002", "중복된 닉네임입니다."),
    USER_NOT_FOUND(BAD_REQUEST, "E003", "사용자를 찾을 수 없습니다."),
    PASSWORD_NOT_MATCH(BAD_REQUEST, "E004", "비밀번호가 일치하지 않습니다."),
    NOT_EXISTED_POST(BAD_REQUEST, "E005", "게시물이 존재하지 않습니다"),
    NOT_EXISTED_USER(BAD_REQUEST, "E006", "사용자가 존재하지 않습니다."),
    NO_PERMISSION(BAD_REQUEST, "E007", "게시물을 삭제할 권한이 없습니다."),
    VALIDATION_FAIL(BAD_REQUEST, "E008", "검증에 실패하였습니다."),
    EMAIL_DUPLICATED(BAD_REQUEST, "E009", "중복된 이메일입니다."),
    CERTIFICATION_FAIL(BAD_REQUEST, "E010", "이메일 인증에 실패하였습니다."),
    CERTIFICATION_MISSMATCHING(BAD_REQUEST, "E011", "인증코드가 일치하지 않습니다."),
    NOT_EXISTED_CATEGORY(BAD_REQUEST, "E012", "카테고리가 존재하지 않습니다.")
    ;
    private HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
