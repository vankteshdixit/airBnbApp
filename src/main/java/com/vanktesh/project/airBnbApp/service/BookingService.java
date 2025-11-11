package com.vanktesh.project.airBnbApp.service;

import com.vanktesh.project.airBnbApp.dto.BookingDto;
import com.vanktesh.project.airBnbApp.dto.BookingRequest;
import com.vanktesh.project.airBnbApp.dto.GuestsDto;

import java.util.List;

public interface BookingService {

    BookingDto initialiseBooking(BookingRequest bookingRequest);
    BookingDto addGuests(Long bookingId, List<GuestsDto> guestsDtoList);
    String initiatePayments(Long bookingId);
}