package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.entity.ParkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkOrderRepository extends JpaRepository<ParkOrder, Long> {

    Optional<ParkOrder> findByCarLisenceAndStatus(String license, boolean status);
    int countAllByParkingLotNameAndStatus(String parkingLotName, boolean status);
}
