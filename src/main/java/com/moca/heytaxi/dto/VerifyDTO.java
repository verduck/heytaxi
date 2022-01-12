package com.moca.heytaxi.dto;

public class VerifyDTO {
    public static final class Request {
        private String phone;
        private String clientSecret;
        private String code;

        public Request() {}

        public Request(String phone, String clientSecret, String code) {
            this.phone = phone;
            this.clientSecret = clientSecret;
            this.code = code;
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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static final class Response {
        private boolean success;
        private String message;

        public Response() {
            this(false, null);
        }

        public Response(boolean success, String message) {
            this.success = success;
            this.message = message;
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
    }
}
