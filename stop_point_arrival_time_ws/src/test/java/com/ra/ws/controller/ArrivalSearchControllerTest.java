package com.ra.ws.controller;

import com.ra.common.ApplicationException;
import com.ra.common.ExceptionMessage;
import com.ra.dto.Arrival;
import com.ra.dto.ArrivalSortType;
import com.ra.dto.StopPoint;
import com.ra.service.ArrivalSearchService;
import org.assertj.core.util.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArrivalSearchController.class)
class ArrivalSearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArrivalSearchService arrivalSearchService;

    @Test
    void testDefaultResponse() throws Exception {
        mockMvc.perform(get("/arrivals/940GZZLUKBY"))
            .andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_VALUE));
    }

    @Test
    void testParameterValidation() throws Exception {
        mockMvc.perform(get("/arrivals/940GZZLUKBY").params(new LinkedMultiValueMap<String, String>() {{
            add("sortBy", "YYY");
        }}))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(ExceptionMessage.INVALID_PARAMETER.getMessage()));
    }

    @Test
    void testApplicationException() throws Exception {
        when(arrivalSearchService.searchStopPointArrivals("940GZZLUKBY", ArrivalSortType.TIME))
            .thenThrow(new ApplicationException(null, "Application exception"));

        mockMvc.perform(get("/arrivals/940GZZLUKBY"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(ExceptionMessage.APPLICATION_EXCEPTION.getMessage()));
    }

    @Test
    void testDefaultParameterResponse() throws Exception {
        when(arrivalSearchService.searchStopPointArrivals("940GZZLUKBY", ArrivalSortType.TIME))
            .thenReturn(new ArrayList<StopPoint>(){
            {
                add(StopPoint.builder().naptanId("940GZZLUKBY").platformName("KJ").build());
            }
        });
        mockMvc.perform(get("/arrivals/940GZZLUKBY"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$[0].naptanId", Matchers.is("940GZZLUKBY")))
            .andExpect(jsonPath("$[0].platformName", Matchers.is("KJ")));
    }

    @Test
    void testSearchResponse() throws Exception {
        when(arrivalSearchService.searchStopPointArrivals("940GZZLUKBY", ArrivalSortType.TIME))
            .thenReturn(Lists.newArrayList(
                StopPoint.builder()
                    .naptanId("940GZZLUKBY")
                    .platformName("KJ")
                    .stationName("Kingsbury")
                    .towards("Stanmore")
                    .arrivals(Lists.newArrayList(Arrival.builder()
                        .lineId("423")
                        .timeToStation(120)
                        .destinationName("Sudbury")
                        .build()))
                    .build(),
                StopPoint.builder()
                    .naptanId("940GZZLUKBY")
                    .platformName("D")
                    .stationName("Kingsbury")
                    .towards("Stanmore")
                    .arrivals(Lists.newArrayList(Arrival.builder()
                        .lineId("423")
                        .timeToStation(320)
                        .destinationName("Sudbury")
                        .build()))
                    .build()
            ));
        mockMvc.perform(get("/arrivals/940GZZLUKBY"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$[0].naptanId", Matchers.is("940GZZLUKBY")))
            .andExpect(jsonPath("$[0].platformName", Matchers.is("KJ")))
            .andExpect(jsonPath("$[0].stationName", Matchers.is("Kingsbury")))
            .andExpect(jsonPath("$[0].towards", Matchers.is("Stanmore")))
            .andExpect(jsonPath("$[0].arrivals[0].lineId", Matchers.is("423")))
            .andExpect(jsonPath("$[0].arrivals[0].timeToStation", Matchers.is(120)))
            .andExpect(jsonPath("$[0].arrivals[0].destinationName", Matchers.is("Sudbury")))
            .andExpect(jsonPath("$[1].naptanId", Matchers.is("940GZZLUKBY")))
            .andExpect(jsonPath("$[1].platformName", Matchers.is("D")))
            .andExpect(jsonPath("$[1].stationName", Matchers.is("Kingsbury")))
            .andExpect(jsonPath("$[1].towards", Matchers.is("Stanmore")))
            .andExpect(jsonPath("$[1].arrivals[0].lineId", Matchers.is("423")))
            .andExpect(jsonPath("$[1].arrivals[0].timeToStation", Matchers.is(320)))
            .andExpect(jsonPath("$[1].arrivals[0].destinationName", Matchers.is("Sudbury")));
    }


}
