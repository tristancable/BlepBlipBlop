package com.example.BlepBlipBlop.Models;

public class User {
    private String username;
    private String password;
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
