package com.tp7.flutter.restControllers;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

	private Set<String> invalidTokens = new HashSet<>(); 
	
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
            invalidTokens.add(token);
            return "Déconnexion réussie";
        } else {
            return "Token manquant";
        }
    }

    public boolean isTokenInvalid(String token) {
        return invalidTokens.contains(token);
    }
    
    @GetMapping("/profile")
    public User getProfile(@RequestHeader("Authorization") String authHeader) {
        String email = extractEmailFromToken(authHeader);
        return userRepository.findByEmail(email);
    }

    @PutMapping("/profile")
    public User updateProfile(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody User updatedUser
    ) {
        String email = extractEmailFromToken(authHeader);
        User user = userRepository.findByEmail(email);

        if (user != null) {
            user.setEmail(updatedUser.getEmail() != null ? updatedUser.getEmail() : user.getEmail());
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            return userRepository.save(user);
        }
        return null;
    }

    // Helper pour extraire email depuis le token JWT
    private String extractEmailFromToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            return JWT.decode(token).getSubject();
        }
        return null;
    }


}
