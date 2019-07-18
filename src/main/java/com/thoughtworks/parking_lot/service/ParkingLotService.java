package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingLotRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ParkingLot removeParkingLot(Long parkinglotId) {
        return parkingLotRepository.removeParkingLot(parkinglotId);
    }

    public List<ParkingLot> findAllParkingLots(Integer page, Integer pageSize) {
        return parkingLotRepository.findAllParkingLots(page, pageSize);
    }

    public ParkingLot findParkingLotById(Long parkinglotId) {
        return parkingLotRepository.findParkingLotById(parkinglotId);
    }

    public ParkingLot updateParkingLotById(Long parkinglotId, ParkingLot parkingLot) {
        return parkingLotRepository.updateParkingLotById(parkinglotId, parkingLot);
    }
}
