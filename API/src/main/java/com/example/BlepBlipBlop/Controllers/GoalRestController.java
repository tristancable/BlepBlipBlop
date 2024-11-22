package com.example.BlepBlipBlop.Controllers;
import com.example.BlepBlipBlop.Models.Database;
import com.example.BlepBlipBlop.Models.Goal;
import com.example.BlepBlipBlop.Models.User;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
@RestController
@RequestMapping("/goal")
public class GoalRestController {
    @RequestMapping(path="/{username}/{password}", method=RequestMethod.GET)
    public ArrayList<Goal> getAllGoals(@PathVariable String username, @PathVariable String password) {
        User user = Database.getUserByUsernameAndPassword(username, password);
        byte level = user.getLevel();
        ArrayList<Goal> goals = Database.getAllGoals(level);
        return goals;
    }
    @RequestMapping(path="", method=RequestMethod.POST)
    public void createGoal(@RequestBody Goal goal) {
        if (Database.getGoalById(goal.getId()) == null) {
            Database.addGoal(goal);
        }
    }
    @RequestMapping(path="", method=RequestMethod.PUT)
    public Goal updateGoal(@RequestBody Goal goal) {
        return Database.updateGoal(goal);
    }
    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public void deleteGoal(@PathVariable int id) {
        Database.deleteGoal(id);
    }
}