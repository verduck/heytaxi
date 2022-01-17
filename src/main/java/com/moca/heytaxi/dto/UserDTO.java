package com.moca.heytaxi.dto;

public class UserDTO {
    private String name;
    private String username;

    public UserDTO() { }

    public UserDTO(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static final class Request {
        private Long id;
        private String name;
        private String username;

        public Request() {
            this(-1L, null, null);
        }

        public Request(Long id, String name, String username) {
            this.id = id;
            this.name = name;
            this.username = username;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static final class Response {
        private boolean success;
        private String message;
        private UserDTO user;

        public Response() {
            this(false, null, null);
        }

        public Response(boolean success, String message, UserDTO userDTO) {
            this.success = success;
            this.message = message;
            this.user = userDTO;
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

        public UserDTO getUser() {
            return user;
        }

        public void setUser(UserDTO user) {
            this.user = user;
        }
    }
}
