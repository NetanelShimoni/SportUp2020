package com.example.sportup;

import java.io.Serializable;

/**
 * this class is a object of User in the app.
 */
public class User  implements Serializable {
    String id_system,name, phone,city,high,password,weight;

    public String getId_system() {
        return id_system;
    }

    public void setId_system(String id_system) {
        this.id_system = id_system;
    }

    public User(String name, String id_system, String phone, String city, String high, String password, String weight) {
        this.name = name;
        this.id_system = id_system;
        this.phone = phone;
        this.city = city;
        this.high = high;
        this.password = password;
        this.weight = weight;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
