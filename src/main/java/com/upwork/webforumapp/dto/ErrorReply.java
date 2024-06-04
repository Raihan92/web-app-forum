package com.upwork.webforumapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorReply<T> implements Serializable {

    private boolean success;

    private String status;

    private String message;

    private List<String> errors;

    public ErrorReply(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ErrorReply(boolean success, String status, String message) {
        this.success = success;
        this.status = status;
        this.message = message;
    }

    public ErrorReply(boolean success, String status, String message, List<String> errors) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
