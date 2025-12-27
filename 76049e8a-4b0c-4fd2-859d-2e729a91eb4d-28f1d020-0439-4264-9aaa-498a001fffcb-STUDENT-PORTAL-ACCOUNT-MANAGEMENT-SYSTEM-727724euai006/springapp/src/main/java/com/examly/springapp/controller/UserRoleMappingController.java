package com.examly.springapp.controller;

import com.examly.springapp.model.UserRoleMapping;
import com.examly.springapp.repository.UserRoleMappingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userRoleMappings")
public class UserRoleMappingController {
    
    @Autowired
    private UserRoleMappingRepo userRoleMappingRepo;
    
    @PostMapping
    public ResponseEntity<UserRoleMapping> addUserRoleMapping(@RequestBody UserRoleMapping mapping) {
        UserRoleMapping savedMapping = userRoleMappingRepo.save(mapping);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMapping);
    }
    
    @GetMapping
    public ResponseEntity<List<UserRoleMapping>> getAllUserRoleMappings() {
        List<UserRoleMapping> mappings = userRoleMappingRepo.findAll();
        return ResponseEntity.ok(mappings);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserRoleMapping> getUserRoleMappingById(@PathVariable Long id) {
        Optional<UserRoleMapping> mapping = userRoleMappingRepo.findById(id);
        return mapping.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserRoleMapping> updateUserRoleMapping(@PathVariable Long id, @RequestBody UserRoleMapping mapping) {
        if (userRoleMappingRepo.existsById(id)) {
            mapping.setId(id);
            UserRoleMapping updatedMapping = userRoleMappingRepo.save(mapping);
            return ResponseEntity.ok(updatedMapping);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRoleMapping>> getUserRoleMappingsByUserId(@PathVariable Long userId) {
        List<UserRoleMapping> mappings = userRoleMappingRepo.findByUserId(userId);
        if (mappings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mappings);
    }
    
    @GetMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<UserRoleMapping> getUserRoleMappingByUserIdAndRoleId(@PathVariable Long userId, @PathVariable Long roleId) {
        Optional<UserRoleMapping> mapping = userRoleMappingRepo.findByUserIdAndRoleId(userId, roleId);
        return mapping.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
