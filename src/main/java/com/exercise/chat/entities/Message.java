package com.exercise.chat.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String messageTime;

    private String date;
    private String userName;
    private String textString;
    private Color color;


    public Message() {
    }

    public Message(String userName, String textString) {
        this.userName = userName;
        this.textString = textString;

    }

    public void setDate(String date) {
        date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    public String getDate() {return date;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTextString() {
        return textString;
    }

    public void setTextString(String message) {
        this.textString = message;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public String toString() {
        return "Message{" +
            "id=" + id +
            ", date=" + date +
            ", messageTime='" + messageTime + '\'' +
            ", userName='" + userName + '\'' +
            ", textString='" + textString + '\'' +
            ", color=" + color +
            '}';
    }
}
