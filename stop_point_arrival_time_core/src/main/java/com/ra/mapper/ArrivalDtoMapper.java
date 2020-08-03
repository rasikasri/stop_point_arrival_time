package com.ra.mapper;

import com.ra.dto.Arrival;
import com.ra.entity.StopPointArrival;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ArrivalDtoMapper {
    /**
     * The constant INSTANCE.
     */
    ArrivalDtoMapper INSTANCE = Mappers.getMapper(ArrivalDtoMapper.class);

    Arrival toDto(StopPointArrival stopPointArrival);

    List<Arrival> toDtoList(List<StopPointArrival> stopPointArrivals);
}
