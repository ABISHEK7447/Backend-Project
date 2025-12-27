package com.examly.springapp.repository;

import com.examly.springapp.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p WHERE p.firstName = :firstName")
    List<Profile> findByFirstName(@Param("firstName") String firstName);
    
    @Query("SELECT p FROM Profile p WHERE p.firstName = :firstName AND p.address = :address")
    List<Profile> findByFirstNameAndAddress(@Param("firstName") String firstName, @Param("address") String address);
}
