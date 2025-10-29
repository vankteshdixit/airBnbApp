package com.vanktesh.project.airBnbApp.service;

import com.vanktesh.project.airBnbApp.entity.Room;

public interface InventoryService {

    void initializeRoomFOrAYear(Room room);

    void deleteFutureInventories(Room room);

}
