package com.ra.client;

import com.ra.entity.StopPointArrival;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "tfl-api", url = "${tflApi.url:}", fallback = HystrixFallback.class)
public interface TflApiClient {

    /**
     * Gets list of arrivals at stop point.
     *
     * @param id the stop point id
     * @return the list of arrivals at {@code id}
     */
    @RequestMapping(
        value = "/StopPoint/{id}/Arrivals",
        method = GET,
        produces = APPLICATION_JSON_VALUE)
    List<StopPointArrival> arrivalsAtStopPoint(@PathVariable("id") String id);
}

