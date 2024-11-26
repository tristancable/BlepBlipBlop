package com.example.BlepBlipBlop.Models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {

    // @Query("SELECT s FROM Spaceship s WHERE s.pilot.pilot_id = :pilot_id")
    // List<Spaceship> findSpaceshipsByPilot_Id(int pilot_id);

    User findByUsernameAndPassword(String username, String password);

    
}
