package com.lgcns.tct_backend.Util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {
    private static final DateTimeFormatter localDateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

    /*
     *  TimeZone : Seoul
     */
    public static LocalDateTime now(){
        return  LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    /*
     * Format : yyyyMMdd
     * Type : String
     */
    public static String getNowWithLocalDateFormat(){
        LocalDateTime now = now();
        return now.format(localDateFormat);
    }
}
