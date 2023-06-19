package diary.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import diary.annotation.Fast;
import diary.exception.DiaryException;

/**
 * CalendarUtil Test
 * 
 * @author iceman
 * @version 1.0
 */
public class CalendarUtilTest {
    static Calendar calendar;

    /**
     * SetUP().
     */
    @BeforeAll
    public static void setUpTest() {
        calendar = Calendar.getInstance();
        calendar.set(1999, 11, 31); // Month start at 0. So, 11 means December.
    }

    /**
     * strToCalendarTest().
     * 
     * Check convert str to Calendar.
     * If str format is illegal, strToCalendar() throws DiaryException.
     * If date time is not valid, strToCalendar() throws DiaryException.
     */
    @Fast
    @Test
    public void strToCalendarTest() {
        // null check.
        assertThrows(DiaryException.class, () -> CalendarUtil.strToCalendar(""));

        // Convert str to Calendar Test.
        Calendar calendarClone = CalendarUtil.strToCalendar("1999-12-31");
        System.out.println(calendar.get(Calendar.YEAR));
        assertEquals(calendar.get(Calendar.YEAR), calendarClone.get(Calendar.YEAR));
        assertEquals(calendar.get(Calendar.MONTH), calendarClone.get(Calendar.MONTH));
        assertEquals(calendar.get(Calendar.DATE), calendarClone.get(Calendar.DATE));

        // Illegal str format.
        assertThrows(DiaryException.class, () -> CalendarUtil.strToCalendar("1999/12-30"));

        // Not exist date.
        assertThrows(DiaryException.class, () -> CalendarUtil.strToCalendar("1111/22-33"));
    }
}
