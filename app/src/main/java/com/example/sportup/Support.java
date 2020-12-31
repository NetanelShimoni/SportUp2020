package com.example.sportup;

import java.io.Serializable;

public class Support implements Serializable {

    private String name;
    private String phone;
    private String system_id;
    private boolean is_takeCare;

    public Support(String name,String id ,String phone) {
        this.name = name;
        this.system_id=id;
        this.phone = phone;
        this.is_takeCare = false;
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

    public String getSystem_id() {
        return system_id;
    }

    public void setSystem_id(String system_id) {
        this.system_id = system_id;
    }

    public boolean isIs_takeCare() {
        return is_takeCare;
    }

    public void setIs_takeCare(boolean is_takeCare) {
        this.is_takeCare = is_takeCare;
    }
}
