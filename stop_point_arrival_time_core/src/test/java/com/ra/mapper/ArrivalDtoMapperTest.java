package com.ra.mapper;

import com.ra.dto.Arrival;
import com.ra.entity.StopPointArrival;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ArrivalDtoMapperTest {
//        "naptanId": "490000091G",
//        "stationName": "Great Portland Street Station",
//        "platformName": "G",
//        "towards": "Edgware Road or Marble Arch",
    @Test
    void testArrivalDtoMapping(){
        Arrival arrival = ArrivalDtoMapper.INSTANCE.toDto(StopPointArrival.builder()
            .id("1223")
            .vehicleId("LV99ERT")
            .destinationName("Waterloo")
            .bearing("170")
            .lineId("423")
            .operationType(1)
            .lineName("423")
            .direction("inbound")
            .destinationNaptanId("")
            .currentLocation("")
            .timestamp(LocalDateTime.of(2020,8,02,8,25))
            .timeToStation(120)
            .modeName("Bus")
            .expectedArrival(LocalDateTime.of(2020,8,02,8,25))
            .timeToLive(LocalDateTime.of(2020,8,02,8,25))
            .build());

        assertNotNull(arrival);
        assertEquals("1223", arrival.getId());
        assertEquals("LV99ERT", arrival.getVehicleId());
        assertEquals("Waterloo", arrival.getDestinationName());
        assertEquals("170", arrival.getBearing());
        assertEquals("423", arrival.getLineId());
        assertEquals("", arrival.getCurrentLocation());
        assertEquals(1, arrival.getOperationType());
        assertEquals("423", arrival.getLineName());
        assertEquals("inbound", arrival.getDirection());
        assertEquals("", arrival.getDestinationNaptanId());
        assertEquals(LocalDateTime.of(2020,8,02,8,25), arrival.getTimestamp());
        assertEquals(120, arrival.getTimeToStation());
        assertEquals("Bus", arrival.getModeName());
        assertEquals(LocalDateTime.of(2020,8,02,8,25), arrival.getExpectedArrival());
        assertEquals(LocalDateTime.of(2020,8,02,8,25), arrival.getTimeToLive());
    }
}
