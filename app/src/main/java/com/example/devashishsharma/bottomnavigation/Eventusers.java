package com.example.devashishsharma.bottomnavigation;

/**
 * Created by DEVASHISH SHARMA on 16-06-2018.
 */

class Eventusers {
    String id, email, fname, mobile, gender, city, uni, pass, con_pass;

    public Eventusers() {

    }

    public Eventusers(String id, String email, String fname, String mobile, String gender, String city, String uni, String pass, String con_pass) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.mobile = mobile;
        this.gender = gender;
        this.city = city;
        this.uni = uni;
        this.pass = pass;
        this.con_pass = con_pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCon_pass() {
        return con_pass;
    }

    public void setCon_pass(String con_pass) {
        this.con_pass = con_pass;
    }
}