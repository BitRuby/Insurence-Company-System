package com.youLoveLife.domain;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long problemID;
    private boolean isReaded;
    @Column(length = 1024)
    private String message;
    private String topic;
    private Date receivedDate;

    public Problem() {
    }

    public Problem(String message, String topic) {
        this.message = message;
        this.topic = topic;
        this.isReaded = false;
        this.receivedDate = new Date();
    }

    public Long getProblemID() {
        return problemID;
    }

    public void setProblemID(Long problemID) {
        this.problemID = problemID;
    }

    public boolean isReaded() {
        return isReaded;
    }

    public void setReaded(boolean readed) {
        isReaded = readed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "problemID=" + problemID +
                ", isReaded=" + isReaded +
                ", message='" + message + '\'' +
                ", topic='" + topic + '\'' +
                ", receivedDate=" + receivedDate +
                '}';
    }
}
