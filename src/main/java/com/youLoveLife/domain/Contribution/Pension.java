package com.youLoveLife.domain.Contribution;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
public class Pension {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double amount;
    private boolean isGranted;
    private Date fromDate;

    @OneToOne(fetch = FetchType.LAZY)
    private SocialContribution socialContribution;

    public Pension() {
        this.amount = Double.valueOf(0);
        this.isGranted = false;
        this.fromDate = new Date();
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(2500,1,1);
        fromDate = cal.getTime();
    }

    public Pension(SocialContribution socialContribution) {
        this.amount = Double.valueOf(0);
        this.isGranted = false;
        this.fromDate = new Date();
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(2500,1,1);
        fromDate = cal.getTime();
        this.socialContribution = socialContribution;
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

    public boolean isGranted() {
        return isGranted;
    }

    public void setGranted(boolean granted) {
        isGranted = granted;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date from) {
        this.fromDate = from;
    }

    @Override
    public String toString() {
        return "Pension{" +
                "id=" + id +
                ", amount=" + amount +
                ", isGranted=" + isGranted +
                ", from=" + fromDate +
                '}';
    }
}
