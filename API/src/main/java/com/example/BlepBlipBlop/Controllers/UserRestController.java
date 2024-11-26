package com.example.BlepBlipBlop.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import com.example.BlepBlipBlop.Models.User;
import com.example.BlepBlipBlop.Models.UserJpaRepository;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  UserJpaRepository userJpaRepository;
    @RequestMapping(path="", method=RequestMethod.GET)
    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) userJpaRepository.findAll();
    }
    @RequestMapping(path="", method=RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (userJpaRepository.findByUsername(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userJpaRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created");
        } 
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username taken");
    }
    @RequestMapping(path="", method=RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        User existingUser = userJpaRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
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
    @RequestMapping(path="/login", method=RequestMethod.POST)
    public ResponseEntity<String> loginUser(@RequestBody User user, HttpServletResponse response) {
        User dbUser = userJpaRepository.findByUsername(user.getUsername());
        if (dbUser != null) {
            if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
                Cookie cookie = new Cookie("loggedIn", "true");
                cookie.setMaxAge(60 * 60 * 24);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }
}