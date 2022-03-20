package com.moca.heytaxi.dto;

import java.time.LocalDateTime;

public class EmptyDTO {
    private TaxiDTO taxi;
    private LatLng location;

    public EmptyDTO() {}

    public EmptyDTO(TaxiDTO taxi, LatLng location) {
        this.taxi = taxi;
        this.location = location;
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

    public static class Request {
        private LatLng location;

        public Request() {}

        public Request(LatLng location, LocalDateTime timestamp) {
            this.location = location;
        }

        public LatLng getLocation() {
            return location;
        }

        public void setLocation(LatLng location) {
            this.location = location;
        }
    }

    public static class Response {
        private boolean success;
        private String message;
        private EmptyDTO waiting;

        public Response() {}

        public Response(boolean success, String message, EmptyDTO waiting) {
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

        public EmptyDTO getWaiting() {
            return waiting;
        }

        public void setWaiting(EmptyDTO waiting) {
            this.waiting = waiting;
        }
    }
}
