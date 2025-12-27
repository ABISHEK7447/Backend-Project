package com.examly.springapp.controller;

import com.examly.springapp.model.Profile;
import com.examly.springapp.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    
    @Autowired
    private ProfileRepo profileRepo;
    
    @PostMapping
    public ResponseEntity<Profile> addProfile(@RequestBody Profile profile) {
        Profile savedProfile = profileRepo.save(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }
    
    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileRepo.findAll();
        return ResponseEntity.ok(profiles);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        Optional<Profile> profile = profileRepo.findById(id);
        return profile.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
        if (profileRepo.existsById(id)) {
            profile.setId(id);
            Profile updatedProfile = profileRepo.save(profile);
            return ResponseEntity.ok(updatedProfile);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/name/{firstName}")
    public ResponseEntity<List<Profile>> getProfileByName(@PathVariable String firstName) {
        List<Profile> profiles = profileRepo.findByFirstName(firstName);
        return ResponseEntity.ok(profiles);
    }
    
    @GetMapping("/search/{firstName}/{address}")
    public ResponseEntity<List<Profile>> getProfilesByNameAndAddress(@PathVariable String firstName, @PathVariable String address) {
        List<Profile> profiles = profileRepo.findByFirstNameAndAddress(firstName, address);
        return ResponseEntity.ok(profiles);
    }
}
