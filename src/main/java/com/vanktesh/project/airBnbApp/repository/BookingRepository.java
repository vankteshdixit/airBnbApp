package com.vanktesh.project.airBnbApp.repository;

import com.vanktesh.project.airBnbApp.entity.Booking;
import com.vanktesh.project.airBnbApp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByPaymentSessionId(String sessionId);

    List<Booking> findByHotel(Hotel hotel);

    List<Booking> findByHotelAndCreatedAtBetween(Hotel hotel,
                                                 LocalDateTime startDateTime,
                                                 LocalDateTime endDateTime);
}