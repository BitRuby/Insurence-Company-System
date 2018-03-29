package com.youlovelife.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NormalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String login;
    private String password;

    public NormalUser() {

    }
}
