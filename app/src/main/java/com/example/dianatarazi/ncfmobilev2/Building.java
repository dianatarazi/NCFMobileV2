package com.example.dianatarazi.ncfmobilev2;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.SerializedName;


@IgnoreExtraProperties
public class Building {

    private String function;

    private String id;

    private double latitude;

    private double longitude;

    private String name;

    private String prefix;

    public Building() {}

    public Building(String function, String id, double latitude, double longitude, String name, String prefix) {
        this.function = function;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.prefix = prefix;
    }

    public String getFunction() {
        return function;
    }

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}

