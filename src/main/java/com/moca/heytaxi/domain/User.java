package com.moca.heytaxi.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String taxiCertification;
    @OneToOne(mappedBy = "user")
    private Taxi taxi;
    @OneToOne(mappedBy = "user")
    private GetIn GetIn;
    @OneToOne(mappedBy = "user")
    private GetOut getOut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getTaxiCertification() {
        return taxiCertification;
    }

    public void setTaxiCertification(String taxiCertification) {
        this.taxiCertification = taxiCertification;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("User(id=%d, name=%s, username=%s, password=%s, taxiCertification=%s)", id, name, username, password, taxiCertification);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
