package com.examly.springapp.controller;

import com.examly.springapp.model.Role;
import com.examly.springapp.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleRepo roleRepo;

    @PostConstruct
    public void init() {
        if (roleRepo.count() == 0) {
            Role defaultRole = new Role();
            defaultRole.setRoleName("Admin");
            roleRepo.save(defaultRole);
        }
    }
    
    @PostMapping
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        Role savedRole = roleRepo.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleRepo.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleRepo.findById(id);
        return role.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role updatedRole) {
        if (roleRepo.existsById(id)) {
            updatedRole.setId(id);
            Role savedRole = roleRepo.save(updatedRole);
            return ResponseEntity.ok(savedRole);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        if (roleRepo.existsById(id)) {
            roleRepo.deleteById(id);
            return ResponseEntity.ok("Role deleted");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/page/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<Role>> getRolesPaginated(@PathVariable int pageNumber, @PathVariable int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Role> page = roleRepo.findAll(pageable);
        return ResponseEntity.ok(page);
    }
}
