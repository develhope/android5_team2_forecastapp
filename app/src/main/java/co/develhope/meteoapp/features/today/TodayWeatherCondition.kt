package co.develhope.meteoapp.features.today

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TodayWeatherCondition(@StringRes val hour: Int, @DrawableRes val imageWeather: Int, val grade: Int, val humidityPercentage: Int)