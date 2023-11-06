package com.example.datamanagement.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtility {

    public static String dateTimeFormat(LocalDateTime localDateTime, String pattern){
        //pattern = "dd-MM-yyyy HH:mm"
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    private static boolean compareStringedLocalDateTime(String earlydt, String laterdt, String pattern){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime locald1 = LocalDateTime.parse(earlydt, dateTimeFormatter);
        LocalDateTime locald2 = LocalDateTime.parse(laterdt,dateTimeFormatter);
        return locald2.compareTo(locald1)>0?true:false;
    }

}
