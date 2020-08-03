package com.ra.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StopPointArrival {
    private String id;
    private Integer operationType;
    private String vehicleId;
    private String naptanId;
    private String stationName;
    private String lineId;
    private String lineName;
    private String platformName;
    private String direction;
    private String bearing;
    private String destinationNaptanId;
    private String destinationName;
    private LocalDateTime timestamp;
    private Integer timeToStation;
    private String currentLocation;
    private String towards;
    private LocalDateTime expectedArrival;
    private LocalDateTime timeToLive;
    private String modeName;
    private Timing timing;
}
