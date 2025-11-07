package com.vanktesh.project.airBnbApp.service;

import com.vanktesh.project.airBnbApp.dto.HotelPriceDto;
import com.vanktesh.project.airBnbApp.dto.HotelSearchRequest;
import com.vanktesh.project.airBnbApp.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomFOrAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
