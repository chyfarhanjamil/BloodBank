package com.example.bloodbank;

public class MakeRequest {
    private String recipientName, recipientArea, recipientBloodGrp, recipientPhone, recipientAmount,
            recipientDetails;


    public MakeRequest() {

    }

    public MakeRequest(String recipientName, String recipientArea, String recipientBloodGrp,
                        String recipientAmount, String recipientPhone, String recipientDetails) {
        this.recipientName = recipientName;
        this.recipientArea = recipientArea;
        this.recipientBloodGrp = recipientBloodGrp;
        this.recipientPhone = recipientPhone;
        this.recipientAmount = recipientAmount;
        this.recipientDetails = recipientDetails;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientArea() {
        return recipientArea;
    }

    public void setRecipientArea(String recipientArea) {
        this.recipientArea = recipientArea;
    }

    public String getRecipientBloodGrp() {
        return recipientBloodGrp;
    }

    public void setRecipientBloodGrp(String recipientBloodGrp) {
        this.recipientBloodGrp = recipientBloodGrp;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getRecipientAmount() {
        return recipientAmount;
    }

    public void setRecipientAmount(String recipientAmount) {
        this.recipientAmount = recipientAmount;
    }

    public String getRecipientDetails() {
        return recipientDetails;
    }

    public void setRecipientDetails(String recipientDetails) {
        this.recipientDetails = recipientDetails;
    }
}
