package ir.kingapp.calendar

import android.util.Log
import java.util.Locale

internal class SimpleDateFormatter {


    fun parse(
        stringDate: String,
    ): GeneralCalendar? {
        val result = runCatching {
            if (stringDate.length < 8) return@runCatching null

            val splitChar = stringDate[4]
            val splitList = stringDate.split(splitChar)
            val year = getSafeInt(splitList.getOrNull(0), 4) ?: return@runCatching null
            val month = getSafeInt(splitList.getOrNull(1), 2) ?: return@runCatching null
            val day = getSafeInt(splitList.getOrNull(2), 2) ?: return@runCatching null

            if (year in 1900..2100) return@runCatching GeneralCalendar(
                GregorianDate(
                    year,
                    month,
                    day
                )
            )

            if (year in 1300..1500) return@runCatching GeneralCalendar(
                PersianDate(
                    year,
                    month,
                    day
                )
            )
            return@runCatching null
        }
        if (result.getOrNull() == null)
            Log.e("GeneralCalendar", "can't convert $stringDate")

        return result.getOrNull()
    }


    fun format(
        dateDelegate: DateDelegate,
        locale: Locale,
        patternDateFormat: PatternDateFormat = PatternDateFormat.SHORT_SLASH,
    ): String {
        var result = ""
        patternDateFormat.pattern.forEach { ch ->
            val data: String
            when (ch) {
                YEAR -> {
                    data = dateDelegate.year.toString()
                }

                MONTH -> {
                    data = dateDelegate.month.withZeroNumber()
                }

                MONTH_N -> {
                    data = dateDelegate.monthName(locale)
                }

                DAY -> {
                    data = dateDelegate.day.withZeroNumber()
                }

                DAY_N -> {
                    data = dateDelegate.dayWeekName(locale)
                }

                DAY_NONE_ZERO -> {
                    data = dateDelegate.day.toString()
                }

                HOUR -> {
                    data = dateDelegate.date.getHourCompact().withZeroNumber()
                }

                MINUTES -> {
                    data = dateDelegate.date.getMinutesCompact().withZeroNumber()
                }

                SECOND -> {
                    data = dateDelegate.date.getSecondCompact().withZeroNumber()
                }

                MILLI -> {
                    data = dateDelegate.date.getMillisecondCompact().withZeroNumber()
                }

                else -> {
                    data = ch.toString()
                }
            }
            result += data
        }
        return result
    }


    private fun getSafeInt(text: String?, maxLength: Int): Int? {
        if (text == null) return null

        if (text.length == 1) return text.toIntOrNull()

        return if (text.length < maxLength) null
        else text.substring(0, maxLength).toIntOrNull()
    }
}