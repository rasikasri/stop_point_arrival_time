package com.ra.mapper;

import com.ra.dto.StopPoint;
import com.ra.entity.StopPointArrival;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StopPointDtoMapper {
    /**
     * The constant INSTANCE.
     */
    StopPointDtoMapper INSTANCE = Mappers.getMapper(StopPointDtoMapper.class);


    StopPoint toDto(StopPointArrival stopPointArrival);

    List<StopPoint> toDtoList(List<StopPointArrival> stopPointArrivals);
}
