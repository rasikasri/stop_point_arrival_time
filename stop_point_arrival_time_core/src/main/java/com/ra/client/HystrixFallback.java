package com.ra.client;

import com.ra.entity.StopPointArrival;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class HystrixFallback implements TflApiClient {

    /**
     * Gets list of arrivals at stop point.
     *
     * @param id the stop point id
     * @return the list of arrivals at {@code id}
     */
    public List<StopPointArrival> arrivalsAtStopPoint(String id){
        return new ArrayList<>();
    }
}
