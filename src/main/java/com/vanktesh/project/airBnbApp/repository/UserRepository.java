package com.vanktesh.project.airBnbApp.repository;

import com.vanktesh.project.airBnbApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
