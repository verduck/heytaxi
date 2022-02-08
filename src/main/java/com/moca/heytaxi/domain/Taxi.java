package com.moca.heytaxi.domain;

import javax.persistence.*;

@Entity
public class Taxi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private String carNumber;

    @OneToOne(mappedBy = "taxi")
    private GetIn getIn;

    public Taxi() {
        this(null, null, null);
    }

    public Taxi(User user, String name, String carNumber) {
        this(-1L, user, name, carNumber);
    }

    public Taxi(Long id, User user, String name, String carNumber) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.carNumber = carNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return String.format("Taxi[id=%d, driver=%s, name=%s, carNumber=%s]", id, user.toString(), name, carNumber);
    }
}
