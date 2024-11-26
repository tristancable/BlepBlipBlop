package com.example.BlepBlipBlop.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import com.example.BlepBlipBlop.Models.User;
import com.example.BlepBlipBlop.Models.UserJpaRepository;
@RestController
@RequestMapping("/user")
public class UserRestController {
        @Autowired
    private  UserJpaRepository userJpaRepository;
    @RequestMapping(path="", method=RequestMethod.GET)
    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) userJpaRepository.findAll();    }

    @RequestMapping(path="", method=RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        if (userJpaRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()) == null) {
            userJpaRepository.save(user);
        }
    }
    @RequestMapping(path="", method=RequestMethod.PUT)
    public void updateUser(@RequestBody User user) {
        User ogUser = userJpaRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (ogUser != null) {
            // ogUser.setUsername(user.getUsername());
            // ogUser.setPassword(user.getPassword());
            userJpaRepository.save(user);
        }    
    }
    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public void deleteUser(@PathVariable int id) {
        userJpaRepository.deleteById(id);
    }
}