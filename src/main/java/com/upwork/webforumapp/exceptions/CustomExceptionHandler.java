package com.upwork.webforumapp.exceptions;

import com.upwork.webforumapp.dto.ErrorReply;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorReply reply = new ErrorReply(false, HttpStatus.BAD_REQUEST.toString(), "Failed", errors);
        return new ResponseEntity(reply, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthFailException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthFailException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorReply reply = new ErrorReply(false, HttpStatus.UNAUTHORIZED.toString(), "Authentication Failed", errors);
        return new ResponseEntity(reply, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFondException.class)
    public ResponseEntity<?> handleNotFoundException(NotFondException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorReply reply = new ErrorReply(false, HttpStatus.NOT_FOUND.toString(), ex.getMessage(), errors);
        return new ResponseEntity(reply, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorReply reply = new ErrorReply(false, HttpStatus.BAD_REQUEST.toString(), "Failed", errors);
        return new ResponseEntity(reply, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllException(Exception ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorReply reply = new ErrorReply(false, HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Failed", errors);
        return new ResponseEntity(reply, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<?> handleNullPointerException(NullPointerException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorReply reply = new ErrorReply(false, HttpStatus.BAD_REQUEST.toString(), "Failed", errors);
        return new ResponseEntity(reply, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleRunTimeException(RuntimeException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorReply reply = new ErrorReply(false, HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Failed", errors);
        return new ResponseEntity(reply, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
