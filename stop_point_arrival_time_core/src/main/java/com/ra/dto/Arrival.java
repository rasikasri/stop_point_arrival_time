package com.ra.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class Arrival implements Serializable {
    private String id;
    private Integer operationType;
    private String vehicleId;
    private String lineId;
    private String lineName;
    private String direction;
    private String bearing;
    private String destinationNaptanId;
    private String destinationName;
    private LocalDateTime timestamp;
    private Integer timeToStation;
    private String currentLocation;
    private LocalDateTime expectedArrival;
    private LocalDateTime timeToLive;
    private String modeName;
}
