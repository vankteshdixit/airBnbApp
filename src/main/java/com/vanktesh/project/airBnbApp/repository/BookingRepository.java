package com.vanktesh.project.airBnbApp.repository;

import com.vanktesh.project.airBnbApp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}