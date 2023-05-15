package co.develhope.meteoapp

data class WeatherConditions(
    val country: String,
    val region: String,
    val city: String,
    val timeOfDay: String,
    val weatherCondition: String,
    val minTemperature: Int,
    val maxTemperature: Int,
    val wind: Int,
    val windDirection: String,
    val humidity: Int,
    val rainfall: Int,
    val cloudCover: Int,
    val uvIndex: Int,
)

