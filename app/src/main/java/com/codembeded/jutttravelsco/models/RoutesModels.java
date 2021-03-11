package com.codembeded.jutttravelsco.models;

public class RoutesModels {

    int id;
    String location_name, ac_rate, non_ac_rate, date;

    public RoutesModels(int id, String location_name, String ac_rate, String non_ac_rate, String date) {
        this.id = id;
        this.location_name = location_name;
        this.ac_rate = ac_rate;
        this.non_ac_rate = non_ac_rate;
        this.date = date;
    }

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

    public String getAc_rate() {
        return ac_rate;
    }

    public void setAc_rate(String ac_rate) {
        this.ac_rate = ac_rate;
    }

    public String getNon_ac_rate() {
        return non_ac_rate;
    }

    public void setNon_ac_rate(String non_ac_rate) {
        this.non_ac_rate = non_ac_rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
