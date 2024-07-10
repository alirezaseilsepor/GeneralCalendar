package ir.kingapp.generalcalendar

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ir.kingapp.calendar.CalendarType
import ir.kingapp.calendar.GeneralCalendar
import ir.kingapp.calendar.GregorianDate
import ir.kingapp.calendar.PersianDate
import java.util.Locale

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GeneralCalendar.init(this.applicationContext)

        //   parsePersianDate("1402/03/09")
        val date5 = PersianDate()
        val date6 = GregorianDate()
        val date7 = GeneralCalendar()

        val startDate = GeneralCalendar("2024-03-24T12:30:00.000")
        val today = GeneralCalendar()
        val today1 = GeneralCalendar().date
        val eee=today1
        val progress = startDate.untilDays(today).toFloat()
        val a22=progress
        val a13 = date7.monthName(Locale("fa"))
        val a24 = date7.monthName(Locale("en"))
        val a = date5.getListMonthName(Locale("fa"))
        val a1 = date5.getListMonthName(Locale("en"))
        val a2 = date6.getListMonthName(Locale("fa"))
        val a3 = date6.getListMonthName(Locale("en"))
        val e=a3


        GeneralCalendar.calendarType = CalendarType.PERSIAN
        val date1="2024-07-07T12:30:00.000"
        val persianDate=GeneralCalendar(date1)
        val untilDayP=GeneralCalendar().untilDays(persianDate)
        GeneralCalendar.calendarType = CalendarType.Gregorian
        val gregDate=GeneralCalendar(date1)
        val untilDayG=GeneralCalendar().untilDays(gregDate)
      //  assertEquals(untilDayP, untilDayG)

        Log.e("date55555",untilDayP.toString()+"   "+untilDayG.toString())
    }

}