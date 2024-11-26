package com.example.BlepBlipBlop.Models;
import java.io.Serializable;
import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Goal")
public class Goal implements Serializable{

    @Id
    @Column(name = "goalId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private Date dueDate;

    @Column(nullable = false)
    private byte priority;

    @Column(nullable = false)
    private byte level;

    public Goal(){}
    
    public Goal (String title, String description, Date dueDate,  byte priority, byte level) {
        setTitle(title);
        setDescription(description);
        setDueDate(dueDate);
        setPriority(priority);
        setLevel(level);
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    private void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    private void setDescription(String description) {
        this.description = description;
    }
    public Date getDate() {
        return dueDate;
    }
    private void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public byte getPriority() {
        return priority;
    }
    private void setPriority(byte priority) {
        this.priority = priority;
    }
    public byte getLevel() {
        return level;
    }
    private void setLevel(byte level) {
        this.level = level;
    }
}