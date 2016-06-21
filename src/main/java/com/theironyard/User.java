package com.theironyard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

/**
 * Created by hoseasandstrom on 6/20/16.
 */
@Entity
@Table(name = "users")

public class User {
    @Column(nullable = false)
    String name;


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
}
