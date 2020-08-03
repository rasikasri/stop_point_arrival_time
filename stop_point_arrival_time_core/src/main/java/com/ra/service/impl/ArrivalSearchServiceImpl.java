package com.ra.service.impl;

import com.ra.client.TflApiClient;
import com.ra.common.ApplicationException;
import com.ra.dto.Arrival;
import com.ra.dto.ArrivalSortType;
import com.ra.dto.StopPoint;
import com.ra.entity.StopPointArrival;
import com.ra.mapper.ArrivalDtoMapper;
import com.ra.mapper.StopPointDtoMapper;
import com.ra.service.ArrivalSearchService;
import com.ra.service.SortMethodFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArrivalSearchServiceImpl implements ArrivalSearchService {

    private final TflApiClient tflApiClient;

    private final SortMethodFactory arrivalSortMethodFactory;

    @Override
    public List<StopPoint> searchStopPointArrivals(String stopPointId, ArrivalSortType sortBy) throws ApplicationException {
        List<StopPointArrival> stopPointArrivals = tflApiClient.arrivalsAtStopPoint(stopPointId);
        log.info("Number of arrivals at {} is {}", stopPointId, stopPointArrivals.size());

        //Extract list of stop points
        List<StopPoint> stopPoints = StopPointDtoMapper.INSTANCE.toDtoList(stopPointArrivals);
        //Get distinct stop points base on id and platform
        stopPoints = stopPoints.stream().distinct().collect(Collectors.toList());

        //Comparator to sort arrivals
        Comparator<Arrival> sortComparator = arrivalSortMethodFactory.sortMethod(sortBy);

        //Group arrivals into each stop point
        stopPoints.forEach((stopPoint) ->
        {
            //Find arrivals of current stop point
            List<Arrival> arrivals = ArrivalDtoMapper.INSTANCE.toDtoList(
                stopPointArrivals.stream()
                    .filter(x -> x.getNaptanId().equals(stopPoint.getNaptanId())
                        && x.getPlatformName().equals(stopPoint.getPlatformName()))
                    .collect(Collectors.toList())
            );

            arrivals.sort(sortComparator);
            stopPoint.setArrivals(arrivals);
        });

        return stopPoints;
    }
}
