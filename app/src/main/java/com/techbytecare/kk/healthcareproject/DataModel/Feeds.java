package com.techbytecare.kk.healthcareproject.DataModel;

import java.util.ArrayList;

/**
 * Created by kundan on 3/1/2018.
 */

public class Feeds {

    private String entry_id;

    private String field2;

    private String created_at;

    private String field1;

    public String getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(String entry_id) {
        this.entry_id = entry_id;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    @Override
    public String toString() {
        return "Feeds{" +
                "entry_id='" + entry_id + '\'' +
                ", field2='" + field2 + '\'' +
                ", created_at='" + created_at + '\'' +
                ", field1='" + field1 + '\'' +
                '}';
    }
}
