package com.youLoveLife.domain.Contribution;

import com.youLoveLife.domain.user.AppUser;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String companyInfo;
    private Date fromDate;
    private Date toDate;
    private Double salary;
    @ManyToOne
    private AppUser appUser;

    public Job() {
    }

    public Job(String companyInfo, Date from, Date to, Double salary, AppUser appUser) {
        this.companyInfo = companyInfo;
        this.fromDate = from;
        this.toDate = to;
        this.salary = salary;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", companyInfo='" + companyInfo + '\'' +
                ", from=" + fromDate +
                ", to=" + toDate +
                ", salary=" + salary +
                '}';
    }
}
