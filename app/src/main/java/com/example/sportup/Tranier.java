package com.example.sportup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tranier implements Serializable {
    private String name , city, phone_num,password,id_system;

    public Tranier() {
    }

    public Tranier(String name,String id_system ,String city, String phone_num, String password) {
        this.name = name;
        this.city = city;
        this.id_system=id_system;
        this.phone_num = phone_num;
        this.password = password;
    }

    public String getId_system() {
        return id_system;
    }

    public void setId_system(String id_system) {
        this.id_system = id_system;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
