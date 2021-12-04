package com.testing.retrievedata;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;

import java.util.HashMap;
import java.util.List;

public class CountryVisaData {
    public CountryVisaData() {
    }

    public CountryVisaData(String name, String code, List<HashMap<String, Object>> visaFree, List<HashMap<String, Object>> visaOnArrival, List<HashMap<String, Object>> visaRequired) {
        this.name = name;
        this.code = code;
        this.visaFree = visaFree;
        this.visaOnArrival = visaOnArrival;
        this.visaRequired = visaRequired;
    }

    private String name;
    private String code;

    @PropertyName("visa_free")
    private List<HashMap<String, Object>> visaFree;

    @PropertyName("visa_on_arrival")
    private List<HashMap<String, Object>> visaOnArrival;


    @PropertyName("visa_required")
    private List<HashMap<String, Object>> visaRequired;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<HashMap<String, Object>> getVisaFree() {
        return visaFree;
    }

    public void setVisaFree(List<HashMap<String, Object>> visaFree) {
        this.visaFree = visaFree;
    }

    public List<HashMap<String, Object>> getVisaOnArrival() {
        return visaOnArrival;
    }

    public void setVisaOnArrival(List<HashMap<String, Object>> visaOnArrival) {
        this.visaOnArrival = visaOnArrival;
    }

    public List<HashMap<String, Object>> getVisaRequired() {
        return visaRequired;
    }

    public void setVisaRequired(List<HashMap<String, Object>> visaRequired) {
        this.visaRequired = visaRequired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
