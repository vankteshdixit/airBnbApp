package com.vanktesh.project.airBnbApp.repository;

import com.vanktesh.project.airBnbApp.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}