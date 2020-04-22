package com.redevio.models;

import java.util.ArrayList;

/**
 * Created by Muhammed Topgul.
 * Date: 17.04.2020
 * Time: 20:08
 */
public class Patient {
    private String id;
    private String name;
    private String email;
    private String tel;
    private String age;
    private String address;
    private String hairCaliber;
    private String hairColour;
    private String gender;
    private String donor;
    private String norwoodScale;
    private String doctorID;

    public Patient(String name, String email, String tel, String age, String address, String hairCaliber, String hairColour, String gender, String donor, String norwoodScale, String doctorID) {
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.age = age;
        this.address = address;
        this.hairCaliber = hairCaliber;
        this.hairColour = hairColour;
        this.gender = gender;
        this.donor = donor;
        this.norwoodScale = norwoodScale;
        this.doctorID = doctorID;
    }

    public Patient(String id, String name, String email, String tel, String age, String address, String hairCaliber, String hairColour, String gender, String donor, String norwoodScale, String doctorID) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.age = age;
        this.address = address;
        this.hairCaliber = hairCaliber;
        this.hairColour = hairColour;
        this.gender = gender;
        this.donor = donor;
        this.norwoodScale = norwoodScale;
        this.doctorID = doctorID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHairCaliber() {
        return hairCaliber;
    }

    public void setHairCaliber(String hairCaliber) {
        this.hairCaliber = hairCaliber;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public String getNorwoodScale() {
        return norwoodScale;
    }

    public void setNorwoodScale(String norwoodScale) {
        this.norwoodScale = norwoodScale;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }
}
