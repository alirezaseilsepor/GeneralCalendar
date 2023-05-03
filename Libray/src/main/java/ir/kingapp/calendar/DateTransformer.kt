package ir.kingapp.calendar

import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Calendar.getYearCompact(): Int {
    return this.get(Calendar.YEAR)
}

fun Calendar.getMonthCompact(): Int {
    return this.get(Calendar.MONTH) + 1
}

fun Calendar.getDayCompact(): Int {
    return this.get(Calendar.DAY_OF_MONTH)
}

fun Date.getHourCompact(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.HOUR_OF_DAY)
}

fun Date.getMinutesCompact(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.MINUTE)
}

fun Date.getSecondCompact(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.SECOND)
}

fun Date.getMillisecondCompact(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.MILLISECOND)
}

fun getCurrentHour(): Int {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.HOUR_OF_DAY)
}

fun getCurrentMinute(): Int {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.MINUTE)
}

fun CalendarType.getAbstractDate(): DateDelegate {
    return when (this) {
        CalendarType.PERSIAN -> PersianDate()
        CalendarType.Gregorian -> GregorianDate()
    }
}

fun Number.withZeroNumber(): String = String.format(Locale.US, "%02d", this)

