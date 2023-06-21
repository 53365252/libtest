package com.allen.weather.di.data

import com.allen.weather.data.provider.WeatherProvider
import com.allen.weather.domain.contract.WeatherContract
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ProviderModule {

    @Binds
    @Singleton
    abstract fun providesWeather(provider: WeatherProvider): WeatherContract

}