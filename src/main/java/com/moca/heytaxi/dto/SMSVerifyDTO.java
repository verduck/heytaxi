package com.moca.heytaxi.dto;

public class SMSVerifyDTO {
    private String phone;
    private String clientSecret;
    private String message;

    public SMSVerifyDTO(String phone, String clientSecret, String message) {
        this.phone = phone;
        this.clientSecret = clientSecret;
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
