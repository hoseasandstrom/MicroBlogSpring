package com.theironyard;

/**
 * Created by hoseasandstrom on 6/20/16.
 */
public class Message {
    Integer id;
    String text;

    public Message(String text) {

        this.text = text;
    }

    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
