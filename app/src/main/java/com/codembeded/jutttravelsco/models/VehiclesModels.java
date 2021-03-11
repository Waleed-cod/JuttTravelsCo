package com.codembeded.jutttravelsco.models;

public class VehiclesModels {

    int id;
    String vehicle_name, vehicle_model, vehicle_ac, vehicle_number, vehicle_color, vehicle_type, route_id, driver_id, date;

    public VehiclesModels(int id, String vehicle_name, String vehicle_model, String vehicle_ac, String vehicle_number, String vehicle_color, String vehicle_type, String route_id, String driver_id, String date) {
        this.id = id;
        this.vehicle_name = vehicle_name;
        this.vehicle_model = vehicle_model;
        this.vehicle_ac = vehicle_ac;
        this.vehicle_number = vehicle_number;
        this.vehicle_color = vehicle_color;
        this.vehicle_type = vehicle_type;
        this.route_id = route_id;
        this.driver_id = driver_id;
        this.date = date;
    }

    public VehiclesModels(int id, String vehicle_name) {
        this.id = id;
        this.vehicle_name = vehicle_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_ac() {
        return vehicle_ac;
    }

    public void setVehicle_ac(String vehicle_ac) {
        this.vehicle_ac = vehicle_ac;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getVehicle_color() {
        return vehicle_color;
    }

    public void setVehicle_color(String vehicle_color) {
        this.vehicle_color = vehicle_color;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
