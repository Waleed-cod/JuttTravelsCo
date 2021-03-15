package com.codembeded.jutttravelsco.models;

public class MyTicketsRoutesTiming {

    int id;
    String departure_time, arrival_time, entry_date, status;

    public MyTicketsRoutesTiming(int id, String departure_time, String arrival_time, String entry_date, String status) {
        this.id = id;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.entry_date = entry_date;
        this.status = status;
    }

    public MyTicketsRoutesTiming(String departure_time, String arrival_time, String entry_date) {
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.entry_date = entry_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
