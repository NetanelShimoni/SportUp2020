package com.example.sportup;

import java.net.URL;
import java.security.PrivateKey;

public class PDF {

    private  String Name;
    private String url;

    public PDF(String name, String url) {
        Name = name;
        this.url = url;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
