package ir.kingapp.calendar

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date


class PersianDateUnitTest {
    @Test
    fun test1() {
        val date1 = PersianDate(1402, 2, 1)
        val date2 = PersianDate("1402-02-01T01:59:22.123Z")
        val date3 = PersianDate("1399-06-12T23:59:2")
        val formatter = SimpleDateFormat("yyyy/MM/dd")
        val date5 = PersianDate(formatter.format(Date()))
        val date6 = PersianDate()
        val date7 = PersianDate.of("2500/1/12")
        val date8 = PersianDate.of("test")
        val date9 = PersianDate.of("1401/4/1")
        val date10 = PersianDate.of("1401/04/01")
        val date11 = PersianDate.of("1401.04.01")
        val date12 = PersianDate.of("1402 04 21")
        val date13 = PersianDate.of("2023-05-02")
        val date14 = PersianDate.of("2023-01-01")

        val date15 = GregorianDate("2023-05-02").toPersianDate()
        val date16 = GregorianDate("2023-01-01").toPersianDate()
        val date17 = GeneralCalendar("2023-01-01").toPersianDate()

        assertEquals("1402-02-01", date1.format(PatternDateFormat.SHORT_LINE))
        assertEquals("1402-02-01", date2.format(PatternDateFormat.SHORT_LINE))
        assertEquals("1399-06-12", date3.format(PatternDateFormat.SHORT_LINE))
        assertEquals("1401-04-01", date11?.format(PatternDateFormat.SHORT_LINE))
        assertEquals("1402-04-21", date12?.format(PatternDateFormat.SHORT_LINE))
        assertEquals("1402-02-12", date13?.format(PatternDateFormat.SHORT_LINE))
        assertEquals("1401-10-11", date14?.format(PatternDateFormat.SHORT_LINE))
        assertEquals("1402-02-12", date15.format(PatternDateFormat.SHORT_LINE))
        assertEquals("1401-10-11", date16.format(PatternDateFormat.SHORT_LINE))
        assertEquals("1401-10-11", date17.format(PatternDateFormat.SHORT_LINE))
        assertEquals("1401-04-01", date9?.format(PatternDateFormat.SHORT_LINE))
        assertTrue(date5 == date6)
        assertNull(date7)
        assertNull(date8)
        assertNotNull(date10)
    }

    @Test
    fun equalTest1() {
        val date1 = PersianDate()
        val date2 = PersianDate()
        val date3 = PersianDate(1402, 2, 17)
        val date4 = PersianDate(1402, 2, 17)
        val date7 = PersianDate("1402-05-02")
        val date8 = PersianDate("1402-05-02")
        val date9 = PersianDate("1402/03/30")
        val date10 = PersianDate("1402/03/30")
        val date11 = PersianDate("2023/04/12")
        val date12 = PersianDate("2023/04/12")
        val date13 = PersianDate("2024/04/12")
        val date14 = PersianDate("2023/04/12")

        assertTrue(date1 == date2)
        assertTrue(date3 == date4)
        assertTrue(date7 == date8)
        assertTrue(date9 == date10)
        assertTrue(date11 == date12)
        assertTrue(date11 <= date12)
        assertTrue(date11 >= date12)
        assertTrue(date13 > date14)
        assertFalse(date13 < date14)
    }


    @Test
    fun addTest() {
        val date1 = PersianDate(1402, 12, 5).addDay(5)
        val date2 = PersianDate(1402, 12, 28).addDay(2)
        val date3 = PersianDate(1399, 12, 28).addDay(2)
        val date4 = PersianDate(1399, 12, 28).addDay(-22)

        val date5 = PersianDate(1402, 12, 5).addMonth(1)
        val date6 = PersianDate(1402, 12, 28).addMonth(-1)
        val date7 = PersianDate(1399, 12, 28).addMonth(-8)

        val date8 = PersianDate(1402, 12, 5).addYear(1)
        val date9 = PersianDate(1402, 12, 28).addYear(-1)
        val date10 = PersianDate(1399, 12, 28).addYear(4)

        val date11 = PersianDate(1402, 1, 5).addMonth(1)
        val date12 = PersianDate(1402, 1, 28).addMonth(-1)

        val date13 = PersianDate(1402, 1, 10)
            .addDay(5)
            .addMonth(-1)
            .addYear(1)


        assertTrue(date1.format(PatternDateFormat.SHORT_LINE) == "1402-12-10")
        assertTrue(date2.format(PatternDateFormat.SHORT_LINE) == "1403-01-01")
        assertTrue(date3.format(PatternDateFormat.SHORT_LINE) == "1399-12-30")
        assertTrue(date4.format(PatternDateFormat.SHORT_LINE) == "1399-12-06")
        assertTrue(date5.format(PatternDateFormat.SHORT_LINE) == "1403-01-05")
        assertTrue(date6.format(PatternDateFormat.SHORT_LINE) == "1402-11-28")
        assertTrue(date7.format(PatternDateFormat.SHORT_LINE) == "1399-04-28")
        assertTrue(date8.format(PatternDateFormat.SHORT_LINE) == "1403-12-05")
        assertTrue(date9.format(PatternDateFormat.SHORT_LINE) == "1401-12-28")
        assertTrue(date10.format(PatternDateFormat.SHORT_LINE) == "1403-12-28")
        assertTrue(date11.format(PatternDateFormat.SHORT_LINE) == "1402-02-05")
        assertTrue(date12.format(PatternDateFormat.SHORT_LINE) == "1401-12-28")
        assertTrue(date13.format(PatternDateFormat.SHORT_LINE) == "1402-12-15")
    }

    @Test
    fun testOtherFunction() {
        val date1 = PersianDate(1400, 1, 1)
        val date2 = PersianDate(1402, 7, 1)
        val date3 = PersianDate(1401, 12, 1)
        val date4 = PersianDate(1399, 12, 1)
        val date5 = PersianDate(1399, 12, 17).goStartMonth()
        val date6 = date5.copy().goEndMonth()
        val date7 = PersianDate(1399, 1, 1)
        val date8 = PersianDate(1400, 1, 1)
        val date9 = PersianDate(1400, 1, 1)
        val date10 = PersianDate(1400, 1, 10)
        val date11 = PersianDate(1400, 1, 11)


        assertTrue(date1.lengthOfMonth == 31)
        assertTrue(date2.lengthOfMonth == 30)
        assertTrue(date3.lengthOfMonth == 29)
        assertTrue(date4.lengthOfMonth == 30)
        assertTrue(date2.day == 1)
        assertTrue(date2.month == 7)
        assertTrue(date2.year == 1402)
        assertTrue(date5.format(PatternDateFormat.SHORT_LINE) == "1399-12-01")
        assertTrue(date6.format(PatternDateFormat.SHORT_LINE) == "1399-12-30")
        assertTrue(date7.isLeapYear)
        assertFalse(date8.isLeapYear)
        assertEquals(9, date9.untilDays(date10))
        assertEquals(-9, date10.untilDays(date9))
        assertEquals(0, date10.untilDays(date10))
        assertEquals(1, date10.untilDays(date11))
    }
}



