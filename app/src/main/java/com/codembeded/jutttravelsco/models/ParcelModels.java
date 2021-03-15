package com.codembeded.jutttravelsco.models;

public class ParcelModels {

    int id;
    String parcel_weight, parcel_quantity, receiver_name, receiver_contact, parcel_date, status, description, amount;

    public ParcelModels(int id, String parcel_weight, String parcel_quantity, String receiver_name, String receiver_contact, String parcel_date, String status, String description, String amount) {

        this.id = id;
        this.parcel_weight = parcel_weight;
        this.parcel_quantity = parcel_quantity;
        this.receiver_name = receiver_name;
        this.receiver_contact = receiver_contact;
        this.parcel_date = parcel_date;
        this.status = status;
        this.description = description;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParcel_weight() {
        return parcel_weight;
    }

    public void setParcel_weight(String parcel_weight) {
        this.parcel_weight = parcel_weight;
    }

    public String getParcel_quantity() {
        return parcel_quantity;
    }

    public void setParcel_quantity(String parcel_quantity) {
        this.parcel_quantity = parcel_quantity;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String discounted_amount) {
        this.receiver_name = discounted_amount;
    }

    public String getReceiver_contact() {
        return receiver_contact;
    }

    public void setReceiver_contact(String parcel_status) {
        this.receiver_contact = parcel_status;
    }

    public String getParcel_date() {
        return parcel_date;
    }

    public void setParcel_date(String parcel_date) {
        this.parcel_date = parcel_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
