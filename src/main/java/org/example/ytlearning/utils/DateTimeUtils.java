package org.example.ytlearning.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

@Slf4j
public class DateTimeUtils {
    private DateTimeUtils() {
    }

    public static Date convertStringToDate(String dateOfBirth) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(dateOfBirth);
        } catch (Exception e) {
            log.error("Error parsing date: {}", dateOfBirth, e);
        }
        return new Date();
    }

    public static LocalTime convertStringTimeToLocalTime(String strTime) {
        if (strTime == null) {
            return null;
        }

        String value = strTime.trim();

        DateTimeFormatter twelveHour = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("h:mm[ ]a") // allows both "11:30 AM" and "11:30AM"
                .toFormatter(Locale.ENGLISH);

        DateTimeFormatter twentyFourHour = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("H:mm")     // allows "0:00" to "23:59"
                .toFormatter(Locale.getDefault());

        try {
            return LocalTime.parse(value, twelveHour);
        } catch (DateTimeParseException ignore) {
            // try next formatter
        }

        try {
            return LocalTime.parse(value, twentyFourHour);
        } catch (DateTimeParseException e) {
            log.error("Error parsing time '{}'. Expected formats like '11:30 AM', '11:30AM' or '23:30'", value, e);
            throw new IllegalArgumentException("Invalid time: " + value, e);
        }
    }


}
