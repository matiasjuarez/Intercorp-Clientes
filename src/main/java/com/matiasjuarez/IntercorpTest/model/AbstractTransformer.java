package com.matiasjuarez.IntercorpTest.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public abstract class AbstractTransformer<D, E> {
    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("US/Pacific-New");

    public abstract D convertToDTO(E entity);
    public abstract E convertToEntity(D dto);

    public static long transformToMillis(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException("localDate can not be null");
        }

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(DEFAULT_ZONE_ID);

        return zonedDateTime.toInstant().toEpochMilli();
    }

    public static LocalDate transformToLocalDate(Long timeInMillis) {
        Instant instant = Instant.ofEpochMilli(timeInMillis);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, DEFAULT_ZONE_ID);

        LocalDate localDate = LocalDate.of(zonedDateTime.getYear(), zonedDateTime.getMonth(), zonedDateTime.getDayOfMonth());

        return localDate;
    }
}
