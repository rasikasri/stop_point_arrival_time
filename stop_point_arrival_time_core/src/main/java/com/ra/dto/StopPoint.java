package com.ra.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StopPoint {
    @EqualsAndHashCode.Include
    private String naptanId;
    @EqualsAndHashCode.Include
    private String platformName;
    private String stationName;
    private String towards;
    private List<Arrival> arrivals;
}
