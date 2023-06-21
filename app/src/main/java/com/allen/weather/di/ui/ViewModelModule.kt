package com.allen.weather.di.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.allen.weather.app.base.BaseViewModelFactory
import com.allen.weather.ui.pages.dailyweather.WeatherDailyViewModel
import com.allen.weather.ui.pages.hourlyweather.WeatherHourlyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherDailyViewModel::class)
    abstract fun bindWeatherDailyViewModel(viewModel: WeatherDailyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherHourlyViewModel::class)
    abstract fun bindWeatherHourlyViewModel(viewModel: WeatherHourlyViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: BaseViewModelFactory): ViewModelProvider.Factory
}
