package com.matiasjuarez.IntercorpTest.service;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateHandler {
    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("US/Pacific-New");
    public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public long transformToMillis(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException("localDate can not be null");
        }

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(DEFAULT_ZONE_ID);

        return zonedDateTime.toInstant().toEpochMilli();
    }

    public LocalDate transformToLocalDate(Long timeInMillis) {
        Instant instant = Instant.ofEpochMilli(timeInMillis);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, DEFAULT_ZONE_ID);

        LocalDate localDate = LocalDate.of(zonedDateTime.getYear(), zonedDateTime.getMonth(), zonedDateTime.getDayOfMonth());

        return localDate;
    }

    public LocalDate transformToLocalDate(String dateString) {
        return LocalDate.from(DEFAULT_DATE_TIME_FORMATTER.parse(dateString));
    }

    public String transformToFormattedString(LocalDate localDate) {
        return localDate.format(DEFAULT_DATE_TIME_FORMATTER);
    }

    public String transformToFormattedString(Long timeInMillis) {
        return transformToFormattedString(transformToLocalDate(timeInMillis));
    }
}
