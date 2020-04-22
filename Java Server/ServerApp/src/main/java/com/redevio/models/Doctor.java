package com.redevio.models;

/**
 * Created by Muhammed Topgul.
 * Date: 17.04.2020
 * Time: 00:25
 */
public class Doctor {

    private int id;
    private String name;
    private String password;

    public Doctor(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
