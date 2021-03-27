package com.codembeded.jutttravelsco.models;

public class MyBookingsModels {
    int special_booking_id,vehicle_id, amount, discounted_amount,special_booking_status,extra_mileage,vehicle_ac_status;
    String special_booking_date,booking_date,booking_time,description,departure,arrival,vehicle_name,vehicle_number;

    public MyBookingsModels(int special_booking_id, int vehicle_id, int amount, int discounted_amount, int special_booking_status,
                            int extra_mileage, int vehicle_ac_status, String special_booking_date, String booking_date,
                            String booking_time, String description, String departure, String arrival, String vehicle_name, String vehicle_number) {
        this.special_booking_id = special_booking_id;
        this.vehicle_id = vehicle_id;
        this.amount = amount;
        this.discounted_amount = discounted_amount;
        this.special_booking_status = special_booking_status;
        this.extra_mileage = extra_mileage;
        this.vehicle_ac_status = vehicle_ac_status;
        this.special_booking_date = special_booking_date;
        this.booking_date = booking_date;
        this.booking_time = booking_time;
        this.description = description;
        this.departure = departure;
        this.arrival = arrival;
        this.vehicle_name = vehicle_name;
        this.vehicle_number = vehicle_number;
    }

    public int getSpecial_booking_id() {
        return special_booking_id;
    }

    public void setSpecial_booking_id(int special_booking_id) {
        this.special_booking_id = special_booking_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDiscounted_amount() {
        return discounted_amount;
    }

    public void setDiscounted_amount(int discounted_amount) {
        this.discounted_amount = discounted_amount;
    }

    public int getSpecial_booking_status() {
        return special_booking_status;
    }

    public void setSpecial_booking_status(int special_booking_status) {
        this.special_booking_status = special_booking_status;
    }

    public int getExtra_mileage() {
        return extra_mileage;
    }

    public void setExtra_mileage(int extra_mileage) {
        this.extra_mileage = extra_mileage;
    }

    public int getVehicle_ac_status() {
        return vehicle_ac_status;
    }

    public void setVehicle_ac_status(int vehicle_ac_status) {
        this.vehicle_ac_status = vehicle_ac_status;
    }

    public String getSpecial_booking_date() {
        return special_booking_date;
    }

    public void setSpecial_booking_date(String special_booking_date) {
        this.special_booking_date = special_booking_date;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }
}
