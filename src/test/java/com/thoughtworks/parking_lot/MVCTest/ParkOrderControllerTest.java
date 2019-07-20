package com.thoughtworks.parking_lot.MVCTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.controller.ParkingLotController;
import com.thoughtworks.parking_lot.entity.ParkOrder;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.exception.NotEnoughPositionException;
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
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkOrderControllerTest.class)
public class ParkOrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ParkOrderService parkOrderService;

    @Test
    public void should_create_park_order_when_parked() throws Exception, NotEnoughPositionException {
        ParkingLot parkingLot = new ParkingLot("we","zha", 10);
        ParkOrder parkOrder = new ParkOrder("9527", "park1", parkingLot, new Date(), new Date(), true);

        when(parkOrderService.createOrder(anyLong(), any())).thenReturn(parkOrder);

        ResultActions result = mvc.perform(post("/parkinglots/{parkingLotId}/orders", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(parkOrder)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.carLisence", is("9527")))
                .andExpect(jsonPath("$.parkingLotName", is("park1")));
        verify(parkOrderService).createOrder(1, parkOrder);
    }

    @Test
    public void should_update_park_order_status_when_fetch_car() throws Exception {
        ParkingLot parkingLot = new ParkingLot("we","zha", 10);
        ParkOrder parkOrder = new ParkOrder("9527", "park1", parkingLot, new Date(), new Date(), true);


        when(parkOrderService.updateOrder(anyString())).thenReturn(parkOrder);

        ResultActions result = mvc.perform(post("/parkinglots/{parkingLotId}/orders", 1)
                .param("license", "8848")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(parkOrder)));

        result.andExpect(status().isOk());
        verify(parkOrderService).updateOrder("9527");
    }
}
