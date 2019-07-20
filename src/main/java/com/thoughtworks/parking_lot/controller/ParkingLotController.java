package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @DeleteMapping("/{parkingLotId}")
    public ResponseEntity removeParkingLot(@PathVariable Long parkingLotId){
        try {
            parkingLotService.removeParkingLot(parkingLotId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping()
    public ResponseEntity findParkingLotsByPage(@RequestParam("page")Integer page){
        try {
            return new ResponseEntity(parkingLotService.findParkingLotsByPage(page), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{parkingLotId}")
    public ResponseEntity findParkingLotById(@PathVariable Long parkingLotId){
        try {
            return new ResponseEntity(parkingLotService.findParkingLotById(parkingLotId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{parkingLotId}")
    public ResponseEntity updateParkingLotById(@PathVariable Long parkingLotId, @RequestBody ParkingLot parkingLot){
        try {
            return new ResponseEntity(parkingLotService.updateParkingLotById(parkingLotId, parkingLot), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity addParkingLot(@RequestBody ParkingLot parkingLot){
        try {
            return new ResponseEntity(parkingLotService.addParkingLot(parkingLot), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
