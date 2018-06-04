package com.youLoveLife.domain.Contribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.youLoveLife.domain.user.AppUser;

import javax.persistence.*;
import java.util.Date;

@Entity
public class HealthContribution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double amount;
    private Date fromDate;
    private Date toDate;

    private boolean isInsured;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("healthContribution")
    private AppUser appUser;

    public HealthContribution() {
        this.amount = Double.valueOf(0);
        this.fromDate = null;
        this.toDate = null;
        this.isInsured = false;
    }

    public HealthContribution(AppUser appUser) {
        this.amount = Double.valueOf(0);
        this.fromDate = null;
        this.toDate = null;
        this.isInsured = false;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date from) {
        this.fromDate = from;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date to) {
        this.toDate = to;
    }

    public boolean isInsured() {
        return isInsured;
    }

    public void setInsured(boolean insured) {
        isInsured = insured;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
