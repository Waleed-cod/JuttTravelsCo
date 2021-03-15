package com.codembeded.jutttravelsco.models;

public class MyBookingsModels {
    int id;
    String image, departure, arrival, departure_date, arrival_date, time, rate_parcel_vehicle, desc, ac_status;

    public MyBookingsModels(int id, String image, String departure, String arrival, String departure_date,
                            String arrival_date, String time, String rate_parcel_vehicle, String desc, String ac_status) {
        this.id = id;
        this.image = image;
        this.departure = departure;
        this.arrival = arrival;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.time = time;
        this.rate_parcel_vehicle = rate_parcel_vehicle;
        this.desc = desc;
        this.ac_status = ac_status;
    }

    public MyBookingsModels(String departure, String arrival, String departure_date,
                            String time, String rate_parcel_vehicle, String ac_status) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.departure_date = departure_date;
        this.time = time;
        this.rate_parcel_vehicle = rate_parcel_vehicle;
        this.ac_status = ac_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
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

    public String getRate_parcel_vehicle() {
        return rate_parcel_vehicle;
    }

    public void setRate_parcel_vehicle(String rate_parcel_vehicle) {
        this.rate_parcel_vehicle = rate_parcel_vehicle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAc_status() {
        return ac_status;
    }

    public void setAc_status(String ac_status) {
        this.ac_status = ac_status;
    }
}
