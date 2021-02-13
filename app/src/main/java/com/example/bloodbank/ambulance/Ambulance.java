package com.example.bloodbank.ambulance;

public class Ambulance {
    private String ambulanceName, ambulanceArea, ambulancePhone;

    public Ambulance(){

    }

    public Ambulance(String ambulanceName, String ambulanceArea, String ambulancePhone) {
        this.ambulanceName = ambulanceName;
        this.ambulanceArea = ambulanceArea;
        this.ambulancePhone = ambulancePhone;
    }

    public String getAmbulanceName() {
        return ambulanceName;
    }

    public void setAmbulanceName(String ambulanceName) {
        this.ambulanceName = ambulanceName;
    }

    public String getAmbulanceArea() {
        return ambulanceArea;
    }

    public void setAmbulanceArea(String ambulanceArea) {
        this.ambulanceArea = ambulanceArea;
    }

    public String getAmbulancePhone() {
        return ambulancePhone;
    }

    public void setAmbulancePhone(String ambulancePhone) {
        this.ambulancePhone = ambulancePhone;
    }
}
