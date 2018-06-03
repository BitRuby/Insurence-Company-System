package com.youLoveLife.domain.Contribution;

import com.youLoveLife.domain.user.AppUser;
import com.youLoveLife.enums.ContributionType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


public class Contribution {
    private Long id;

    private ContributionType type;
    private Date from, whenRetire, whenSick, whenAllowance;



    public Contribution() {
        this.type = ContributionType.SOCIAL;
        from = new Date();
        whenRetire = new Date();
        LocalDateTime.from(whenRetire.toInstant()).plusYears(200);
        LocalDateTime.from(whenRetire.toInstant()).plusYears(200);
        LocalDateTime.from(whenRetire.toInstant()).plusYears(200);
    }

    public Contribution(ContributionType type) {
        this.type = type;
        from = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public ContributionType getType() {
        return type;
    }

    public void setType(ContributionType type) {
        this.type = type;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getWhenRetire() {
        return whenRetire;
    }

    public void setWhenRetire(Date whenRetire) {
        this.whenRetire = whenRetire;
    }

    public Date getWhenSick() {
        return whenSick;
    }

    public void setWhenSick(Date whenSick) {
        this.whenSick = whenSick;
    }

    public Date getWhenAllowance() {
        return whenAllowance;
    }

    public void setWhenAllowance(Date whenAllowance) {
        this.whenAllowance = whenAllowance;
    }
}
