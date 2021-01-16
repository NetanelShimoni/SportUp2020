package com.example.sportup;

/**
 * this class is a object of excersise in this app
 */
public class Exersice {
    String name_muselce,name, trainer_name, trainer_phone, description,wight,hight,link;

    public Exersice(String muselce,String name, String trainer_name, String trainer_phone, String description, String wight, String hight, String link) {
        this.name = name;
        this.trainer_name = trainer_name;
        this.trainer_phone = trainer_phone;
        this.description = description;
        this.wight = wight;
        this.hight = hight;
        this.link = link;
        this.name_muselce=muselce;
    }

    public String getName_muselce() {
        return name_muselce;
    }

    public void setName_muselce(String name_muselce) {
        this.name_muselce = name_muselce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public String getTrainer_phone() {
        return trainer_phone;
    }

    public void setTrainer_phone(String trainer_phone) {
        this.trainer_phone = trainer_phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWight() {
        return wight;
    }

    public void setWight(String wight) {
        this.wight = wight;
    }

    public String getHight() {
        return hight;
    }

    public void setHight(String hight) {
        this.hight = hight;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
