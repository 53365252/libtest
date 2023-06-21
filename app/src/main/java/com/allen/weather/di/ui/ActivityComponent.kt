package com.allen.weather.di.ui

import com.allen.weather.app.MyApplication
import com.allen.weather.di.ActivityScope
import com.allen.weather.di.ApplicationComponent
import com.allen.weather.ui.pages.dailyweather.WeatherDailyActivity
import com.allen.weather.ui.pages.hourlyweather.WeatherHourlyActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [
        ApplicationComponent::class
    ]
)
interface ActivityComponent {
    fun inject(activity : WeatherDailyActivity)
    fun inject(activity: WeatherHourlyActivity)
}

fun WeatherDailyActivity.bindComponent() = build().inject(this)
fun WeatherHourlyActivity.bindComponent() = build().inject(this)

private fun build() = DaggerActivityComponent.builder()
    .applicationComponent(MyApplication.applicationComponent)
    .build()
