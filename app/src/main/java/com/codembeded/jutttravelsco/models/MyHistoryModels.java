package com.codembeded.jutttravelsco.models;

public class MyHistoryModels {
    String departure, arrival, date, time;

    public MyHistoryModels(String departure, String arrival, String date, String time) {
        this.departure = departure;
        this.arrival = arrival;
        this.date = date;
        this.time = time;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
