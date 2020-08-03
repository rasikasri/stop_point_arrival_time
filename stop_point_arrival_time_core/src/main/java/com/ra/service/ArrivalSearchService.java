package com.ra.service;

import com.ra.common.ApplicationException;
import com.ra.dto.ArrivalSortType;
import com.ra.dto.StopPoint;

import java.util.List;

public interface ArrivalSearchService {
    /**
     * Search arrivals at each platforms of given stop point id
     * @param stopPointId
     * @return
     */
    List<StopPoint> searchStopPointArrivals(String stopPointId, ArrivalSortType sortBy) throws ApplicationException;
}
