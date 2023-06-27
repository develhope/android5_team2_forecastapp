package co.develhope.meteoapp.features.network

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

object DateUtils {
    private val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val outputFormat = DateTimeFormatter.ofPattern("MM/dd")
    private val dayOfWeekFormat = DateTimeFormatter.ofPattern("EEEE")

    fun getMonthAndDay(inputDate: String): String {
        val date = LocalDate.parse(inputDate, inputFormat)
        return date.format(outputFormat)
    }

    fun getDayOfWeek(inputDate: String): String {
        val date = LocalDate.parse(inputDate, inputFormat)
        return date.format(dayOfWeekFormat)
    }
}
