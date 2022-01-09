package com.moca.heytaxi.dto;

public class SMSVerifyDTO {
    public static final class Request {
        private String phone;
        private String clientSecret;
        private String code;

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
        private boolean result;
        private String message;

        public Response() {
            this(false, "");
        }

        public Response(boolean result, String message) {
            this.result = result;
            this.message = message;
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
    }
}
