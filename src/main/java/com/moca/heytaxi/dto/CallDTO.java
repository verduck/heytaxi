package com.moca.heytaxi.dto;

public class CallDTO {
    private LatLng src;
    private LatLng dest;

    public CallDTO(LatLng src, LatLng dest) {
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
}
