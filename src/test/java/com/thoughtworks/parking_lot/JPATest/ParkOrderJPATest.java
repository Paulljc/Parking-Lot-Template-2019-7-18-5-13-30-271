package com.thoughtworks.parking_lot.JPATest;

import com.thoughtworks.parking_lot.entity.ParkOrder;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkOrderRepository;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ParkOrderJPATest {
    @Autowired
    private ParkOrderRepository parkOrderRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @BeforeEach
    public void clearDB(){
        parkOrderRepository.deleteAll();
    }

    @Test
    public void should_create_park_order_when_parked() {
        ParkingLot parkingLot = new ParkingLot("oocl", "zha", 10);
        parkingLotRepository.saveAndFlush(parkingLot);
        ParkOrder parkOrder = new ParkOrder("9527","park1", parkingLot, new Date(), new Date(), true);
        ParkOrder parkOrderInDB = parkOrderRepository.saveAndFlush(parkOrder);

        Assertions.assertEquals(parkOrder, parkOrderInDB);
    }

    @Test
    public void should_return_car_when_fetch_car() {
        ParkingLot parkingLot = new ParkingLot("oocl", "zha", 10);
        parkingLotRepository.saveAndFlush(parkingLot);

        ParkOrder parkOrder = new ParkOrder("9527","park1", parkingLot, new Date(), new Date(), true);
        ParkOrder orderInDB = parkOrderRepository.saveAndFlush(parkOrder);

        parkOrder.setStatus(false);
        ParkOrder newOrderInDB = parkOrderRepository.saveAndFlush(parkOrder);

        Assertions.assertFalse(newOrderInDB.getStatus());
    }
}
