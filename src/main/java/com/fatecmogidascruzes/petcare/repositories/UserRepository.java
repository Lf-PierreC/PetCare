package com.fatecmogidascruzes.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatecmogidascruzes.petcare.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
};
