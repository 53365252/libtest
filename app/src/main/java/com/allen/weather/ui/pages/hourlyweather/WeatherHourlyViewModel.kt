package com.allen.weather.ui.pages.hourlyweather

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allen.weather.app.ForBackground
import com.allen.weather.app.ForForeground
import com.allen.weather.domain.usecase.UseCases
import com.allen.weather.ui.common.BaseViewModel
import com.allen.weather.ui.common.IntentResolver
import com.allen.weather.ui.mapper.WeatherMapper
import com.allen.weather.ui.viewdata.WeatherHourlySummaryViewData
import com.allen.weather.ui.viewdata.WeatherHourlyViewData
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class WeatherHourlyViewModel@Inject constructor(
    useCases: UseCases,
    private val intentResolver: IntentResolver.Weather,
    private val weatherMapper: WeatherMapper,
    @ForBackground private val backgroundScheduler: Scheduler,
    @ForForeground private val foregroundScheduler: Scheduler
) : BaseViewModel() {

    private val weatherUseCase = useCases.weatherUseCase
    val listViewData: MutableLiveData<List<WeatherHourlyViewData>> = MutableLiveData()

    fun start(intent:Intent) {
        val(location, time) = intentResolver.resolveHourlyIntent(intent)
        viewModelScope
        weatherUseCase.getHourlyWeather(location,time)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .subscribeBy(
                onSuccess = {
                    listViewData.value = weatherMapper.toWeatherHourlyViewDataList( it )
                },
                onError = { handleError(it) }
            )
            .addTo(compositeDisposable)
    }



}