package com.lemon.entity;


public class UserEntity {

    private String user;
    private String pwd;
    private String name;
    private String email;
    private String address;
    private String gender;
    private String tele;

    public UserEntity() {
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public UserEntity(String user, String pwd, String name, String email, String address, String gender, String tele) {
        this.user = user;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.tele = tele;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
