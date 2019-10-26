package com.hackthon.m100u.CarbonMarketAPI.api.to;

import com.hackthon.m100u.CarbonMarketAPI.domain.User;

public class UserInputTO {

    private long id;
    private String cpf;
    private String email;
    private String password;

    public UserInputTO(){

    }

    public UserInputTO(User user){
        this.id = user.getId();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
    }

    public User toUser(){
        User user = new User();
        user.setPassword(getPassword());
        user.setCpf(getCpf());
        user.setEmail(getEmail());
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
}
