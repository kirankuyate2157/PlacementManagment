package com.kiran.restapi.controller;

import com.kiran.restapi.entity.User;
import com.kiran.restapi.repository.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        try {
            // Check if a user with the same name already exists
            Optional<User> existingUser = userRepository.findByName(user.getName());

            if (existingUser.isPresent()) {
                // User with the same name already exists, return conflict response
                return new ResponseEntity<>("User with the same name already exists.", HttpStatus.CONFLICT);
            }

            // Save the new user
            User createdUser = userRepository.save(user);

            // Return the created user with 201 Created status
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any unexpected exception and return a 500 Internal Server Error response
            return new ResponseEntity<>("Failed to create user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Update only the non-null fields from the request
            if (updatedUser.getName() != null) {
                existingUser.setName(updatedUser.getName());
            }

            if (updatedUser.getType() != null) {
                existingUser.setType(updatedUser.getType());
            }

            if (updatedUser.getPassword() != null) {
                existingUser.setPassword(updatedUser.getPassword());
            }

            userRepository.save(existingUser);
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeUser(@PathVariable long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody User loginUser, HttpServletResponse response) {
        Optional<User> optionalUser = userRepository.findByNameAndPassword(loginUser.getName(), loginUser.getPassword());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();


         // Set secure cookies with HttpOnly attribute
            setSecureCookie(response, "userId", String.valueOf(user.getId()));
            setSecureCookie(response, "userName", user.getName());



            // Return user information in the response without including the password
            User userResponse = new User();
            userResponse.setId(user.getId());
            userResponse.setName(user.getName());
            userResponse.setType(user.getType());

            return ResponseEntity.ok().body(userResponse);
        } else {
            return new ResponseEntity<>("Login failed. User not found.", HttpStatus.NOT_FOUND);
        }
    }

    private void setSecureCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(true);  // Set the Secure attribute
        cookie.setHttpOnly(true);  // Set the HttpOnly attribute
        cookie.setMaxAge(864000);
        response.addCookie(cookie);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestBody User logoutUser, HttpServletResponse response) {
        // Validate the user by finding it in the repository
        Optional<User> optionalUser = userRepository.findByNameAndPassword(logoutUser.getName(), logoutUser.getPassword());

        if (optionalUser.isPresent()) {
            // User found, clear the cookies
            clearCookies(response);

            // Return a successful logout message
            return new ResponseEntity<>("Logout successful", HttpStatus.OK);
        } else {
            // User not found, return 404
            return new ResponseEntity<>("Logout failed. User not found.", HttpStatus.NOT_FOUND);
        }
    }
    private void clearCookies(HttpServletResponse response) {
        // Clear existing cookies by setting their maxAge to 0
        Cookie userIdCookie = new Cookie("userId", "");
        userIdCookie.setMaxAge(0);
        response.addCookie(userIdCookie);

        Cookie userNameCookie = new Cookie("userName", "");
        userNameCookie.setMaxAge(0);
        response.addCookie(userNameCookie);
    }
}
