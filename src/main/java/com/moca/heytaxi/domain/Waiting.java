package com.moca.heytaxi.domain;

import com.moca.heytaxi.dto.LatLng;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.time.LocalDateTime;

@RedisHash("waiting")
public class Waiting implements Comparable<Waiting> {
    @Id
    private Long id;
    private Taxi taxi;
    private LatLng location;
    private LocalDateTime timestamp;

    public Waiting() {}

    public Waiting(Taxi taxi, LatLng location, LocalDateTime timestamp) {
        this.taxi = taxi;
        this.location = location;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(Waiting o) {
        return timestamp.compareTo(o.timestamp);
    }
}
