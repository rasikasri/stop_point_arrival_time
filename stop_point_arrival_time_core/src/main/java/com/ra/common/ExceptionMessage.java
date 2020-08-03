package com.ra.common;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    APPLICATION_EXCEPTION("Internal Application Error"),
    INVALID_PARAMETER("Invalid Request Parameters");

    private String message;


    ExceptionMessage(String message) {
        this.message = message;
    }
}
