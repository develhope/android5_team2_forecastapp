package co.develhope.meteoapp.features.network

import co.develhope.meteoapp.R
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import kotlin.time.DurationUnit

object DateUtils {
    private val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val outputFormat = DateTimeFormatter.ofPattern("MM/dd")
    private val dayOfWeekFormat = DateTimeFormatter.ofPattern("EEEE")
    private val dateWithNamesFormat = DateTimeFormatter.ofPattern("EEEE dd MMMM")

    fun getMonthAndDay(inputDate: String): String {
        val date = LocalDate.parse(inputDate, inputFormat)
        return date.format(outputFormat)
    }

    fun getDayOfWeek(inputDate: String): String {
        val date = LocalDate.parse(inputDate, inputFormat)
        return date.format(dayOfWeekFormat)
    }

    fun getYearMonthAndDay(outputDate: String): String {
        val date = LocalDate.parse(outputDate, inputFormat)
        return date.format(inputFormat)
    }

    fun getDateForTodayAndTomorrowScreen(date: String): String {
        val date = LocalDate.parse(date, inputFormat)
        return date.format(dateWithNamesFormat)
    }

}
