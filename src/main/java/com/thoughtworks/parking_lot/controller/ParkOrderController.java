package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkOrder;
import com.thoughtworks.parking_lot.exception.NotEnoughPositionException;
import com.thoughtworks.parking_lot.service.ParkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkinglots/{parkingLotId}/orders")
public class ParkOrderController {

    @Autowired
    ParkOrderService parkOrderService;

    @PostMapping()
    public ResponseEntity createOrder(@PathVariable long parkingLotId, @RequestBody ParkOrder parkOrder){
        try {
            return new ResponseEntity(parkOrderService.createOrder(parkingLotId, parkOrder), HttpStatus.OK);
        } catch (NotEnoughPositionException e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping()
    public ResponseEntity updateOrder(@RequestParam String carLicense) throws Exception {
        try {
            return new ResponseEntity(parkOrderService.updateOrder(carLicense), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
