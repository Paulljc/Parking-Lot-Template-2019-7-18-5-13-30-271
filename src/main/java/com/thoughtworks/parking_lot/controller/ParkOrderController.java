package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.Car;
import com.thoughtworks.parking_lot.entity.ParkOrder;
import com.thoughtworks.parking_lot.service.ParkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/orders")
public class ParkOrderController {

    @Autowired
    ParkOrderService parkOrderService;

    @PostMapping("/parkCar")
    public ParkOrder parkCar(@RequestBody Car car){
        return parkOrderService.parkCar(car);
    }

    @DeleteMapping("/fetchCar")
    public Car fetchCar(@RequestBody ParkOrder parkingOrder){
        return parkOrderService.fetchCar(parkingOrder);
    }
}
