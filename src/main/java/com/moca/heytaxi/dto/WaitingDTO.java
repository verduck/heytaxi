package com.moca.heytaxi.dto;

import java.time.LocalDateTime;

public class WaitingDTO {
    private TaxiDTO taxi;
    private LatLng location;
    private LocalDateTime timestamp;

    public WaitingDTO() {}

    public WaitingDTO(TaxiDTO taxi, LatLng location, LocalDateTime timestamp) {
        this.taxi = taxi;
        this.location = location;
        this.timestamp = timestamp;
    }

    public TaxiDTO getTaxi() {
        return taxi;
    }

    public void setTaxi(TaxiDTO taxi) {
        this.taxi = taxi;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static class Request {
        private LatLng location;
        private LocalDateTime timestamp;

        public Request() {}

        public Request(LatLng location, LocalDateTime timestamp) {
            this.location = location;
            this.timestamp = timestamp;
        }


        public LatLng getLocation() {
            return location;
        }

        public void setLocation(LatLng location) {
            this.location = location;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static class Response {
        private boolean success;
        private String message;
        private WaitingDTO waiting;

        public Response() {}

        public Response(boolean success, String message, WaitingDTO waiting) {
            this.success = success;
            this.message = message;
            this.waiting = waiting;
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

        public WaitingDTO getWaiting() {
            return waiting;
        }

        public void setWaiting(WaitingDTO waiting) {
            this.waiting = waiting;
        }
    }
}
