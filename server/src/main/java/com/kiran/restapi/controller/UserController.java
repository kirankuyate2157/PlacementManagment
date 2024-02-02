package com.kiran.restapi.controller;

import com.kiran.restapi.entity.User;
import com.kiran.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createdUser = userRepository.save(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setName(user.getName());
            existingUser.setType(user.getType());
            existingUser.setPassword(user.getPassword());
            userRepository.save(existingUser);
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginUser) {
        Optional<User> optionalUser = userRepository.findByNameAndPassword(loginUser.getName(), loginUser.getPassword());

        if (optionalUser.isPresent()) {
            // User found, consider the user logged in
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            // User not found, return 404
            return new ResponseEntity<>("Login failed. User not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestBody User logoutUser) {
        // You might not need the password for logout, but including
        Optional<User> optionalUser = userRepository.findByNameAndPassword(logoutUser.getName(), logoutUser.getPassword());

        if (optionalUser.isPresent()) {
            // User found, consider the user logged out
            return new ResponseEntity<>("Logout successful", HttpStatus.OK);
        } else {
            // User not found, return 404
            return new ResponseEntity<>("Logout failed. User not found.", HttpStatus.NOT_FOUND);
        }
    }
}
