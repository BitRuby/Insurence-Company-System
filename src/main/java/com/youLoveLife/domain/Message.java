package com.youLoveLife.domain;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userID;
    private boolean isReaded;
    @Column(length = 1024)
    private String message;
    private String topic;

    public Message() {
    }

    public Message(Long userID, String topic, String message) {
        this.userID = userID;
        this.isReaded = false;
        this.topic = topic;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userID=" + userID +
                ", isReaded=" + isReaded +
                ", message='" + message + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}