package com.ra.mapper;

import com.ra.dto.StopPoint;
import com.ra.entity.StopPointArrival;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StopPointDtoMapperTest {
    @Test
    void testStopPointDtoMapping(){
        StopPoint sp = StopPointDtoMapper.INSTANCE.toDto(StopPointArrival.builder()
            .naptanId("490000091G")
            .stationName("Great Portland Street Station")
            .platformName("G")
            .towards("Edgware Road or Marble Arch")
            .build());

        assertNotNull(sp);
        assertEquals("490000091G", sp.getNaptanId());
        assertEquals("Great Portland Street Station", sp.getStationName());
        assertEquals("G", sp.getPlatformName());
        assertEquals("Edgware Road or Marble Arch", sp.getTowards());
    }
}
