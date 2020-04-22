package com.redevio.models;

/**
 * Created by Muhammed Topgul.
 * Date: 17.04.2020
 * Time: 20:10
 */
public class PatientPhoto {

    private String id;
    private String photoName;

    public PatientPhoto(String id, String photoName) {
        this.id = id;
        this.photoName = photoName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}
