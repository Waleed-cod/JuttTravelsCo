package com.codembeded.jutttravelsco.models;

public class GetTicketsModels {
    int booking_id, ladies_seats, status, total_seats, total_amount, route_id, ac_status;

    String date, booking_date , arrival_time,route_name, departure_time;

    public GetTicketsModels(int booking_id, int ladies_seats, int status, int total_seats, int total_amount,
                            int route_id, int ac_status, String date, String booking_date, String booking_time,
                            String route_name, String departure_time) {
        this.booking_id = booking_id;
        this.ladies_seats = ladies_seats;
        this.status = status;
        this.total_seats = total_seats;
        this.total_amount = total_amount;
        this.route_id = route_id;
        this.ac_status = ac_status;
        this.date = date;
        this.booking_date = booking_date;
        this.arrival_time= booking_time;
        this.route_name= route_name;
        this.departure_time= departure_time;
    }

    public GetTicketsModels(int ladies_seats, int total_seats, int total_amount, int route_id, String booking_date, String booking_time) {
        this.ladies_seats = ladies_seats;
        this.total_seats = total_seats;
        this.total_amount = total_amount;
        this.route_id = route_id;
        this.ac_status = ac_status;
        this.booking_date = booking_date;
        this.arrival_time = booking_time;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getLadies_seats() {
        return ladies_seats;
    }

    public void setLadies_seats(int ladies_seats) {
        this.ladies_seats = ladies_seats;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public int getAc_status() {
        return ac_status;
    }

    public void setAc_status(int ac_status) {
        this.ac_status = ac_status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }
}
