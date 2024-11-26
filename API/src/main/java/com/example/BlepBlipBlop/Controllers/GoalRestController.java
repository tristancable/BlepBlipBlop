package com.example.BlepBlipBlop.Controllers;
import com.example.BlepBlipBlop.Models.Goal;
import com.example.BlepBlipBlop.Models.GoalJpaRepository;
import com.example.BlepBlipBlop.Models.User;
import com.example.BlepBlipBlop.Models.UserJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
@RestController
@RequestMapping("/goal")
public class GoalRestController {
        @Autowired
    private  GoalJpaRepository goalJpaRepository;
        @Autowired
    private  UserJpaRepository userJpaRepository;
    @RequestMapping(path="/{username}/{password}", method=RequestMethod.GET)
    public ArrayList<Goal> getAllGoals(@PathVariable String username, @PathVariable String password) {
        User user = userJpaRepository.findByUsernameAndPassword(username, password);
        byte level = user.getLevel();
        ArrayList<Goal> goals = (ArrayList<Goal>) goalJpaRepository.findByLevelLessThanEqual(level);
\        return goals;
    }
    @RequestMapping(path="", method=RequestMethod.POST)
    public void createGoal(@RequestBody Goal goal) {
        goalJpaRepository.save(goal);
    }
    @RequestMapping(path="", method=RequestMethod.PUT)
    public Goal updateGoal(@RequestBody Goal goal) {
        return goalJpaRepository.save(goal);
    }
    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public void deleteGoal(@PathVariable int id) {
        goalJpaRepository.deleteById(id);
    }
}