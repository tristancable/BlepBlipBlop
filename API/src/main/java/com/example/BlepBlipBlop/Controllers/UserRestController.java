package com.example.BlepBlipBlop.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (userJpaRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()) == null) {
            userJpaRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created");
        } 
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username taken");
        
    }
    @RequestMapping(path="", method=RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        User existingUser = userJpaRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setLevel(user.getLevel());
            userJpaRepository.save(existingUser);
        
            return ResponseEntity.status(HttpStatus.CREATED).body("User updated");
        } 
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
    }
    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public void deleteUser(@PathVariable int id) {
        userJpaRepository.deleteById(id);
    }
}