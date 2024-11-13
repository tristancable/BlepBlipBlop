package com.example.BlepBlipBlop.Models;
import java.util.Date;
public class Goal {
    private String title;
    private Date dueDate;
    private String description;
    private byte priority;
    public Goal (String title, Date dueDate, String description, byte priority) {
        setTitle(title);
        setDueDate(dueDate);
        setDescription(description);
        setPriority(priority);
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
}