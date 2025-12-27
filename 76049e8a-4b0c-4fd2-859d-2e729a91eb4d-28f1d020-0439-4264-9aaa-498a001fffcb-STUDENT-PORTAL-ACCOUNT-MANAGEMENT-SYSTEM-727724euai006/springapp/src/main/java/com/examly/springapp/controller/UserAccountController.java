package com.examly.springapp.controller;

import com.examly.springapp.model.UserAccount;
import com.examly.springapp.repository.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {
    
    @Autowired
    private UserAccountRepo userAccountRepo;
    
    @PostMapping
    public ResponseEntity<UserAccount> createUser(@RequestBody UserAccount user) {
        UserAccount savedUser = userAccountRepo.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
