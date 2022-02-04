package com.moca.heytaxi.dto;

public class CallDTO {
    private UserDTO user;
    private LatLng src;
    private LatLng dest;

    public CallDTO() {}

    public CallDTO(UserDTO user, LatLng src, LatLng dest) {
        this.user = user;
        this.src = src;
        this.dest = dest;
    }

    public LatLng getSrc() {
        return src;
    }

    public void setSrc(LatLng src) {
        this.src = src;
    }

    public LatLng getDest() {
        return dest;
    }

    public void setDest(LatLng dest) {
        this.dest = dest;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public static class Request {
        
    }

    public static class Response {

    }
}
