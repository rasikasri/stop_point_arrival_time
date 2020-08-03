package com.ra.service.impl;

import com.ra.common.ApplicationException;
import com.ra.dto.Arrival;
import com.ra.dto.ArrivalSortType;
import com.ra.service.SortMethodFactory;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component("arrivalSortMethodFactory")
public class ArrivalSortMethodFactory implements SortMethodFactory<Arrival, ArrivalSortType> {

    /**
     * Create comparator to sort arrival collection
     * @param sortBy ArrivalSortType
     * @return
     * @throws ApplicationException
     */
    public Comparator<Arrival> sortMethod(ArrivalSortType sortBy) throws ApplicationException {
        switch (sortBy) {
            case LINE: return Comparator.comparing(Arrival::getLineId);
            case TIME: return Comparator.comparing(Arrival::getTimeToStation);
            case DESTINATION: return Comparator.comparing(Arrival::getDestinationName);
        }
       throw new ApplicationException(null, "Could not create valid sort method");
    }
}
