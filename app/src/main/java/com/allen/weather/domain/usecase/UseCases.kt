package com.allen.weather.domain.usecase

import com.allen.weather.domain.usecase.WeatherUseCase
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseCases @Inject constructor(
    private val _weatherUseCase: Lazy<WeatherUseCase>,
) {
    val weatherUseCase: WeatherUseCase by lazy { _weatherUseCase.get() }
}
