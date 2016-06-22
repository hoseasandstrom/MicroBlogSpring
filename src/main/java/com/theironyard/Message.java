package com.theironyard;

import javax.persistence.*;

/**
 * Created by hoseasandstrom on 6/20/16.
 */
@Entity
@Table(name = "messages")
public class Message {
    @Id
            @GeneratedValue
    int id;

    @Column(nullable = false)
    String text;

    public Message(String text) {
        this.text = text;
    }

    public Message(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
