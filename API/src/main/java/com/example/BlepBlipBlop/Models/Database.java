package com.example.BlepBlipBlop.Models;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class Database {

    @Autowired
    private static GoalJpaRepository goalJpaRepository;

    @Autowired
    private static UserJpaRepository userJpaRepository;

    public static void addGoal(Goal goal) {
        goalJpaRepository.save(goal);
    }

    public static Goal getGoalById(int id) {
        Optional<Goal> goal = goalJpaRepository.findById(id);
        return goal.orElse(null);
    }

    public static ArrayList<Goal> getAllGoals(byte level) {

        return (ArrayList<Goal>) goalJpaRepository.findAll();
    }

    public static Goal updateGoal(Goal goal) {

        return goalJpaRepository.save(goal);
    }

    public static void deleteGoal(int id) {
        goalJpaRepository.deleteById(id);
    }

    public static void addUser(User user) {
        userJpaRepository.save(user);
    }

    public static User getUserByUsernameAndPassword(String username, String password) {

        // return userJpaRepository.;
        return null; // TODO We need to update this with a more specific query
    }

    public static ArrayList<User> getAllUsers() {
        return (ArrayList<User>) userJpaRepository.findAll();
    }

    public static void updateUser(User user) {
        userJpaRepository.save(user);
    }

    public static void deleteUser(int id) {
        userJpaRepository.deleteById(id);
    }
}
