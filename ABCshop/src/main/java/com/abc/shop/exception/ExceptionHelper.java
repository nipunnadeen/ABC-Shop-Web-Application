package com.abc.shop.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.logging.Logger;

public class ExceptionHelper {
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(ExceptionHelper.class);

//    @ExceptionHandler(value = { InvalidInputException.class })
//    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
//        LOGGER.error("Invalid Input Exception: ",ex.getMessage());
//        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
//    }
//
    @ExceptionHandler(value = { HttpClientErrorException.Unauthorized.class })
    public ResponseEntity<Object> handleUnauthorizedException(HttpClientErrorException.Unauthorized ex) {
//        LOGGER.error("Unauthorized Exception: ",ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(value = { BusinessException.class })
//    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
//        LOGGER.error("Business Exception: ",ex.getMessage());
//        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleException(Exception ex) {
//        log.info("Exception: ",ex.getMessage());
        return new ResponseEntity<Object>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
