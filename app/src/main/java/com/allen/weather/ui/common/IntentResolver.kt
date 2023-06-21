package com.allen.weather.ui.common

import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.allen.weather.ui.pages.dailyweather.WeatherDailyActivity
import com.allen.weather.ui.pages.hourlyweather.WeatherHourlyActivity
import javax.inject.Inject

sealed class IntentResolver {

    class Weather @Inject constructor() {
        companion object {
            private const val KEY_LOCATION = "KEY_LOCATION"
            private const val KEY_TIME = "KEY_TIME"
        }

        fun getDaily(): (Context) -> Intent =
                {
                    Intent(it, WeatherDailyActivity::class.java)
                }

        fun getHourly(location: String, time: Long): (Context) -> Intent =
                {
                    Intent(it, WeatherHourlyActivity::class.java).apply {
                        putExtra(KEY_LOCATION, location)
                        putExtra(KEY_TIME, time)
                    }
                }

        fun resolveHourlyIntent(intent: Intent) = Pair<String, Long>(
                intent.extras?.getString(KEY_LOCATION) ?: "",
                intent.extras?.getLong(KEY_TIME) ?: 0L
        )
    }

    class Outer @Inject constructor() {

        fun getGpsSetting(): (Context) -> Intent = {
            Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        }

    }
}
