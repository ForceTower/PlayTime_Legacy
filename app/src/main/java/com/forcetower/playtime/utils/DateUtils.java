package com.forcetower.playtime.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    /**
     * Formats timestamp to 'date month year' format (e. g. '25/04/2018).
     * @param timeInMillis time to format in millis
     * @return String with formatted time
     */
    public static String formatFullDate(long timeInMillis) {
        Date date = new Date(timeInMillis);
        Format format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return format.format(date);
    }
}
