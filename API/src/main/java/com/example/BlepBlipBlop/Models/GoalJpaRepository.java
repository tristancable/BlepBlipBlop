package com.example.BlepBlipBlop.Models;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalJpaRepository extends JpaRepository<Goal, Integer> {

    ArrayList<Goal> findByLevelLessThanEqual(byte level);
    
}
