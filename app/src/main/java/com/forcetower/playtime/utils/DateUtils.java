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

    public static String monthFromInt(int i) {
        switch (i) {
            case 1:
                return "Janeiro";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março";
            case 4:
                return "Abril";
            case 5:
                return "Maio";
            case 6:
                return "Junho";
            case 7:
                return "Julho";
            case 8:
                return "Agosto";
            case 9:
                return "Setembro";
            case 10:
                return "Outubro";
            case 11:
                return "Novembro";
            case 12:
                return "Dezembro";
        }
        return "Desconhecido";
    }
}
