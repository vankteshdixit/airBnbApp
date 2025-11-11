package com.vanktesh.project.airBnbApp.service;

import com.vanktesh.project.airBnbApp.dto.RoomDto;
import com.vanktesh.project.airBnbApp.entity.Hotel;
import com.vanktesh.project.airBnbApp.entity.Room;
import com.vanktesh.project.airBnbApp.entity.User;
import com.vanktesh.project.airBnbApp.exception.ResourceNotFoundException;
import com.vanktesh.project.airBnbApp.exception.UnAuthorisedException;
import com.vanktesh.project.airBnbApp.repository.HotelRepository;
import com.vanktesh.project.airBnbApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating a new Room in Hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id: {} "+hotelId));

        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.getId().equals(hotel.getOwner().getId())){
            throw new UnAuthorisedException("This User does not own this hotel with id: "+ hotelId);
        }

        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);

        if(hotel.getActive()){
            inventoryService.initializeRoomFOrAYear(room);
        }

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all Rooms in Hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id: {} "+hotelId));

        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnAuthorisedException("This User does not own this hotel with id: "+ hotelId);
        }

        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting all Room with ID: {}", roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found with ID: {} "+roomId));

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Delete the room with ID: {}", roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found with ID: {} "+roomId));

        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(room.getHotel().getOwner())){
            throw new UnAuthorisedException("This User does not own this room with id: "+ roomId);
        }

        inventoryService.deleteAllInventories(room);
        roomRepository.deleteById(roomId);
    }
}