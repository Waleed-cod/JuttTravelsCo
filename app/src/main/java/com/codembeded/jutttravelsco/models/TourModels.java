package com.codembeded.jutttravelsco.models;

public class TourModels {
    int id,rate_per_person, tour_id, tour_status, tour_booking_id, total_amount, discount,
            total_seats;
    String image, routes,departure_date, arrival_date, time, desc;

    public TourModels(int id, String image, String routes, String departure_date,
                      String arrival_date, String time, int rate_per_person, String desc) {
        this.id = id;
        this.image = image;
        this.routes = routes;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.time = time;
        this.rate_per_person = rate_per_person;
        this.desc = desc;
    }

    // get Tour Booking constructor
    public TourModels(String image, String routes, String departure_date, String arrival_date,
                      String time, String desc, int tour_id,
                      int tour_status, int tour_booking_id, int total_amount, int discount, int total_seats) {

        this.image = image;
        this.routes = routes;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.time = time;
        this.desc = desc;
        this.tour_id = tour_id;
        this.tour_status = tour_status;
        this.tour_booking_id = tour_booking_id;
        this.total_amount = total_amount;
        this.discount = discount;
        this.total_seats = total_seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate_per_person() {
        return rate_per_person;
    }

    public void setRate_per_person(int rate_per_person) {
        this.rate_per_person = rate_per_person;
    }

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public int getTour_status() {
        return tour_status;
    }

    public void setTour_status(int tour_status) {
        this.tour_status = tour_status;
    }

    public int getTour_booking_id() {
        return tour_booking_id;
    }

    public void setTour_booking_id(int tour_booking_id) {
        this.tour_booking_id = tour_booking_id;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRoutes() {
        return routes;
    }

    public void setRoutes(String routes) {
        this.routes = routes;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
