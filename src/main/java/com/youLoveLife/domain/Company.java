package com.youLoveLife.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.youLoveLife.domain.user.Address;
import com.youLoveLife.domain.user.AppUser;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyID;
    private String companyName;
    @Embedded
    private Address address;
    private String nip;
    private String regon;
    @OneToMany(mappedBy = "currentCompany", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"currentCompany", "ownCompany"})
    private List<AppUser> employees;

    //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"currentCompany", "ownCompany"})
    @OneToOne(fetch = FetchType.LAZY)
    private AppUser owner;


    public Company() {
    }

    public Company(AppUser owner, String companyName, Address address) {
        this.owner = owner;
        this.companyName = companyName;
        this.address = address;

        Random gen = new Random();
        Double number = Double.valueOf(gen.nextInt(999999999) + 1000000000);
        this.nip = String.format ("%.0f", number);
        number = Double.valueOf(gen.nextInt(999999999) + 1000000000);
        this.regon = String.format ("%.0f", number);
    }

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public List<AppUser> getEmployees() {
        return employees;
    }

    public void setEmployees(List<AppUser> employees) {
        this.employees = employees;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public void addNewEmployee(AppUser employee) {
        employees.add(employee);
    }

    public void deleteEmployee(AppUser employee) {
        employees.remove(employee);
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyID=" + companyID +
                ", owner=" + owner +
                ", companyName='" + companyName + '\'' +
                ", address=" + address +
                ", nip='" + nip + '\'' +
                ", regon='" + regon + '\'' +
                //", employees=" + employees +
                '}';
    }
}
