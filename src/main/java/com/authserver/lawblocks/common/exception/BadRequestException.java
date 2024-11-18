package com.authserver.lawblocks.common.exception;

import com.authserver.lawblocks.common.LawblocksException;

public class BadRequestException extends LawblocksException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
