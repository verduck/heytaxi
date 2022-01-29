package com.moca.heytaxi.dto;

public class MatchingDTO {
    private CallDTO user;
    private WaitingDTO taxi;

    public MatchingDTO() {}

    public MatchingDTO(CallDTO user, WaitingDTO taxi) {
        this.user = user;
        this.taxi = taxi;
    }

    public CallDTO getUser() {
        return user;
    }

    public void setUser(CallDTO user) {
        this.user = user;
    }

    public WaitingDTO getTaxi() {
        return taxi;
    }

    public void setTaxi(WaitingDTO taxi) {
        this.taxi = taxi;
    }

    public static class Response {
        private boolean success;
        private String message;
        private MatchingDTO matching;

        public Response() {}

        public Response(boolean success, String message, MatchingDTO matching) {
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

        public MatchingDTO getMatching() {
            return matching;
        }

        public void setMatching(MatchingDTO matching) {
            this.matching = matching;
        }
    }
}
