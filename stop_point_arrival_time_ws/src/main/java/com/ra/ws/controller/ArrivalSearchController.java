package com.ra.ws.controller;

import com.ra.common.ApplicationException;
import com.ra.dto.ArrivalSortType;
import com.ra.dto.StopPoint;
import com.ra.service.ArrivalSearchService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ArrivalSearchController {

    @Autowired
    private ArrivalSearchService arrivalSearchService;

    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @ApiOperation(
        value = "Stop points with list of buses due to arrive",
        notes = "This end point returns list of stop points alone with list of buses due to arrive for given stop point parameter",
        response = String.class,
        responseContainer = "List")
    @RequestMapping( value = "/arrivals/{stopPointId}",
        produces = APPLICATION_JSON_VALUE,
        method = GET)
    public ResponseEntity<List<StopPoint>> listStops(@PathVariable("stopPointId") String stopPointId,
                                                     @RequestParam(value = "sortBy", required = false, defaultValue = "TIME") ArrivalSortType sortBy)
        throws ApplicationException {

        List<StopPoint> results = arrivalSearchService.searchStopPointArrivals(stopPointId, sortBy);

        if (results != null) {
            log.info("Number of stop points in id {} are {}", stopPointId, results.size());
            return new ResponseEntity<>(results, HttpStatus.OK);
        }
        return ResponseEntity.ok(new ArrayList<>());

    }

}
