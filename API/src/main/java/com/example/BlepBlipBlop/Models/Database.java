package com.example.BlepBlipBlop.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {

    private static String dbURL = "jdbc:mysql://localhost:3306/fantasy_world?createDatabaseIfNotExist=TRUE&allowPublicKeyRetrieval=TRUE&useSSL=FALSE";
    private static String dbUser = "root";
    private static String dbPass = "test";

    public static void addGoal(Goal goal) {
        String tempSQLtxt = "TBA";

        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            PreparedStatement preparedStatement = conn.prepareStatement(tempSQLtxt);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("affected Rows: " + affectedRows);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static Goal getGoalById(int id) {
        String tempSQLtxt = "TBA";

        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            PreparedStatement preparedStatement = conn.prepareStatement(tempSQLtxt);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("affected Rows: " + affectedRows);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
    }

    public static ArrayList<Goal> getAllGoals(byte level) {
        String tempSQLtxt = "TBA";

        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            PreparedStatement preparedStatement = conn.prepareStatement(tempSQLtxt);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("affected Rows: " + affectedRows);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        switch (level) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;

        }
        return null;
    }

    public static Goal updateGoal(Goal goal) {
        String tempSQLtxt = "TBA";

        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            PreparedStatement preparedStatement = conn.prepareStatement(tempSQLtxt);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("affected Rows: " + affectedRows);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }

    public static void deleteGoal(int id) {
        String tempSQLtxt = "TBA";

        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            PreparedStatement preparedStatement = conn.prepareStatement(tempSQLtxt);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("affected Rows: " + affectedRows);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void addUser(User user) {
        String tempSQLtxt = "TBA";

        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            PreparedStatement preparedStatement = conn.prepareStatement(tempSQLtxt);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("affected Rows: " + affectedRows);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static User getUserByUsernameAndPassword(String username, String password) {
        String tempSQLtxt = "TBA";

        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            PreparedStatement preparedStatement = conn.prepareStatement(tempSQLtxt);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("affected Rows: " + affectedRows);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }

    public static ArrayList<User> getAllUsers() {
        String tempSQLtxt = "TBA";

        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            PreparedStatement preparedStatement = conn.prepareStatement(tempSQLtxt);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("affected Rows: " + affectedRows);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        switch (level) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;

        }
        return null;
    }

    public static void updateUser(User user) {
        String tempSQLtxt = "TBA";

        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            PreparedStatement preparedStatement = conn.prepareStatement(tempSQLtxt);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("affected Rows: " + affectedRows);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void deleteUser(int id) {
        String tempSQLtxt = "TBA";

        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            PreparedStatement preparedStatement = conn.prepareStatement(tempSQLtxt);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("affected Rows: " + affectedRows);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
