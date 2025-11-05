package com.vanktesh.project.airBnbApp.service;

import com.vanktesh.project.airBnbApp.dto.BookingDto;
import com.vanktesh.project.airBnbApp.dto.BookingRequest;

public interface BookingService {

    BookingDto initialiseBooking(BookingRequest bookingRequest);

}