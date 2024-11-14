package com.example.BlepBlipBlop.Models;
import java.util.Date;
public class Goal {
    private int id;
    private String title;
    private Date dueDate;
    private String description;
    private byte priority;
    private byte level;
    public Goal (int id, String title, Date dueDate, String description, byte priority, byte level) {
        setId(id);
        setTitle(title);
        setDueDate(dueDate);
        setDescription(description);
        setPriority(priority);
        setLevel(level);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public byte getPriority() {
        return priority;
    }
    public void setPriority(byte priority) {
        this.priority = priority;
    }
    public byte getLevel() {
        return level;
    }
    public void setLevel(byte level) {
        this.level = level;
    }
}