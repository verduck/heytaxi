package com.moca.heytaxi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxiDTO {
    private Long id;
    @JsonProperty("driver")
    private UserDTO user;
    private String name;
    private String carNumber;

    public TaxiDTO() {}

    public TaxiDTO(Long id, UserDTO user, String name, String carNumber) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.carNumber = carNumber;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO driver) {
        this.user = driver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static final class Request {
        private String name;
        private String carNumber;

        public Request() {
            this(null, null);
        }

        public Request(String name, String carNumber) {
            this.name = name;
            this.carNumber = carNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }
    }

    public static final class Response {
        private boolean success;
        private String message;
        private TaxiDTO taxi;

        public Response() {
            this(false, null, null);
        }

        public Response(boolean success, String message, TaxiDTO taxiDTO) {
            this.success = success;
            this.message = message;
            this.taxi = taxiDTO;
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

        public TaxiDTO getTaxi() {
            return taxi;
        }

        public void setTaxi(TaxiDTO taxi) {
            this.taxi = taxi;
        }
    }
}
