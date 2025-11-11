package com.tp7.flutter.restControllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tp7.flutter.entities.User;
import com.tp7.flutter.repos.UserRepository;
import com.tp7.flutter.security.SecParams;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {

            String token = JWT.create()
                    .withSubject(existingUser.getEmail())
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecParams.EXP_TIME))
                    .sign(Algorithm.HMAC256(SecParams.SECRET));

            return token; 
        } else {
            return "Invalid credentials";
        }
    }
    
    @PostMapping("/logout")
    public String logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            return "Déconnexion réussie";
        } else {
            return "Token manquant";
        }
    }

}
