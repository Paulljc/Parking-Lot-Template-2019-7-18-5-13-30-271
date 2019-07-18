package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @DeleteMapping("/{parkinglotId}")
    public ParkingLot removeParkingLot(@PathVariable Long parkinglotId){
        return parkingLotService.removeParkingLot(parkinglotId);
    }

    @GetMapping()
    public List<ParkingLot> listAllParkingLots(Integer page, Integer pageSize){
        return parkingLotService.findAllParkingLots(page, pageSize);
    }

    @GetMapping("/{parkinglotId}")
    public ParkingLot listParkingLotById(@PathVariable Long parkinglotId){
        return parkingLotService.findParkingLotById(parkinglotId);
    }

    @PutMapping()
    public ParkingLot updateParkingLotById(@PathVariable Long parkinglotId, ParkingLot parkingLot){
        return parkingLotService.updateParkingLotById(parkinglotId, parkingLot);
    }
}
