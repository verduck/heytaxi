package com.moca.heytaxi.dto;

public class CallDTO {
    private LatLng src;
    private LatLng dst;

    public CallDTO() {}

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getName());
        stringBuilder.append('@');
        stringBuilder.append(hashCode());
        stringBuilder.append("(");
        stringBuilder.append("src = " + src.toString());
        stringBuilder.append(", dst = " + dst.toString());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public CallDTO(LatLng src, LatLng dst) {
        this.src = src;
        this.dst = dst;
    }

    public LatLng getSrc() {
        return src;
    }

    public void setSrc(LatLng src) {
        this.src = src;
    }

    public LatLng getDst() {
        return dst;
    }

    public void setDst(LatLng dst) {
        this.dst = dst;
    }

    public static class Request {
        
    }

    public static class Response {

    }
}
