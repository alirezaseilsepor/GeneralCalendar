package ir.kingapp.calendar

import androidx.annotation.Keep

const val YEAR = 'y'
const val MONTH = 'm'
const val DAY = 'd'
const val DAY_N = 'p'
const val DAY_NONE_ZERO = 'j'
const val MONTH_N = 'G'
const val HOUR = 'h'
const val MINUTES = 'M'
const val SECOND = 's'
const val MILLI = 'S'

@Keep
enum class PatternDateFormat(val pattern: String) {
    SHORT_SLASH("$YEAR/$MONTH/$DAY"),
    SHORT_LINE("$YEAR-$MONTH-$DAY"),
    MONTH_NAME("$DAY_NONE_ZERO $MONTH_N $YEAR"),
    MONTH_DAY("$DAY_NONE_ZERO $MONTH_N"),
    FULL("$YEAR-$MONTH-$DAY $HOUR:$MINUTES:$SECOND"),
    FULL_BY_MILLI("$YEAR-$MONTH-$DAY $HOUR:$MINUTES:$SECOND:$MILLI"),
    FULL_BY_NAME("$DAY_N $DAY $MONTH_N $YEAR");
}