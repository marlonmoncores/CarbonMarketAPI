package com.hackthon.m100u.CarbonMarketAPI.model.entity;

import com.hackthon.m100u.CarbonMarketAPI.domain.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_system")
public class UserSystemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String cpf;
    private String email;
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public User toUser(){
        User user = new User();
        user.setId(getId());
        user.setPassword(getPassword());
        user.setCpf(getCpf());
        user.setEmail(getEmail());
        user.setCreatedAt(getCreatedAt());
        return user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
