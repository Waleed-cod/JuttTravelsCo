package com.codembeded.jutttravelsco.models;

public class ParcelModels {

    int id, parcel_weight, parcel_quantity, status, amount;
    String receiver_name, receiver_contact, parcel_date, description, route_name;

    public ParcelModels(int id, int parcel_weight, int parcel_quantity, int status, int amount,
                        String receiver_name, String receiver_contact, String parcel_date, String description, String route_name) {
        this.id = id;
        this.parcel_weight = parcel_weight;
        this.parcel_quantity = parcel_quantity;
        this.status = status;
        this.amount = amount;
        this.receiver_name = receiver_name;
        this.receiver_contact = receiver_contact;
        this.parcel_date = parcel_date;
        this.description = description;
        this.route_name = route_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParcel_weight() {
        return parcel_weight;
    }

    public void setParcel_weight(int parcel_weight) {
        this.parcel_weight = parcel_weight;
    }

    public int getParcel_quantity() {
        return parcel_quantity;
    }

    public void setParcel_quantity(int parcel_quantity) {
        this.parcel_quantity = parcel_quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_contact() {
        return receiver_contact;
    }

    public void setReceiver_contact(String receiver_contact) {
        this.receiver_contact = receiver_contact;
    }

    public String getParcel_date() {
        return parcel_date;
    }

    public void setParcel_date(String parcel_date) {
        this.parcel_date = parcel_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }
}
