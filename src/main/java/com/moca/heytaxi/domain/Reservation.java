package com.moca.heytaxi.domain;

public class Reservation {
    private Long id;
    private User user;
    private Taxi taxi;
    private Call call;

    public Reservation() {}

    public Reservation(User user, Taxi taxi, Call call) {
        this.user = user;
        this.taxi = taxi;
        this.call = call;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }
}
