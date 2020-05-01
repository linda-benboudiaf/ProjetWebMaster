package com.ProductManager.ProjectWebMaster.controllers;

/** This controller will use token and API to log in the user **/

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/** API implementation **/

/** Import all Users packages tools **/
import com.ProductManager.ProjectWebMaster.token.JwtTokenProvider;
import com.ProductManager.ProjectWebMaster.auth.CustomUserDetails;
import com.ProductManager.ProjectWebMaster.models.User;
import com.ProductManager.ProjectWebMaster.interfaces.UserInterface;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserInterface users;

    @Autowired
    private CustomUserDetails userService;


    @RequestMapping(value="/", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        return "hello";
    }

    @GetMapping("/allUsers")
    public Iterable<User> getAllUsers(){
        return users.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationBody data) {
        try {
            String username = data.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.users.findByEmail(username).getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            System.out.println(token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid Email or Password retry ! ");
        }
    }

    /** Insert new User into collection "users" **/
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + user.getEmail() + " already exists");
        }
        userService.saveUser(user);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        return ok(model);
    }
}
