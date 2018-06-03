package com.youLoveLife.domain.Contribution;

import com.youLoveLife.domain.user.AppUser;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SocialContribution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double amount;
    private Date fromDate;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "socialContribution")
    private Pension pension;
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "socialContribution")
    private Rent rent;

    @OneToOne(fetch = FetchType.LAZY)
    private AppUser appUser;

    public SocialContribution() {
        this.amount = Double.valueOf(0);
        this.fromDate = null;
        this.pension = new Pension();
        this.rent = new Rent();
    }

    public SocialContribution(AppUser appUser) {
        this.amount = Double.valueOf(0);
        this.fromDate = null;
        this.pension = new Pension(this);
        this.rent = new Rent(this);
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

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date from) {
        this.fromDate = from;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public String toString() {
        return "SocialContribution{" +
                "id=" + id +
                ", amount=" + amount +
                ", from=" + fromDate +
                ", pension=" + pension +
                ", rent=" + rent +
                '}';
    }
}
