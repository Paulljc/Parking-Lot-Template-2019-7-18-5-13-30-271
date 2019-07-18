package com.thoughtworks.parking_lot.JPATest;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ParkingLotJPATest {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @BeforeEach
    public void clearDB(){
        parkingLotRepository.deleteAll();
    }

    @Test
    public void should_return_parkinglot_when_add_parkinglot() {
        ParkingLot parkingLot = new ParkingLot("oocl", "zha");
        ParkingLot parkingLotInDB  = parkingLotRepository.saveAndFlush(parkingLot);

        Assertions.assertEquals(parkingLot, parkingLotInDB);
    }

    @Test
    public void should_return_parkinglot_when_find_parkinglot_by_id() {
        ParkingLot parkingLot = new ParkingLot("oocl", "zha");

        ParkingLot parkingLotInDB = parkingLotRepository.saveAndFlush(parkingLot);
        parkingLotInDB = parkingLotRepository.findById(parkingLotInDB.getId()).get();

        Assertions.assertEquals(parkingLot, parkingLotInDB);
    }

    @Test
    public void should_return_all_parkinglot_when_find_all_parkinglots() {
        List<ParkingLot> parkingLots = Arrays.asList(
                new ParkingLot("park1", "zha"),
                new ParkingLot("park2", "zha")
        );

        parkingLotRepository.saveAll(parkingLots);

        List<ParkingLot> parkingLotsInDB = parkingLotRepository.findAll();

        Assertions.assertEquals(parkingLots.get(0), parkingLotsInDB.get(0));
        Assertions.assertEquals(parkingLots.get(1), parkingLotsInDB.get(1));
    }

    @Test
    public void should_return_parkinglot_when_update_parkinglot() {
        ParkingLot parkingLot = new ParkingLot("oocl", "zha");
        parkingLotRepository.saveAndFlush(parkingLot);

        parkingLot.setName("ookl");
        ParkingLot parkingLotInDB = parkingLotRepository.saveAndFlush(parkingLot);
        parkingLotInDB = parkingLotRepository.findById(parkingLotInDB.getId()).get();

        Assertions.assertEquals(parkingLot, parkingLotInDB);
        Assertions.assertEquals(parkingLot.getName(), parkingLotInDB.getName());
    }

    @Test
    public void should_return_parkinglot_when_delete_parkinglot_by_id() {
        ParkingLot parkingLot1 = new ParkingLot("oocl", "zha");
        ParkingLot parkingLot2 = new ParkingLot("oohl", "zha");
        parkingLotRepository.save(parkingLot1);
        parkingLotRepository.save(parkingLot2);

        parkingLotRepository.deleteById(parkingLot1.getId());;

        List<ParkingLot> allCases = parkingLotRepository.findAll();
        Assertions.assertEquals(1, allCases.size());
        Assertions.assertFalse(allCases.contains(parkingLot1));
    }


}
