package com.thoughtworks.parking_lot.JPATest;

import com.thoughtworks.parking_lot.entity.Car;
import com.thoughtworks.parking_lot.entity.ParkOrder;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkOrderRepository;
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

    @BeforeEach
    public void clearDB(){
        parkOrderRepository.deleteAll();
    }

    @Test
    public void should_return_order_when_park_car() {
        Car car = new Car("9527");
        parkOrderRepository.saveAndFlush(car);
        ParkingLot parkingLot = new ParkingLot("oocl", "zha", 10);
        parkOrderRepository.saveAndFlush(parkingLot);
        ParkOrder order = new ParkOrder(car, parkingLot, "888", new Date(), new Date(), true);
        ParkOrder orderInDB = parkOrderRepository.saveAndFlush(order);

        Assertions.assertEquals(order, orderInDB);
    }

    @Test
    public void should_return_car_when_fetch_car() {
        Car car = new Car("9527");
        parkOrderRepository.saveAndFlush(car);
        ParkingLot parkingLot = new ParkingLot("oocl", "zha", 10);
        parkOrderRepository.saveAndFlush(parkingLot);
        ParkOrder order = new ParkOrder(car, parkingLot, "888", new Date(), new Date(), true);
        ParkOrder orderInDB = parkOrderRepository.saveAndFlush(order);
        order.setOrderStatus(false);
        parkOrderRepository.saveAndFlush(order);

        Assertions.assertFalse(orderInDB.getOrderStatus());
    }
}
