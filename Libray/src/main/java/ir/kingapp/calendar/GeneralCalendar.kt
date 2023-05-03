package ir.kingapp.calendar

import android.content.Context
import net.time4j.android.ApplicationStarter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GeneralCalendar() : DateDelegate {

    private val simpleDateFormatter = SimpleDateFormatter()
    private var persianDate = PersianDate()
    private var gregorianDate = GregorianDate()
    private var dateDelegate: DateDelegate = calendarType.getAbstractDate()
        set(value) {
            field = value
            when (value) {
                is PersianDate -> {
                    persianDate = value
                    gregorianDate = value.toGregorianDate()
                }

                is GregorianDate -> {
                    persianDate = value.toPersianDate()
                    gregorianDate = value
                }
            }
        }
        get() {
            return when (calendarType) {
                CalendarType.PERSIAN -> {
                    persianDate
                }

                CalendarType.Gregorian -> {
                    gregorianDate
                }
            }
        }


    constructor(delegate: DateDelegate) : this() {
        this.dateDelegate = delegate
    }

    constructor(date: Date) : this() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
        this.dateDelegate = PersianDate(dateFormat.format(date))
    }


    constructor(
        year: Int,
        month: Int,
        day: Int,
    ) : this() {
        dateDelegate = simpleDateFormatter.parse("$year-$month-$day")!!.dateDelegate
    }

    constructor(stringDate: String) : this() {
        dateDelegate = simpleDateFormatter.parse(stringDate)!!.dateDelegate
    }

    companion object {
        var calendarType = CalendarType.PERSIAN
        fun init(context: Context, prefetch: Boolean = true) {
            ApplicationStarter.initialize(context, prefetch)
        }

        fun of(stringDate: String?): GeneralCalendar? {
            return runCatching {
                GeneralCalendar(stringDate!!)
            }.getOrNull()
        }
    }


    override val year: Int
        get() = dateDelegate.year

    override val month: Int
        get() = dateDelegate.month

    override val day: Int
        get() = dateDelegate.day

    override val lengthOfMonth: Int
        get() = dateDelegate.lengthOfMonth

    override val isLeapYear: Boolean
        get() = dateDelegate.isLeapYear

    override val date: Date
        get() = dateDelegate.date

    override val id: Int
        get() = dateDelegate.id

    override fun goStartMonth(): GeneralCalendar {
        dateDelegate = dateDelegate.goStartMonth()
        return this
    }

    override fun goEndMonth(): GeneralCalendar {
        dateDelegate = dateDelegate.goEndMonth()
        return this
    }

    override fun addDay(day: Int): GeneralCalendar {
        dateDelegate = dateDelegate.addDay(day)
        return this
    }

    override fun addMonth(month: Int): GeneralCalendar {
        dateDelegate = dateDelegate.addMonth(month)
        return this
    }

    override fun addYear(year: Int): GeneralCalendar {
        dateDelegate = dateDelegate.addYear(year)
        return this
    }

    override fun monthName(locale: Locale): String {
        return dateDelegate.monthName(locale)
    }

    override fun getListMonthName(locale: Locale): List<String> {
        return dateDelegate.getListMonthName(locale)
    }

    override fun dayWeekName(locale: Locale): String {
        return dateDelegate.dayWeekName(locale)
    }

    fun format(
        patternDateFormat: PatternDateFormat = PatternDateFormat.SHORT_LINE,
    ): String {
        return when (dateDelegate) {
            is PersianDate -> {
                (dateDelegate as PersianDate).format(patternDateFormat)
            }

            is GregorianDate -> {
                (dateDelegate as GregorianDate).format(patternDateFormat)
            }

            else -> ""
        }
    }

    override fun copy(): GeneralCalendar {
        return GeneralCalendar(dateDelegate.copy())
    }

    override fun hashCode(): Int {
        return dateDelegate.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is DateDelegate -> {
                this.id == other.id
            }

            else -> false
        }
    }

    override fun toString(): String {
        return "$persianDate $gregorianDate"
    }

    fun toGregorianDate(): GregorianDate = gregorianDate.copy()
    fun toPersianDate(): PersianDate = persianDate.copy()
}