package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.entity.Car;
import com.thoughtworks.parking_lot.entity.ParkOrder;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkOrderRepository extends JpaRepository<ParkingLot, Long> {
    ParkOrder parkCar(Car car);
    Car fetchCar(ParkOrder parkingOrder);
}
