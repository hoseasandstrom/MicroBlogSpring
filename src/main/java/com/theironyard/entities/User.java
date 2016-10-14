package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by hoseasandstrom on 6/20/16.
 */
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    public
    String password;


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
