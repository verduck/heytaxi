package com.moca.heytaxi.dto;

import java.time.LocalDateTime;

public class GetOutDTO {
    private Long id;
    private TaxiDTO taxi;
    private UserDTO user;
    private double longitude;
    private double latitude;
    private int fee;
    private LocalDateTime timestamp;

    public GetOutDTO() {}

    public GetOutDTO(Long id, TaxiDTO taxi, UserDTO user, double longitude, double latitude, int fee, LocalDateTime timestamp) {
        this.id = id;
        this.taxi = taxi;
        this.user = user;
        this.longitude = longitude;
        this.latitude = latitude;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaxiDTO getTaxi() {
        return taxi;
    }

    public void setTaxi(TaxiDTO taxi) {
        this.taxi = taxi;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static class Response {
        private boolean success;
        private String message;
        private GetOutDTO getOut;

        public Response() {}

        public Response(boolean success, String message, GetOutDTO getOut) {
            this.success = success;
            this.message = message;
            this.getOut = getOut;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public GetOutDTO getGetOut() {
            return getOut;
        }

        public void setGetOut(GetOutDTO getOut) {
            this.getOut = getOut;
        }
    }
}
