package com.techbytecare.kk.healthcareproject.Model;

/**
 * Created by kundan on 2/25/2018.
 */

public class UserPatient {
    private String phone;
    private String name;
    private String age;
    private String email;
    private String bloodGP;
    private String password;

    public UserPatient() {
    }

    public UserPatient(String phone, String name, String age, String email, String bloodGP, String password) {
        this.phone = phone;
        this.name = name;
        this.age = age;
        this.email = email;
        this.bloodGP = bloodGP;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodGP() {
        return bloodGP;
    }

    public void setBloodGP(String bloodGP) {
        this.bloodGP = bloodGP;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
