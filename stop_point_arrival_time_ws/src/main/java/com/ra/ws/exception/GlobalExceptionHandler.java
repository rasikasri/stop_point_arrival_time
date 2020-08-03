package com.ra.ws.exception;

import com.ra.common.ApplicationException;
import com.ra.common.ExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    @ResponseBody
    private ResponseEntity handleApplicationException(ApplicationException exception) {
        log.error(exception.getMessage(), exception);
        return  new ResponseEntity(ExceptionMessage.APPLICATION_EXCEPTION.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseBody
    private ResponseEntity handleConversionFailedException(ConversionFailedException exception) {
        log.error(exception.getMessage(), exception);
        return  new ResponseEntity(ExceptionMessage.INVALID_PARAMETER.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
