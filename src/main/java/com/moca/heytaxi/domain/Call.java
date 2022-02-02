package com.moca.heytaxi.domain;

import com.moca.heytaxi.dto.LatLng;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@RedisHash("Call")
public class Call {
    @Id
    private Long id;
    private LatLng src;
    private LatLng dst;

    public Call() {}

    public Call(LatLng src, LatLng dst) {
        this.src = src;
        this.dst = dst;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
