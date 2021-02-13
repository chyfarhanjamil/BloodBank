package com.example.bloodbank.society;

public class VoluntarySociety {
    private String societyName, societyArea, societyPhone;

    public VoluntarySociety(){

    }

    public VoluntarySociety(String societyName, String societyArea, String societyPhone) {
        this.societyName = societyName;
        this.societyArea = societyArea;
        this.societyPhone = societyPhone;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getSocietyArea() {
        return societyArea;
    }

    public void setSocietyArea(String societyArea) {
        this.societyArea = societyArea;
    }

    public String getSocietyPhone() {
        return societyPhone;
    }

    public void setSocietyPhone(String societyPhone) {
        this.societyPhone = societyPhone;
    }
}
