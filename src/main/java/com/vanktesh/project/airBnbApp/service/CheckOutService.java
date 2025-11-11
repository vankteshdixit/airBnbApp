package com.vanktesh.project.airBnbApp.service;

import com.vanktesh.project.airBnbApp.entity.Booking;

public interface CheckOutService {
    String getCheckOutSession(Booking booking, String successUrl, String failureUrl);
}
