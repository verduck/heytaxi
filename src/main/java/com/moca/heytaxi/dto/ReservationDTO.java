package com.moca.heytaxi.dto;

public class ReservationDTO {
    private UserDTO user;
    private EmptyDTO taxi;
    private CallDTO call;

    public ReservationDTO() {}

    public ReservationDTO(UserDTO user, EmptyDTO taxi, CallDTO call) {
        this.user = user;
        this.taxi = taxi;
        this.call = call;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public EmptyDTO getTaxi() {
        return taxi;
    }

    public void setTaxi(EmptyDTO taxi) {
        this.taxi = taxi;
    }

    public CallDTO getCall() {
        return call;
    }

    public void setCall(CallDTO call) {
        this.call = call;
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
