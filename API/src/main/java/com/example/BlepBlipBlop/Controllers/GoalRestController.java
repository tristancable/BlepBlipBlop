package com.example.BlepBlipBlop.Controllers;
import com.example.BlepBlipBlop.Models.Database;
import com.example.BlepBlipBlop.Models.Goal;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
@RestController
@RequestMapping("/goal")
public class GoalRestController {
    @RequestMapping(path="/{username}/{password}", method=RequestMethod.GET)
    public ArrayList<Goal> getGoals(@PathVariable String username, @PathVariable String password) {
        // Get user level
        // Get goal tables at and below level
        return null;
    }
    @RequestMapping(path="", method=RequestMethod.POST)
    public void createGoal(@RequestAttribute Goal goal) {
        if (Database.getById(goal.getId()) == null) {
            Database.add(goal);
        }
    }
    @RequestMapping(path="", method=RequestMethod.PUT)
    public Goal updateGoal(@RequestAttribute Goal goal) {
        return Database.update(goal);
    }
    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public void deleteGoal(@PathVariable int id) {
        Database.delete(id);
    }
}