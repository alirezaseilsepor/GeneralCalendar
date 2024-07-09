package ir.kingapp.calendar

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date


class GregorianDateUnitTest {
    @Test
    fun test1() {
        val date1 = GregorianDate(2023, 5, 2)
        val date2 = GregorianDate("2023-05-02T01:59:22.123Z")
        val date3 = GregorianDate("2023-05-02T23:59:2")
        val formatter = SimpleDateFormat("yyyy/MM/dd")
        val date5 = GregorianDate(formatter.format(Date()))
        val date6 = GregorianDate()
        val date7 = GregorianDate.of("2500/1/12")
        val date8 = GregorianDate.of("test")
        val date9 = GregorianDate.of("2023/4/1")
        val date10 = GregorianDate.of("2023/04/01")
        val date11 = GregorianDate.of("2023.04.01")
        val date12 = GregorianDate.of("2023 04 21")
        val date13 = GregorianDate.of("1402-05-02")
        val date14 = GregorianDate.of("1401-01-01")

        val date15 = PersianDate("1402-05-02").toGregorianDate()
        val date16 = PersianDate("1401-01-01").toGregorianDate()
        val date17 = GeneralCalendar("2023-05-02").toPersianDate()

        assertTrue(date1.format(PatternDateFormat.SHORT_LINE) == "2023-05-02")
        assertTrue(date2.format(PatternDateFormat.SHORT_LINE) == "2023-05-02")
        assertTrue(date3.format(PatternDateFormat.SHORT_LINE) == "2023-05-02")
        assertTrue(date11?.format(PatternDateFormat.SHORT_LINE) == "2023-04-01")
        assertTrue(date12?.format(PatternDateFormat.SHORT_LINE) == "2023-04-21")
        assertTrue(date13?.format(PatternDateFormat.SHORT_LINE) == "2023-07-24")
        assertTrue(date14?.format(PatternDateFormat.SHORT_LINE) == "2022-03-21")
        assertTrue(date15.format(PatternDateFormat.SHORT_LINE) == "2023-07-24")
        assertTrue(date16.format(PatternDateFormat.SHORT_LINE) == "2022-03-21")
        assertTrue(date17.format(PatternDateFormat.SHORT_LINE) == "1402-02-12")
        assertTrue(date9?.format(PatternDateFormat.SHORT_LINE) == "2023-04-01")
        assertTrue(date5 == date6)
        assertNull(date7)
        assertNull(date8)
        assertNotNull(date10)
    }

    @Test
    fun equalTest1() {
        val date1 = GregorianDate()
        val date2 = GregorianDate()
        val date3 = GregorianDate(2023, 2, 17)
        val date4 = GregorianDate(2023, 2, 17)
        val date7 = GregorianDate("2023-05-02")
        val date8 = GregorianDate("2023-05-02")
        val date9 = GregorianDate("2023/03/30")
        val date10 = GregorianDate("2023/03/30")
        val date11 = GregorianDate("1402/04/12")
        val date12 = GregorianDate("1402/04/12")
        val date13 = GregorianDate("1402/04/12")
        val date14 = GregorianDate("1401/04/12")

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
        val date1 = GregorianDate(2023, 12, 12).addDay(20)
        val date2 = GregorianDate(2020, 2, 27).addDay(3)
        val date3 = GregorianDate(2021, 2, 27).addDay(3)
        val date4 = GregorianDate(2021, 2, 27).addDay(-3)

        val date5 = GregorianDate(2021, 2, 27).addMonth(1)
        val date6 = GregorianDate(2021, 2, 27).addMonth(-1)

        val date7 = GregorianDate(2021, 2, 27).addYear(1)
        val date8 = GregorianDate(2021, 2, 27).addYear(-1)


        val date9 = GregorianDate(2021, 2, 27)
            .addDay(1)
            .addMonth(2)
            .addYear(-1)



        assertEquals("2024-01-01", date1.format(PatternDateFormat.SHORT_LINE))
        assertEquals("2020-03-01", date2.format(PatternDateFormat.SHORT_LINE))
        assertEquals("2021-03-02", date3.format(PatternDateFormat.SHORT_LINE))
        assertEquals("2021-02-24", date4.format(PatternDateFormat.SHORT_LINE))
        assertEquals("2021-03-27", date5.format(PatternDateFormat.SHORT_LINE))
        assertEquals("2021-01-27", date6.format(PatternDateFormat.SHORT_LINE))
        assertEquals("2022-02-27", date7.format(PatternDateFormat.SHORT_LINE))
        assertEquals("2020-02-27", date8.format(PatternDateFormat.SHORT_LINE))
        assertEquals("2020-04-28", date9.format(PatternDateFormat.SHORT_LINE))
    }

    @Test
    fun testOtherFunction() {
        val date1 = GregorianDate(2020, 2, 1)
        val date2 = GregorianDate(2021, 2, 1)
        val date3 = GregorianDate(2020, 4, 1)
        val date4 = GregorianDate(2020, 12, 1)
        val date5 = GregorianDate(2024, 12, 17).goStartMonth()
        val date6 = date5.copy().goEndMonth()
        val date7 = GregorianDate(2020, 1, 1)
        val date8 = GregorianDate(2021, 1, 1)
        val date9 = GregorianDate(2021, 1, 1)
        val date10 = GregorianDate(2021, 1, 10)
        val date11 = GregorianDate(2021, 1, 11)


        assertTrue(date1.lengthOfMonth == 29)
        assertTrue(date2.lengthOfMonth == 28)
        assertTrue(date3.lengthOfMonth == 30)
        assertTrue(date4.lengthOfMonth == 31)
        assertTrue(date2.day == 1)
        assertTrue(date2.month == 2)
        assertTrue(date2.year == 2021)
        assertEquals("2024-12-01", date5.format(PatternDateFormat.SHORT_LINE))
        assertEquals("2024-12-31", date6.format(PatternDateFormat.SHORT_LINE))
        assertTrue(date7.isLeapYear)
        assertFalse(date8.isLeapYear)
        assertEquals(9, date9.untilDays(date10))
        assertEquals(-9, date10.untilDays(date9))
        assertEquals(0, date10.untilDays(date10))
        assertEquals(1, date10.untilDays(date11))
    }
}



