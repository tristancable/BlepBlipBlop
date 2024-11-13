package com.example.BlepBlipBlop.Controllers;
import com.example.BlepBlipBlop.Models.Goal;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
@RestController
@RequestMapping("/goal")
public class GoalRestController {
    @RequestMapping(path="", method=RequestMethod.GET)
    public ArrayList<Goal> getAll() {
        ArrayList<Goal> goals = new ArrayList<>();
        return goals;
    }
    @RequestMapping(path="", method=RequestMethod.POST)
    public String createGoal(@RequestAttribute Goal goal) {
        if (findById(goal.getId()) != null) return "ID Conflict";
        return "";
    }
    @RequestMapping(path="", method=RequestMethod.PUT)
    public Goal updateGoal(@RequestAttribute Goal goal) {
        return null;
    }
    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public String deleteGoal(@PathVariable int id) {
        return "";
    }
    private Goal findById(int id) {
        Goal goal = null;
        return goal;
    }
}