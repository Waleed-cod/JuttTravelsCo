package com.codembeded.jutttravelsco.models;

public class RoutesModels {

    int id;
    String location_name, time, non_ac_rate, date;

    public RoutesModels(String location_name, String date) {
        this.id = id;
        this.location_name = location_name;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
