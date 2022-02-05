package com.moca.heytaxi.domain;

import com.moca.heytaxi.dto.LatLng;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.time.LocalDateTime;

@RedisHash("call")
public class Call implements Comparable<Call> {
    @Id
    private Long id;
    private LatLng src;
    private LatLng dst;
    private LocalDateTime timestamp;

    public Call() {}

    public Call(LatLng src, LatLng dst, LocalDateTime timestamp) {
        this.src = src;
        this.dst = dst;
        this.timestamp = timestamp;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(Call o) {
        return timestamp.compareTo(o.timestamp);
    }
}
