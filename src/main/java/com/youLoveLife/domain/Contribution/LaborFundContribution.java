package com.youLoveLife.domain.Contribution;

import com.youLoveLife.domain.user.AppUser;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LaborFundContribution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date fromDate;
    private Date toDate;
    private boolean isActive;
    private Double amount;

    @OneToOne
    private AppUser appUser;


    public LaborFundContribution() {
        this.fromDate = null;
        this.toDate = null;
        this.isActive = false;
        this.amount = Double.valueOf(0);
    }

    public LaborFundContribution(AppUser appUser) {
        this.fromDate = null;
        this.toDate = null;
        this.isActive = false;
        this.amount = Double.valueOf(0);
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public String toString() {
        return "LaborFundContribution{" +
                "id=" + id +
                ", from=" + fromDate +
                ", to=" + toDate +
                ", isActive=" + isActive +
                ", amount=" + amount +
                '}';
    }
}
