package com.thoughtworks.parking_lot.MVCTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.controller.ParkingLotController;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingLotController.class)
public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ParkingLotService parkingLotService;

    @Test
    public void should_return_parkinglots_when_find_parkinglots_by_page() throws Exception {
        List<ParkingLot> parkingLots = Arrays.asList(
                new ParkingLot("park1", "zha", 10),
                new ParkingLot("park2", "zha", 10)
        );

        when(parkingLotService.findParkingLotsByPage(anyInt())).thenReturn(parkingLots);

        ResultActions result = mvc.perform(get("/parkinglots").param("page", "2"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("park1")))
                .andExpect(jsonPath("$[1].name", is("park2")));
    }

    @Test
    public void should_return_parkinglot_when_find_parkinglot_by_id() throws Exception {
        ParkingLot parkingLot = new ParkingLot("park1", "zha", 10);

        when(parkingLotService.findParkingLotById(anyLong())).thenReturn(parkingLot);

        ResultActions result = mvc.perform(get("/parkinglots/{parkinglotId}", 1));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("park1")))
                .andExpect(jsonPath("$.location", is("zha")));
        verify(parkingLotService).findParkingLotById((long) 1);
    }

    @Test
    public void should_return_parkinglot_when_update_parkinglot_by_id() throws Exception {
        ParkingLot parkingLot = new ParkingLot("park1", "zha", 10);

        when(parkingLotService.updateParkingLotById(anyLong(), any())).thenReturn(parkingLot);

        ResultActions result = mvc.perform(put("/parkinglots", parkingLot.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(parkingLot)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("park1")))
                .andExpect(jsonPath("$.location", is("zha")))
                .andExpect(jsonPath("$.capacity", is(10)));
    }

    @Test
    public void should_return_parkinglot_when_delete_parkinglot_by_id() throws Exception {

        ResultActions result = mvc.perform(delete("/parkinglots/{parkingLotId}", 1));

        result.andExpect(status().isNoContent());
        verify(parkingLotService).removeParkingLot((long) 1);
    }

    @Test
    void should_return_parkinglot_when_add_parkinglot() throws Exception {
        ParkingLot parkingLot = new ParkingLot("park1", "zha", 1);

        when(parkingLotService.addParkingLot(any())).thenReturn(parkingLot);

        ResultActions result = mvc.perform(post("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(parkingLot))
        );

        result.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("park1")))
                .andExpect(jsonPath("$.location", is("zha")))
                .andExpect(jsonPath("$.capacity", is(1)));
    }
}
