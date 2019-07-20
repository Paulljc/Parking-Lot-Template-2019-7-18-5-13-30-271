package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public void  removeParkingLot(Long parkingLotId) {
        parkingLotRepository.deleteById(parkingLotId);
    }

    public ParkingLot findParkingLotById(Long parkingLotId) {
        return parkingLotRepository.findById(parkingLotId).get();
    }

    public ParkingLot updateParkingLotById(Long parkingLotId, ParkingLot parkingLot) {
        ParkingLot parkingLotDB = parkingLotRepository.findById(parkingLotId).get();
        parkingLotDB.setCapacity(parkingLot.getCapacity());
        return parkingLotRepository.save(parkingLotDB);
    }

    public ParkingLot addParkingLot(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    public List<ParkingLot> findParkingLotsByPage(Integer page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, 15, sort);
        return parkingLotRepository.findAll(pageable).stream().collect(Collectors.toList());
    }
}
