package com.codembeded.jutttravelsco.models;

public class TourModels {
    int id;
    String image, departure,departure_date, arrival_date, time, rate_per_person, desc, ac_status;

    public TourModels(int id, String image, String departure, String departure_date,
                      String arrival_date, String time, String rate_per_person, String desc , String ac_status) {
        this.id = id;
        this.image = image;
        this.departure = departure;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.time = time;
        this.rate_per_person = rate_per_person;
        this.desc = desc;
        this.ac_status = ac_status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRate_per_person() {
        return rate_per_person;
    }

    public void setRate_per_person(String rate_per_person) {
        this.rate_per_person = rate_per_person;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAc_status() {
        return ac_status;
    }

    public void setAc_status(String ac_status) {
        this.ac_status = ac_status;
    }
}
