package ir.kingapp.calendar

import net.time4j.PlainDate
import net.time4j.calendar.PersianCalendar
import net.time4j.calendar.PersianMonth
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class PersianDate() : DateDelegate {

    private val simpleDateFormatter = SimpleDateFormatter()
    private var persianCalendar = PersianCalendar.nowInSystemTime()


    constructor(stringDate: String) : this() {
        val gc = simpleDateFormatter.parse(stringDate)!!
        persianCalendar = gc.toPersianDate().persianCalendar
    }

    constructor(
        year: Int,
        month: Int,
        day: Int,
    ) : this() {
        persianCalendar = PersianCalendar.of(year, month, day)
    }


    companion object {
        fun of(stringDate: String?): PersianDate? {
            return runCatching {
                PersianDate(stringDate!!)
            }.getOrNull()
        }

        fun getListMonthName(locale: Locale): List<String> {
            return PersianMonth.values().map { it.getDisplayName(locale) }
        }
    }

    override val year: Int
        get() = persianCalendar.year

    override val month: Int
        get() = persianCalendar.month.value

    override val day: Int
        get() = persianCalendar.dayOfMonth

    override val lengthOfMonth: Int
        get() = persianCalendar.lengthOfMonth()

    override val isLeapYear: Boolean
        get() = persianCalendar.isLeapYear

    override val date: Date
        get() = toDate()

    override fun goStartMonth(): PersianDate {
        persianCalendar = PersianCalendar.of(year, month, 1)
        return this
    }

    override fun goEndMonth(): PersianDate {
        persianCalendar = PersianCalendar.of(year, month, lengthOfMonth)
        return this
    }

    override fun addDay(day: Int): PersianDate {
        persianCalendar = persianCalendar.plus(day.toLong(), PersianCalendar.Unit.DAYS)
        return this
    }

    override fun addMonth(month: Int): PersianDate {
        persianCalendar = persianCalendar.plus(month.toLong(), PersianCalendar.Unit.MONTHS)
        return this
    }

    override fun addYear(year: Int): PersianDate {
        persianCalendar = persianCalendar.plus(year.toLong(), PersianCalendar.Unit.YEARS)
        return this
    }

    override fun monthName(locale: Locale): String {
        return persianCalendar.month.getDisplayName(locale)
    }

    override fun getListMonthName(locale: Locale): List<String> {
        return Companion.getListMonthName(locale)
    }

    override fun dayWeekName(locale: Locale): String {
        return persianCalendar.dayOfWeek.getDisplayName(locale)
    }

    override fun copy(): PersianDate {
        return PersianDate(year, month, day)
    }

    override val id: Int
        get() = (year.toString() + month.withZeroNumber() + day.withZeroNumber()).toInt()


    override fun equals(other: Any?): Boolean {
        return when (other) {
            is PersianDate -> {
                this.id == other.id
            }

            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = year
        result = 31 * result + month
        result = 31 * result + day
        result = 31 * result + persianCalendar.hashCode()
        return result
    }

    override fun toString(): String {
        return simpleDateFormatter.format(this)
    }

    fun toGregorianDate(): GregorianDate {
        val calendar = Calendar.getInstance()
        calendar.time = toDate()
        return GregorianDate(
            calendar.getYearCompact(),
            calendar.getMonthCompact(),
            calendar.getDayCompact()
        )
    }


    private fun toDate(): Date {
        val pd = persianCalendar.transform(PlainDate::class.java).atTime(12, 0)
        val dateFormatter = SimpleDateFormat("y-M-d", Locale.ENGLISH)
        return dateFormatter.parse(pd.toString())!!
    }

}