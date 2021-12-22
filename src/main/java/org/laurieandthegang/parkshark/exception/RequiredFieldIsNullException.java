package org.laurieandthegang.parkshark.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredFieldIsNullException extends RuntimeException {
    public RequiredFieldIsNullException(String field) {
        super("Required field " + field + " was null.");
    }
}
