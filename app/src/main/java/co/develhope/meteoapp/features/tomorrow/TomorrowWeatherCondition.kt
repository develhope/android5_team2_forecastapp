package co.develhope.meteoapp.features.tomorrow

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TomorrowWeatherCondition(@StringRes val hour: Int, @DrawableRes val imageWeather: Int, val grade: Int, val humidityPercentage: Int)
