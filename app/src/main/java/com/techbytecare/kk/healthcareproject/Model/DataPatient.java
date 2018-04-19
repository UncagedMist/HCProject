package com.techbytecare.kk.healthcareproject.Model;

/**
 * Created by kundan on 2/26/2018.
 */

public class DataPatient {

    private String field;
    private String value;
    private String dateTime;

    public DataPatient() {
    }

    public DataPatient(String field, String value, String dateTime) {
        this.field = field;
        this.value = value;
        this.dateTime = dateTime;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
