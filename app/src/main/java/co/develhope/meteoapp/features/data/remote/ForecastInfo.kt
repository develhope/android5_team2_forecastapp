package co.develhope.meteoapp.features.data.remote

import co.develhope.meteoapp.R

object ForecastInfo {

    fun weatherConditionCode(weatherInfo: Int): Int {
        return when(weatherInfo){
            0 -> R.string.clear
            1-> R.string.mainly_clear
            2 -> R.string.partly_cloudy
            3 ->  R.string.overcast
            45 ->  R.string.fog
            48 ->  R.string.depositing_rime_fog
            51 ->  R.string.drizzle_light
            53 ->  R.string.drizzle_moderate
            55 -> R.string.drizzle_dense
            56 ->  R.string.freezing_drizzle_light
            57 ->  R.string.freezing_drizzle_dense
            61 ->  R.string.slight_rain
            63 ->  R.string.moderate_rain
            65 ->  R.string.heavy_rain
            66 -> R.string.freezing_light_rain
            67 -> R.string.freezing_heavy_intensity_rain
            71 -> R.string.slight_snow
            73 -> R.string.moderate_snow
            75 -> R.string.heavy_snow
            77 -> R.string.snow_grains
            80 -> R.string.slight_rain_shower
            81 -> R.string.moderate_rain_shower
            82 -> R.string.violent_rain_shower
            85 -> R.string.slight_snow_shower
            86 -> R.string.heavy_snow_shower
            95 -> R.string.thunderstorm_slight_moderate
            96 -> R.string.thunderstorm_with_slight_hail
            99 -> R.string.thunderstorm_with_heavy_hail
            else -> R.string.error
        }
    }
}