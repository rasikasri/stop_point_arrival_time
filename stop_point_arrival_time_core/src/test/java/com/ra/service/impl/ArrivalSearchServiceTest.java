package com.ra.service.impl;

import com.ra.client.TflApiClient;
import com.ra.common.ApplicationException;
import com.ra.dto.Arrival;
import com.ra.dto.ArrivalSortType;
import com.ra.dto.StopPoint;
import com.ra.entity.StopPointArrival;
import com.ra.service.ArrivalSearchService;
import com.ra.service.SortMethodFactory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArrivalSearchServiceTest {

    private ArrivalSearchService arrivalSearchService;

    @Mock
    private TflApiClient tflApiClient;

    @Mock
    private SortMethodFactory sortMethodFactory;

    @BeforeEach
    void setUp(){
        arrivalSearchService = new ArrivalSearchServiceImpl(tflApiClient, sortMethodFactory);
    }

    @Test
    void testNullResponseHandle() throws ApplicationException {
        assertNotNull(arrivalSearchService.searchStopPointArrivals("Test_Naptan", ArrivalSortType.TIME));
    }

    @Test
    void testReturnUniqueStopPoints() throws ApplicationException {
        when(tflApiClient.arrivalsAtStopPoint(anyString()))
            .thenReturn(Lists.list(
                StopPointArrival.builder().naptanId("Naptan1").platformName("AD").vehicleId("LF66EWC").build(),
                StopPointArrival.builder().naptanId("Naptan1").platformName("XY").vehicleId("LF67EWC").build(),
                StopPointArrival.builder().naptanId("Naptan1").platformName("XY").vehicleId("LF68EWC").build(),
                StopPointArrival.builder().naptanId("Naptan2").platformName("XY").vehicleId("LF69EWC").build()
            ));

        when(sortMethodFactory.sortMethod(any(ArrivalSortType.class))).thenReturn(Comparator.comparing(Arrival::getVehicleId));

        List<StopPoint> stopPoints = arrivalSearchService.searchStopPointArrivals("Test_Naptan", ArrivalSortType.TIME);
        assertNotNull(stopPoints);
        assertEquals(3, stopPoints.size(), "Stop points should be unique");

        List<StopPoint> uniqueSet = Lists.newArrayList(
            StopPoint.builder().naptanId("Naptan1").platformName("AD").build(),
            StopPoint.builder().naptanId("Naptan1").platformName("XY").build(),
            StopPoint.builder().naptanId("Naptan2").platformName("XY").build());
        assertIterableEquals(uniqueSet,stopPoints, "Must return all unique stopPoints");
    }

    @Test
    void testArrivalsGroupedIntoStopPoints() throws ApplicationException {
        when(tflApiClient.arrivalsAtStopPoint(anyString()))
            .thenReturn(Lists.list(
                StopPointArrival.builder().naptanId("Naptan1").platformName("AD").vehicleId("LF67EWC").build(),
                StopPointArrival.builder().naptanId("Naptan1").platformName("AD").vehicleId("LF67EUP").build(),
                StopPointArrival.builder().naptanId("Naptan1").platformName("XY").vehicleId("LV64ETA").build(),
                StopPointArrival.builder().naptanId("Naptan1").platformName("XY").vehicleId("VX09GGT").build(),
                StopPointArrival.builder().naptanId("Naptan2").platformName("XY").vehicleId("ZZ67XML").build()
            ));

        when(sortMethodFactory.sortMethod(any(ArrivalSortType.class))).thenReturn(Comparator.comparing(Arrival::getVehicleId));

        List<StopPoint> stopPoints = arrivalSearchService.searchStopPointArrivals("Test_Naptan", ArrivalSortType.TIME);
        assertNotNull(stopPoints);
        assertEquals(3, stopPoints.size(), "Stop points should be unique");
        assertArrayEquals(new String[]{"LF67EUP", "LF67EWC"},
            stopPoints.get(0).getArrivals().stream().map(x -> x.getVehicleId()).collect(Collectors.toList()).toArray());
        assertArrayEquals(new String[]{"LV64ETA", "VX09GGT"},
            stopPoints.get(1).getArrivals().stream().map(x -> x.getVehicleId()).collect(Collectors.toList()).toArray());
        assertArrayEquals(new String[]{"ZZ67XML"},
            stopPoints.get(2).getArrivals().stream().map(x -> x.getVehicleId()).collect(Collectors.toList()).toArray());
    }

    @Test
    void testArrivalSort() throws ApplicationException {
        when(tflApiClient.arrivalsAtStopPoint(anyString()))
            .thenReturn(Lists.list(
                StopPointArrival.builder().naptanId("Naptan1").platformName("AD").vehicleId("LF67EWC").timeToStation(320).build(),
                StopPointArrival.builder().naptanId("Naptan1").platformName("AD").vehicleId("LF67EUP").timeToStation(200).build(),
                StopPointArrival.builder().naptanId("Naptan1").platformName("AD").vehicleId("LV64ETA").timeToStation(120).build(),
                StopPointArrival.builder().naptanId("Naptan2").platformName("XY").vehicleId("VX09GGT").timeToStation(600).build(),
                StopPointArrival.builder().naptanId("Naptan2").platformName("XY").vehicleId("ZZ67XML").timeToStation(140).build()
            ));

        when(sortMethodFactory.sortMethod(ArrivalSortType.TIME)).thenReturn(Comparator.comparing(Arrival::getTimeToStation));

        List<StopPoint> stopPoints = arrivalSearchService.searchStopPointArrivals(anyString(), ArrivalSortType.TIME);
        assertNotNull(stopPoints);
        assertEquals(2, stopPoints.size(), "Stop points should be unique");
        assertEquals("LV64ETA",stopPoints.get(0).getArrivals().get(0).getVehicleId());
        assertEquals("LF67EUP",stopPoints.get(0).getArrivals().get(1).getVehicleId());
        assertEquals("LF67EWC",stopPoints.get(0).getArrivals().get(2).getVehicleId());
        assertEquals("ZZ67XML",stopPoints.get(1).getArrivals().get(0).getVehicleId());
        assertEquals("VX09GGT",stopPoints.get(1).getArrivals().get(1).getVehicleId());
    }

}
