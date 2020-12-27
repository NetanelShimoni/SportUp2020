package com.example.sportup;

public class Message {
    private String message,id_trainer,id_user;
    private boolean is_read;

    public Message(String message, String id_trainer, String id_user,  boolean is_read) {
        this.message = message;
        this.id_trainer = id_trainer;
        this.id_user = id_user;
        this.is_read = is_read;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId_trainer() {
        return id_trainer;
    }

    public void setId_trainer(String id_trainer) {
        this.id_trainer = id_trainer;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }
}
