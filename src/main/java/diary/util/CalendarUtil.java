package diary.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import diary.exception.DiaryException;

/**
 * Calendar Utility Class.
 * 
 * @author iceman
 * @version 1.0
 */
public class CalendarUtil {
    public static Calendar strToCalendar(String str) throws DiaryException {
        Calendar calendar = Calendar.getInstance();
        if (str == null) {
            throw new DiaryException("input is Null.");
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = df.parse(str);
            calendar.setTime(date);
        } catch (ParseException e) {
            throw new DiaryException("Parse failed. Not valid value.", e);
        }
        return calendar;
    }

}
