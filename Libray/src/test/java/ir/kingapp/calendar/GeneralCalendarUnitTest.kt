package ir.kingapp.calendar

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Locale

class GeneralCalendarUnitTest {
    @Test
    fun test1() {
        GeneralCalendar.calendarType = CalendarType.PERSIAN
        val gc1 = GeneralCalendar(1400, 5, 9)
        assertEquals("1400-05-09", gc1.format())
        GeneralCalendar.calendarType = CalendarType.Gregorian
        val gc2 = GeneralCalendar(1400, 5, 11)
        assertEquals("2021-07-31", gc1.format())
        GeneralCalendar.calendarType = CalendarType.PERSIAN
        val gc3 = GeneralCalendar(2021, 8, 10)
        val gc4 = GeneralCalendar("2022-10-28T00:00:00.000")
        val gc5 = GeneralCalendar("2022-10-28T15:50:00.010")
        val gc6 = GeneralCalendar("2022-10-28T02:00:00.010")

        assertEquals("1400-05-09", gc1.format())
        assertEquals(2, gc1.untilDays(gc2))
        assertEquals(10, gc1.untilDays(gc3))
        assertEquals("1401-08-06", gc4.format())
        assertEquals("1401-08-06", gc5.format())
        assertEquals("1401-08-06", gc6.format())

        GeneralCalendar.calendarType = CalendarType.Gregorian
        assertEquals("2022-10-28", gc4.format())
        assertEquals("2022-10-28", gc5.format())
        assertEquals("2022-10-28", gc6.format())
        assertEquals("1401-08-06", gc4.toPersianDate().format(PatternDateFormat.SHORT_LINE))
        assertEquals("1401-08-06", gc5.toPersianDate().format(PatternDateFormat.SHORT_LINE))
        assertEquals("1401-08-06", gc6.toPersianDate().format(PatternDateFormat.SHORT_LINE))

        GeneralCalendar.calendarType = CalendarType.PERSIAN
        assertEquals("2022-10-28", gc4.toGregorianDate().format(PatternDateFormat.SHORT_LINE))
        assertEquals("2022-10-28", gc5.toGregorianDate().format(PatternDateFormat.SHORT_LINE))
        assertEquals("2022-10-28", gc6.toGregorianDate().format(PatternDateFormat.SHORT_LINE))
        assertEquals("2021-08-10T00:00:00.000", gc3.getDateForServer())

        GeneralCalendar.calendarType = CalendarType.Gregorian
        assertEquals("2021-08-10T12:00:00.000", gc3.getDateForServer())

        GeneralCalendar.calendarType = CalendarType.PERSIAN
        val date1="2024-07-10T12:30:00.000"
        val persianDate=GeneralCalendar(date1)
        val untilDayP=GeneralCalendar().untilDays(persianDate)
        GeneralCalendar.calendarType = CalendarType.Gregorian
        val gregDate=GeneralCalendar(date1)
        val untilDayG=GeneralCalendar().untilDays(gregDate)
        assertEquals(untilDayP, untilDayG)

    }


    private fun GeneralCalendar.getDateForServer(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
        return dateFormat.format(date)
    }
}