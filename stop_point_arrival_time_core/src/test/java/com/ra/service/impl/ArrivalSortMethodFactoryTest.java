package com.ra.service.impl;

import com.ra.common.ApplicationException;
import com.ra.dto.ArrivalSortType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ArrivalSortMethodFactoryTest {
    private ArrivalSortMethodFactory arrivalSortMethodFactory;

    @BeforeEach
    void setUp(){
        arrivalSortMethodFactory = new ArrivalSortMethodFactory();
    }

    @Test
    void testFactoryMakeSortMethodForAllSortType() throws ApplicationException {
        for(ArrivalSortType type: ArrivalSortType.values()){
            Comparator comparator = arrivalSortMethodFactory.sortMethod(type);
            assertNotNull(comparator);
        }
    }
}
