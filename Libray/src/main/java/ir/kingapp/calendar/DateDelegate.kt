package ir.kingapp.calendar

import java.util.Date
import java.util.Locale
import kotlin.math.ceil

interface DateDelegate {
    val year: Int
    val month: Int
    val day: Int
    val lengthOfMonth: Int
    val isLeapYear: Boolean
    val date: Date
    val id: Int
    fun goStartMonth(): DateDelegate
    fun goEndMonth(): DateDelegate
    fun addDay(day: Int): DateDelegate
    fun addMonth(month: Int): DateDelegate
    fun addYear(year: Int): DateDelegate
    fun monthName(locale: Locale): String
    fun getListMonthName(locale: Locale): List<String>
    fun dayWeekName(locale: Locale): String
    fun copy(): DateDelegate

    fun untilDays(dateDelegate: DateDelegate): Int {
        val diffInMillis = dateDelegate.date.time - date.time
        val days=diffInMillis / (1000 * 60 * 60 * 24).toDouble()
        return ceil(days).toInt()
    }


    fun format(
        locale: Locale,
        patternDateFormat: PatternDateFormat = PatternDateFormat.SHORT_LINE,
    ): String {
        val simpleDateFormatter = SimpleDateFormatter()
        return simpleDateFormatter.format(this.copy(), locale, patternDateFormat)
    }

    operator fun compareTo(other: DateDelegate) = id.compareTo(other.id)
}


