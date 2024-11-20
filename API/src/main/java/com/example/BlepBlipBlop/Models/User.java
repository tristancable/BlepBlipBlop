package com.example.BlepBlipBlop.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private byte level;
    
    public User(String username, String password, byte level) {
        setUsername(username);
        setPassword(password);
        setLevel(level);
    }

    public String getUsername() {
        return username;
    }
    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    private void setPassword(String password) {
        this.password = password;
    }

    public byte getLevel() {
        return level;
    }
    private void setLevel(byte level) {
        this.level = level;
    }

    
}
