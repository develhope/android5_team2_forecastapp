package co.develhope.meteoapp.features.data.remote

import co.develhope.meteoapp.R

object ForecastInfo {

    private val mapWeatherCondition = mapOf(
        Pair(0, R.string.clear),
        Pair(1, R.string.mainly_clear),
        Pair(2, R.string.partly_cloudy),
        Pair(3, R.string.overcast),
        Pair(45, R.string.fog),
        Pair(48, R.string.depositing_rime_fog),
        Pair(51, R.string.drizzle_light),
        Pair(53, R.string.drizzle_moderate),
        Pair(55, R.string.drizzle_dense),
        Pair(56, R.string.freezing_drizzle_light),
        Pair(57, R.string.freezing_drizzle_dense),
        Pair(61, R.string.slight_rain),
        Pair(63, R.string.moderate_rain),
        Pair(65, R.string.heavy_rain),
        Pair(66, R.string.freezing_light_rain),
        Pair(67, R.string.freezing_heavy_intensity_rain),
        Pair(71, R.string.slight_snow),
        Pair(73, R.string.moderate_snow),
        Pair(75, R.string.heavy_snow),
        Pair(77, R.string.snow_grains),
        Pair(80, R.string.slight_rain_shower),
        Pair(81, R.string.moderate_rain_shower),
        Pair(82, R.string.violent_rain_shower),
        Pair(85, R.string.slight_snow_shower),
        Pair(86, R.string.heavy_snow_shower),
        Pair(95, R.string.thunderstorm_slight_moderate),
        Pair(96, R.string.thunderstorm_with_slight_hail),
        Pair(99, R.string.thunderstorm_with_heavy_hail)
    )
    fun weatherConditionCode(weatherInfo: Int): Int {
        return mapWeatherCondition[weatherInfo] ?: R.string.error
    }

    fun windDirection(position: Int) :Int {
        return when (position) {
            in 0..60 -> R.string.NNE
            in 61..89 -> R.string.ENE
            in 90..150 -> R.string.ESE
            in 151..180 -> R.string.SSE
            in 181..240 -> R.string.SSW
            in 241..270 -> R.string.WSW
            in 271..330 -> R.string.WNW
            else -> R.string.NNW
        }
    }
}