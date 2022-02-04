package com.moca.heytaxi.dto;

public class ReservationDTO {
    private CallDTO user;
    private EmptyDTO taxi;

    public ReservationDTO() {}

    public ReservationDTO(CallDTO user, EmptyDTO taxi) {
        this.user = user;
        this.taxi = taxi;
    }

    public CallDTO getUser() {
        return user;
    }

    public void setUser(CallDTO user) {
        this.user = user;
    }

    public EmptyDTO getTaxi() {
        return taxi;
    }

    public void setTaxi(EmptyDTO taxi) {
        this.taxi = taxi;
    }

    public static class Response {
        private boolean success;
        private String message;
        private ReservationDTO matching;

        public Response() {}

        public Response(boolean success, String message, ReservationDTO matching) {
            this.success = success;
            this.message = message;
            this.matching = matching;
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

        public ReservationDTO getMatching() {
            return matching;
        }

        public void setMatching(ReservationDTO matching) {
            this.matching = matching;
        }
    }
}
