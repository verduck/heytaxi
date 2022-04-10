package com.moca.heytaxi.domain;

public class Reservation {
    private Long id;
    private User user;
    private Empty taxi;
    private Call call;

    public Reservation() {}

    public Reservation(User user, Empty taxi, Call call) {
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

    public Empty getTaxi() {
        return taxi;
    }

    public void setTaxi(Empty taxi) {
        this.taxi = taxi;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }
}
