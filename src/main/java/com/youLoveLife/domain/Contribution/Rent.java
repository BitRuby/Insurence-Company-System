package com.youLoveLife.domain.Contribution;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean isPaid;
    private Long amount;
    private Date fromDate;
    private Date toDate;

    @OneToOne(fetch = FetchType.LAZY)
    private SocialContribution socialContribution;

    public Rent() {
        this.isPaid = false;
        this.amount = Long.valueOf(0);
        this.fromDate = new Date();
        this.toDate = new Date();
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fromDate);
        c.add(Calendar.YEAR, 500);
        fromDate = c.getTime();
        toDate = c.getTime();
    }

    public Rent(SocialContribution socialContribution) {
        this.isPaid = false;
        this.amount = Long.valueOf(0);
        this.fromDate = new Date();
        this.toDate = new Date();
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fromDate);
        c.add(Calendar.YEAR, 500);
        fromDate = c.getTime();
        toDate = c.getTime();
        this.socialContribution = socialContribution;
    }

    public Rent(boolean isPaid, Long amount, Date from, Date to) {
        this.isPaid = isPaid;
        this.amount = amount;
        this.fromDate = from;
        this.toDate = to;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
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

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", isPaid=" + isPaid +
                ", amount=" + amount +
                ", from=" + fromDate +
                ", to=" + toDate +
                '}';
    }
}
