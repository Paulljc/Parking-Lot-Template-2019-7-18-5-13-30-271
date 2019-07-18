package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkingLotRepositoryImp{

    public ParkingLot removeParkingLot(Long parkinglotId) {
        return null;
    }

    public List<ParkingLot> findAllParkingLots(Integer page, Integer pageSize) {
        return null;
    }

    public ParkingLot findParkingLotById(Long parkinglotId) {
        return null;
    }

    public ParkingLot updateParkingLotById(Long parkinglotId, ParkingLot parkingLot) {
        return null;
    }
}
