package com.example.BlepBlipBlop.Controllers;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import com.example.BlepBlipBlop.Models.User;
import com.example.BlepBlipBlop.Models.Database;
@RestController
@RequestMapping("/user")
public class UserRestController {
    @RequestMapping(path="/", method=RequestMethod.GET)
    public ArrayList<User> getAllUsers() {
        return Database.getAllUsers();
    }
    @RequestMapping(path="/", method=RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        Database.addUser(user);
    }
    @RequestMapping(path="/", method=RequestMethod.PUT)
    public void updateUser(@RequestBody User user) {
        Database.updateUser(user);
    }
    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public void deleteUser(@PathVariable int id) {
        Database.deleteUser(id);
    }
}