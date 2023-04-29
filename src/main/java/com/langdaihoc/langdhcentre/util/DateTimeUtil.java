package com.langdaihoc.langdhcentre.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

@Slf4j
public class DateTimeUtil {
    public static final ZoneId vnZoneId = ZoneId.of("Asia/Saigon");


    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(vnZoneId)
                .toLocalDate();
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(vnZoneId)
                .toLocalDateTime();
    }

    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date.from(dateToConvert.atZone(vnZoneId)
                .toInstant());
    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(vnZoneId)
                .toInstant());
    }

    public static Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

    ///*****
    public static LocalDateTime convertToLocalDateTimeViaSqlTimestamp(Date dateToConvert) {
        return new java.sql.Timestamp(dateToConvert.getTime()).toLocalDateTime();
    }

    public static LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(vnZoneId)
                .toLocalDateTime();
    }

    public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return LocalDateTime.ofInstant(dateToConvert.toInstant(), vnZoneId);
    }

    public static LocalTime convertSqlTimeToLocalTime(Time time) {
        try {
            return time.toLocalTime();
        } catch (NullPointerException ex){
            log.error("DateTimeUtil - convertSqlTimeToLocalTime : Time is null", ex);
            return null;
        }
        catch (Exception ex){
            log.error("DateTimeUtil - convertSqlTimeToLocalTime", ex);
            return null;
        }
    }

    public static java.sql.Time convertLocalTimeToSqlTime(LocalTime localTime) {
        try {
            return Time.valueOf(localTime);
        } catch (NullPointerException ex){
            log.error("DateTimeUtil - convertSqlTimeToLocalTime : Time is null", ex);
            return null;
        }
        catch (Exception ex){
            log.error("DateTimeUtil - convertSqlTimeToLocalTime", ex);
            return null;
        }
    }


    /**
     * https://vladmihalcea.com/date-timestamp-jpa-hibernate/
     */
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private java.sql.Date parseDate(String date) {
        try {
            return (java.sql.Date) new Date(DATE_FORMAT.parse(date).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private java.sql.Timestamp parseTimestamp(String timestamp) {
        try {
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
