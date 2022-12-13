package com.exercise.chat.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String name;
    private Color color;

    private String password;

    public User() {}

    public User(String name, Color color, String password) {
        this.name = name;
        this.color = color;
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isRightPassword (String password) {
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public long getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public <T> T getPassword(){
        return (T) password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", password=" + password + ", color=" + color + '}';
    }
}
