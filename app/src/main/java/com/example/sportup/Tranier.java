package com.example.sportup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is a object of trainer in the app.
 */
public class Tranier implements Serializable {
    private String name , city, phone_num,password,id_system;
    private boolean is_verify;

    public Tranier() {
    }

    public Tranier(String name,String id_system ,String city, String phone_num, String password) {
        this.name = name;
        this.is_verify=false;
        this.city = city;
        this.id_system=id_system;
        this.phone_num = phone_num;
        this.password = password;
    }

    public boolean isIs_verify() {
        return is_verify;
    }

    public void setIs_verify(boolean is_verify) {
        this.is_verify = is_verify;
    }

    public String getId_system() {
        return id_system;
    }

    @Override
    public String toString() {
        return "Tranier{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", password='" + password + '\'' +
                ", id_system='" + id_system + '\'' +
                ", is_verify=" + is_verify +
                '}';
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
