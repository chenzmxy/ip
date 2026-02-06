package ComSci.util;

import ComSci.exception.ComSciException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * the class to show dates and time
 */
public class DateTimeUtil {
    // input: 2019-12-02 1800
    public static final DateTimeFormatter INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    // output: Dec 02 2019, 6:00PM
    public static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * check the format of user input
     * @param s
     * @return
     */
    public static LocalDateTime parseUserDateTime(String s) {
        try {
            return LocalDateTime.parse(s.trim(), INPUT);
        } catch (DateTimeParseException e) {
            throw new ComSciException(
                    "Bro! Date/time format wrong. Use yyyy-MM-dd HHmm e.g. 2019-12-02 1800"
            );
        }
    }

    public static String formatForDisplay(LocalDateTime dt) {
        return dt.format(OUTPUT);
    }
}

