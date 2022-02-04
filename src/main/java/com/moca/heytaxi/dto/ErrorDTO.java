package com.moca.heytaxi.dto;

public class ErrorDTO {
    private String message;

    public ErrorDTO() {}

    public ErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
