package com.vanktesh.project.airBnbApp.service;

import com.stripe.model.Event;
import com.vanktesh.project.airBnbApp.dto.BookingDto;
import com.vanktesh.project.airBnbApp.dto.BookingRequest;
import com.vanktesh.project.airBnbApp.dto.GuestsDto;
import com.vanktesh.project.airBnbApp.dto.HotelReportDto;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookingService {

    BookingDto initialiseBooking(BookingRequest bookingRequest);
    BookingDto addGuests(Long bookingId, List<GuestsDto> guestsDtoList);
    String initiatePayments(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    String getBookingStatus(Long bookingId);

    List<BookingDto> getAllBookingsByHotelId(Long hotelId) throws AccessDeniedException;

    HotelReportDto geHotelReport(Long hotelId, LocalDate startDate, LocalDate endDate) throws AccessDeniedException;

    List<BookingDto> getMyBookings();
}