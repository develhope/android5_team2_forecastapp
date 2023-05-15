package co.develhope.meteoapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class HomeScreenItemData(
//    TODO add date values
    @DrawableRes val weather: Int,
    @StringRes val day: Int,
    val wind:Int,
    val temp_min:Int,
    val temp_max:Int,
    val mm_rain:Int,
    ) {
}