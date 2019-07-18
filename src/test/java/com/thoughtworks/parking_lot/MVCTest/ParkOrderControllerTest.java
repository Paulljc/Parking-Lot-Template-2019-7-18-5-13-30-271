package com.thoughtworks.parking_lot.MVCTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.controller.ParkingLotController;
import com.thoughtworks.parking_lot.entity.Car;
import com.thoughtworks.parking_lot.entity.ParkOrder;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingLotController.class)
public class ParkOrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ParkOrderService parkOrderService;

    @Test
    public void should_return_order_when_park_the_car() throws Exception {
        Car car = new Car("9527");
        ParkingLot parkingLot = new ParkingLot("we","zha", 10);
        ParkOrder order = new ParkOrder(car, parkingLot, "88", new Date(), null, true);
        String json = new ObjectMapper().writeValueAsString(car);

        when(parkOrderService.parkCar(ArgumentMatchers.any())).thenReturn(order);

        ResultActions result = mvc.perform(post("/orders/parkCar").contentType(MediaType.APPLICATION_JSON).content(json));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.orderNumber", is("88")))
                .andExpect(jsonPath("$.orderStatus", is(true)));
    }

    @Test
    public void should_return_car_when_fetch_the_car() throws Exception {
        Car car = new Car("9527");
        ParkingLot parkingLot = new ParkingLot("we","zha", 10);
        ParkOrder order = new ParkOrder(car, parkingLot, "88", new Date(), null, true);
        String json = new ObjectMapper().writeValueAsString(order);

        when(parkOrderService.fetchCar(ArgumentMatchers.any())).thenReturn(car);

        ResultActions result = mvc.perform(post("/orders/fetchCar").contentType(MediaType.APPLICATION_JSON).content(json));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.carLisence", is("9527")));
    }
}
