package com.codembeded.jutttravelsco.models;

public class RoutesModels {

    int id;
    String location_name;

    public RoutesModels(int id, String location_name) {
        this.id = id;
        this.location_name = location_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }
}
