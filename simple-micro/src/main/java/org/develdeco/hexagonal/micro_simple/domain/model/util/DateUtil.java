package org.develdeco.hexagonal.micro_simple.domain.model.util;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateUtil {

    public static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static String formatDate(LocalDate date, DateTimeFormatter formatter) {
        try {
            return date != null ? formatter.format(date) : null;
        } catch (DateTimeException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public static String formatDate(LocalDate date) {
        return formatDate(date, LOCAL_DATE_FORMATTER);
    }

    public static String formatDateTime(LocalDateTime dateTime, DateTimeFormatter formatter) {
        try {
            return dateTime != null ? formatter.format(dateTime.withNano(0)) : null;
        } catch (DateTimeException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime, LOCAL_DATE_TIME_FORMATTER);
    }

    public static LocalDateTime parseToDateTime(String dateTime, DateTimeFormatter formatter) {
        if (dateTime == null || formatter == null) return null;
        try {
            return LocalDateTime.from(formatter.parse(dateTime));
        } catch (DateTimeParseException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public static LocalDateTime parseToDateTime(String dateTime) {
        return parseToDateTime(dateTime, LOCAL_DATE_TIME_FORMATTER);
    }

    public static LocalDate parseToDate(String date, DateTimeFormatter formatter) {
        if (date == null || formatter == null) return null;
        try {
            return LocalDate.from(formatter.parse(date));
        } catch (DateTimeParseException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public static LocalDate parseToDate(String date) {
        return  parseToDate(date, LOCAL_DATE_FORMATTER);
    }
}
