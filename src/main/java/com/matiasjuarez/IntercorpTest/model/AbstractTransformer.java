package com.matiasjuarez.IntercorpTest.model;

import java.time.ZoneId;

public abstract class AbstractTransformer<D, E> {
    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("US/Pacific-New");

    public abstract D convertToDTO(E entity);
    public abstract E convertToEntity(D dto);
}
