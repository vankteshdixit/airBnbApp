package com.vanktesh.project.airBnbApp.dto;

import com.vanktesh.project.airBnbApp.entity.Hotel;
import com.vanktesh.project.airBnbApp.entity.Room;
import com.vanktesh.project.airBnbApp.entity.User;
import com.vanktesh.project.airBnbApp.entity.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class BookingDto {
    private Long id;
    private Hotel hotel;
    private Room room;
    private User user;
    private Integer roomCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus bookingStatus;
    private Set<GuestsDto> guests;
}
