package com.lidiabazhenova.webapp.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtil {

    private DateUtil() {

    }

    public static Date convertToDate(final LocalDate localDate) {
        if (localDate == null) {
            return null;
        }

        final Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }

    public static LocalDate convertToLocalDate(final Date date) {
        if (date == null) {
            return null;
        }

        return new Date(date.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
