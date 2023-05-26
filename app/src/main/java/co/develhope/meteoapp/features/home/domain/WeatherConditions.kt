package co.develhope.meteoapp.features.home.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class WeatherConditions(
//    TODO add date values
    @DrawableRes val weather: Int,
    @StringRes val day: Int,
    val wind:Int,
    val temp_min:Int,
    val temp_max:Int,
    val mm_rain:Int,
    ) {
}