package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long>{

    ParkingLot removeParkingLot(Long parkinglotId);

    List<ParkingLot> findAllParkingLots();

    ParkingLot findParkingLotById(Long parkinglotId);

    ParkingLot updateParkingLotById(Long parkinglotId, ParkingLot parkingLot);

    ParkingLot addParkingLot(ParkingLot parkingLot);

    List<ParkingLot> listParkingLotsByPage(Integer page, Integer pageSize);
}
