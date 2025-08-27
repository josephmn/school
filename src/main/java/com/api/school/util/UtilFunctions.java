package com.api.school.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * UtilFunctions.
 *
 * @author Joseph Magallanes
 * @since 2024-08-26
 */
public class UtilFunctions {

    /**
     * Get current date in "yyyy-MM-dd" format.
     *
     * @return current date as String
     */
    public static String getCurrentDate() {
        final LocalDateTime now = LocalDateTime.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
