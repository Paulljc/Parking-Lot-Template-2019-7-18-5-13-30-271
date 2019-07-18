package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.entity.Car;
import com.thoughtworks.parking_lot.entity.ParkOrder;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkOrderService {

    @Autowired
    private ParkOrderRepository parkOrderRepository;

    public ParkOrder parkCar(Car car) {
        return parkOrderRepository.parkCar(car);
    }

    public Car fetchCar(ParkOrder parkingOrder) {
        return parkOrderRepository.fetchCar(parkingOrder);
    }
}
