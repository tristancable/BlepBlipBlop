package com.example.BlepBlipBlop.Controllers;

public class Settings {
    public static enum DBStatus {
        ARRAYLIST,
        SQL,
        JPA;
    }

    public static DBStatus dbStatus = DBStatus.SQL;
    public static String dbURL = "jdbc:mysql://localhost:3306/fantasy_world?createDatabaseIfNotExist=TRUE&allowPublicKeyRetrieval=TRUE&useSSL=FALSE";
    public static String username = "root";
    public static String password = "test";
}
