package com.vanktesh.project.airBnbApp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelSearchRequest {
    private String city;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer roomsCount;

//    Pagination
    private Integer page=0; //default
    private Integer size=10;
}