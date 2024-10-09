package org.examplefghjf.serviceapi.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicateDataException extends DataIntegrityViolationException {
    public DuplicateDataException(String message) {
        super(message);
    }

    public DuplicateDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
