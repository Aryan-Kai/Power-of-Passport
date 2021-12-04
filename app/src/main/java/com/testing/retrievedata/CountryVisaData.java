package com.testing.retrievedata;

import com.google.firebase.database.Exclude;

public class CountryVisaData {
    public CountryVisaData() {
    }


    String name;
    String code;


    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    String paid;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
