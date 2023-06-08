package co.develhope.meteoapp.features.today

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TodayTomorrowWeatherCondition(
    @StringRes val hour: Int,
    @DrawableRes val imageWeather: Int,
    val grade: Int,
    val humidityPercentage: Int,
    val gradeInCardView: String,
    val uvIndexInCardView: String,
    val humidityInCardView: String,
    val WindInCardView:String,
    val coverPercentageInCardView: String,
    val rainInCmInCardView: String
    )

