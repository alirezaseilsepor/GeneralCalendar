package ir.kingapp.calendar

import net.time4j.PlainDate
import net.time4j.calendar.PersianCalendar
import java.text.DateFormatSymbols
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale

class GregorianDate() : DateDelegate {

    private val simpleDateFormatter = SimpleDateFormatter()
    private var gregorianCalendar: GregorianCalendar = GregorianCalendar()


    constructor(stringDate: String) : this() {
        val gc = simpleDateFormatter.parse(stringDate)!!
        gregorianCalendar = gc.toGregorianDate().gregorianCalendar
    }


    constructor(
        year: Int,
        month: Int,
        day: Int,
    ) : this() {
        val gregorianCalendar = GregorianCalendar()
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, day)
        gregorianCalendar.set(Calendar.MONTH, month - 1)
        gregorianCalendar.set(Calendar.YEAR, year)
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 0)
        gregorianCalendar.set(Calendar.MINUTE, 0)
        gregorianCalendar.set(Calendar.SECOND, 0)
        gregorianCalendar.set(Calendar.MILLISECOND, 0)
        this.gregorianCalendar = gregorianCalendar
    }

    companion object {
        fun of(stringDate: String?): GregorianDate? {
            return runCatching {
                GregorianDate(stringDate!!)
            }.getOrNull()
        }

        fun getListMonthName(locale: Locale): List<String> {
            return DateFormatSymbols(locale).months.toList()
        }
    }

    override val year: Int
        get() = gregorianCalendar.getYearCompact()

    override val month: Int
        get() = gregorianCalendar.getMonthCompact()

    override val day: Int
        get() = gregorianCalendar.getDayCompact()

    override val lengthOfMonth: Int
        get() = gregorianCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    override val isLeapYear: Boolean
        get() = gregorianCalendar.isLeapYear(year)

    override val date: Date
        get() = gregorianCalendar.time

    override fun goStartMonth(): GregorianDate {
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1)
        return this
    }

    override fun goEndMonth(): GregorianDate {
        this.gregorianCalendar.set(Calendar.DAY_OF_MONTH, lengthOfMonth)
        return this
    }

    override fun addDay(day: Int): GregorianDate {
        gregorianCalendar.add(Calendar.DATE, day)
        return this
    }

    override fun addMonth(month: Int): GregorianDate {
        gregorianCalendar.add(Calendar.MONTH, month)
        return this
    }

    override fun addYear(year: Int): GregorianDate {
        gregorianCalendar.add(Calendar.YEAR, year)
        return this
    }

    override fun monthName(locale: Locale): String {
        return gregorianCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, locale) ?: ""
    }

    override fun getListMonthName(locale: Locale): List<String> {
        return Companion.getListMonthName(locale)
    }

    override fun dayWeekName(locale: Locale): String {
        return toPersianDate().dayWeekName(locale)
    }

    override fun copy(): GregorianDate {
        return GregorianDate(year, month, day)
    }

    override val id: Int
        get() = (year.toString() + month.withZeroNumber() + day.withZeroNumber()).toInt()

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is GregorianDate -> {
                this.id == other.id
            }

            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = year
        result = 31 * result + month
        result = 31 * result + day
        result = 31 * result + gregorianCalendar.hashCode()
        return result
    }

    override fun toString(): String {
        return simpleDateFormatter.format(this)
    }

    fun toPersianDate(): PersianDate {
        val planDate = PlainDate.of(year, month, day)
        val pt = planDate.transform(PersianCalendar::class.java)
        return PersianDate(pt.year, pt.month.value, pt.dayOfMonth)
    }
}