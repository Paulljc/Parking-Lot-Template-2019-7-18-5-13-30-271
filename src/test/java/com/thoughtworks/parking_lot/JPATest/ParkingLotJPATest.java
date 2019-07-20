package com.thoughtworks.parking_lot.JPATest;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        ParkingLot parkingLot = new ParkingLot("oocl", "zha", 10);
        ParkingLot parkingLotInDB  = parkingLotRepository.saveAndFlush(parkingLot);

        Assertions.assertEquals(parkingLot, parkingLotInDB);
    }

    @Test
    public void should_return_parkinglot_when_find_parkinglot_by_id() {
        ParkingLot parkingLot = new ParkingLot("oocl", "zha", 10);
        ParkingLot parkingLotInDB = parkingLotRepository.save(parkingLot);

        parkingLotInDB = parkingLotRepository.findById(parkingLotInDB.getId()).get();

        Assertions.assertEquals(parkingLot, parkingLotInDB);
    }

    @Test
    public void should_return_all_parkinglot_when_find_all_parkinglots_by_page() {
        List<ParkingLot> parkingLots = Arrays.asList(
                new ParkingLot("park1", "zha", 10),
                new ParkingLot("park2", "zha", 10)
        );
        parkingLotRepository.saveAll(parkingLots);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(1, 15, sort);
        Page<ParkingLot> parkingLotsInDB = parkingLotRepository.findAll(pageable);

        Assertions.assertEquals(parkingLotsInDB.getTotalElements(), 2);
    }

    @Test
    public void should_return_parkinglot_when_update_parkinglot() {
        ParkingLot parkingLot = new ParkingLot("oocl", "zha", 10);
        parkingLotRepository.saveAndFlush(parkingLot);

        parkingLot.setName("ookl");
        ParkingLot parkingLotInDB = parkingLotRepository.saveAndFlush(parkingLot);
        parkingLotInDB = parkingLotRepository.findById(parkingLotInDB.getId()).get();

        Assertions.assertEquals(parkingLot, parkingLotInDB);
        Assertions.assertEquals(parkingLot.getName(), parkingLotInDB.getName());
    }

    @Test
    public void should_remove_parkinglot_when_delete_parkinglot_by_id() {
        ParkingLot parkingLot1 = new ParkingLot("oocl", "zha", 10);
        ParkingLot parkingLot2 = new ParkingLot("oohl", "zha", 10);
        parkingLotRepository.save(parkingLot1);
        parkingLotRepository.save(parkingLot2);

        parkingLotRepository.deleteById(parkingLot1.getId());

        List<ParkingLot> allCases = parkingLotRepository.findAll();
        Assertions.assertEquals(1, allCases.size());
        Assertions.assertFalse(allCases.contains(parkingLot1));
    }


}
