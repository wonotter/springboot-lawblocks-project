package com.authserver.lawblocks.common;

import com.authserver.lawblocks.common.exception.ErrorCode;
import org.springframework.core.NestedRuntimeException;

public abstract class LawblocksException extends NestedRuntimeException {

    private final ErrorCode errorCode;

    protected LawblocksException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    protected LawblocksException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    protected LawblocksException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
