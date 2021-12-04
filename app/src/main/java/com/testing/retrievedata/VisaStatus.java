package com.testing.retrievedata;

public class VisaStatus {
    private String name;
    private boolean paid;

    public VisaStatus() {
    }

    public VisaStatus(String name, boolean paid) {
        this.name = name;
        this.paid = paid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
