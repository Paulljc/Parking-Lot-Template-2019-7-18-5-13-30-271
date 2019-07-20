package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.entity.ParkOrder;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.exception.NotEnoughPositionException;
import com.thoughtworks.parking_lot.repository.ParkOrderRepository;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ParkOrderService {

    @Autowired
    private ParkOrderRepository parkOrderRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ParkOrder createOrder(long parkingLotId, ParkOrder parkOrder) throws NotEnoughPositionException {
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).get();
        int parkedAmount = parkOrderRepository.countAllByParkingLotNameAndStatus(parkingLot.getName(), true);
        if (parkingLot.getCapacity() > parkedAmount) {
            parkOrder.setParkingLot(parkingLot);
            parkOrder.setParkingLotName(parkingLot.getName());
            parkOrder.setStatus(true);
            return parkOrderRepository.save(parkOrder);
        } else {
            throw new NotEnoughPositionException();
        }
    }

    public ParkOrder updateOrder(String carLicense) throws  Exception{
        ParkOrder parkOrder = parkOrderRepository.findByCarLisenceAndStatus(carLicense, true).get();
        parkOrder.setStatus(false);
        parkOrder.setParkEndAt(new Date());
        return parkOrderRepository.save(parkOrder);
    }
}
