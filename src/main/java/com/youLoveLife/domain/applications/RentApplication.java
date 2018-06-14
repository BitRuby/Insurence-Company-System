package com.youLoveLife.domain.applications;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RentApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long applicationID;
    private String name;
    private String surname;
    private String city, building, street, postcode, country;
    private boolean isApproved;
    private Long userID;

    public RentApplication() {
    }

    public RentApplication(String name, String surname, String city, String building, String street, String postcode, String country, boolean isApproved, Long userID) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.building = building;
        this.street = street;
        this.postcode = postcode;
        this.country = country;
        this.isApproved = isApproved;
        this.userID = userID;
    }

    public Long getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(Long applicationID) {
        this.applicationID = applicationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "RentApplicationRepository{" +
                "applicationID=" + applicationID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                ", building='" + building + '\'' +
                ", street='" + street + '\'' +
                ", postcode='" + postcode + '\'' +
                ", country='" + country + '\'' +
                ", isApproved=" + isApproved +
                ", userID=" + userID +
                '}';
    }
}
