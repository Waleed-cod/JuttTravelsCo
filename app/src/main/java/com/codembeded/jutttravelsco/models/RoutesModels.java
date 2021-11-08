package com.codembeded.jutttravelsco.models;

import java.util.ArrayList;
import java.util.List;

public class RoutesModels {

    int id, ac_rate, non_ac_rate;
    String location_name, date, vehicle_name, vehicle_id, vehicle_model, vehicle_number,
            vehicle_ac, vehicle_color;
    ArrayList<MyTicketsRoutesTiming> route_list;

    public RoutesModels(int id, int ac_rate, int non_ac_rate, String location_name, String date, ArrayList<MyTicketsRoutesTiming> route_list) {
        this.id = id;
        this.ac_rate = ac_rate;
        this.non_ac_rate = non_ac_rate;
        this.location_name = location_name;
        this.date = date;
        this.route_list = route_list;
    }

    public ArrayList<MyTicketsRoutesTiming> getRoute_list() {
        return route_list;
    }

    public void setRoute_list(ArrayList<MyTicketsRoutesTiming> route_list) {
        this.route_list = route_list;
    }

    public RoutesModels(int id, String location_name, int ac_rate, int non_ac_rate, String date, String vehicle_name,
                        String vehicle_id, String vehicle_model, String vehicle_number, String vehicle_ac, String vehicle_color) {
        this.id = id;
        this.location_name = location_name;
        this.ac_rate = ac_rate;
        this.non_ac_rate = non_ac_rate;
        this.date = date;
        this.vehicle_name = vehicle_name;
        this.vehicle_id = vehicle_id;
        this.vehicle_model = vehicle_model;
        this.vehicle_number = vehicle_number;
        this.vehicle_ac = vehicle_ac;
        this.vehicle_color = vehicle_color;
    }

    public RoutesModels(int id, String location_name, int ac_rate, int non_ac_rate, String date) {
        this.id = id;
        this.location_name = location_name;
        this.ac_rate = ac_rate;
        this.non_ac_rate = non_ac_rate;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public int getAc_rate() {
        return ac_rate;
    }

    public void setAc_rate(int ac_rate) {
        this.ac_rate = ac_rate;
    }

    public int getNon_ac_rate() {
        return non_ac_rate;
    }

    public void setNon_ac_rate(int non_ac_rate) {
        this.non_ac_rate = non_ac_rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getVehicle_ac() {
        return vehicle_ac;
    }

    public void setVehicle_ac(String vehicle_ac) {
        this.vehicle_ac = vehicle_ac;
    }

    public String getVehicle_color() {
        return vehicle_color;
    }

    public void setVehicle_color(String vehicle_color) {
        this.vehicle_color = vehicle_color;
    }
}
