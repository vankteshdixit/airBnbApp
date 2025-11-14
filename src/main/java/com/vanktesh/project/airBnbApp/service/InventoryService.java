package com.vanktesh.project.airBnbApp.service;

import com.vanktesh.project.airBnbApp.dto.HotelPriceDto;
import com.vanktesh.project.airBnbApp.dto.HotelSearchRequest;
import com.vanktesh.project.airBnbApp.dto.InventoryDto;
import com.vanktesh.project.airBnbApp.dto.UpdateInventoryRequestDto;
import com.vanktesh.project.airBnbApp.entity.Room;
import org.springframework.data.domain.Page;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface InventoryService {

    void initializeRoomFOrAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);

    List<InventoryDto> getAllInventoryByRoom(Long roomId) throws AccessDeniedException;

    void updateInventory(Long roomId, UpdateInventoryRequestDto updateInventoryRequestDto) throws AccessDeniedException;
}
