package com.authserver.lawblocks.common.exception;

import com.authserver.lawblocks.common.LawblocksException;

public class ValidException extends LawblocksException {
    public ValidException(String message) {
        super(ErrorCode.VALIDATION_FAIL, message);
    }
}
