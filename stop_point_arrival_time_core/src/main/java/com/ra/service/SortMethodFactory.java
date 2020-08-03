package com.ra.service;

import com.ra.common.ApplicationException;

import java.util.Comparator;

public interface SortMethodFactory<T, S> {
    Comparator<T> sortMethod(S sortBy) throws ApplicationException;
}
