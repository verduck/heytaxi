package com.moca.heytaxi.dto;

public class TokenDTO {
    private boolean result;
    private String message;
    private String token;

    public TokenDTO() {
        this(false, null, null);
    }

    public TokenDTO(boolean result, String message, String token) {
        this.result = result;
        this.message = message;
        this.token = token;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
