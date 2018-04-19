package com.techbytecare.kk.healthcareproject.Model;

/**
 * Created by kundan on 2/25/2018.
 */

public class UserDoctor {
    private String phone;
    private String hospitalName;
    private String email;
    private String address;
    private String password;

    public UserDoctor() {
    }

    public UserDoctor(String phone, String hospitalName, String email, String address, String password) {
        this.phone = phone;
        this.hospitalName = hospitalName;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
