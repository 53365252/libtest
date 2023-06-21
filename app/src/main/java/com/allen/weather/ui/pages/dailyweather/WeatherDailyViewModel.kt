package com.allen.weather.ui.pages.dailyweather

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allen.weather.app.ForBackground
import com.allen.weather.app.ForForeground
import com.allen.weather.data.entity.WeatherDailySummaryEntity
import com.allen.weather.domain.usecase.UseCases
import com.allen.weather.ui.common.BaseViewModel
import com.allen.weather.ui.common.IntentResolver
import com.allen.weather.ui.mapper.WeatherMapper
import com.allen.weather.ui.viewdata.WeatherDailySummaryViewData
import com.allen.weather.ui.viewdata.WeatherDailyViewData
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await
import javax.inject.Inject

sealed class Action {
    data class StartNext(
        val destination: (Context) -> Intent
    ) : Action()

    data class StartGpsSetting(
        val destination: (Context) -> Intent
    ) : Action()
}

class WeatherDailyViewModel@Inject constructor(
    useCases: UseCases,
    private val intentResolver: IntentResolver.Outer,
    private val weatherIntentResolver: IntentResolver.Weather,
    private val weatherMapper: WeatherMapper,
    @ForBackground private val backgroundScheduler: Scheduler,
    @ForForeground private val foregroundScheduler: Scheduler
) : BaseViewModel() {

    val action: PublishProcessor<Action> = PublishProcessor.create()
    private val weatherUseCase = useCases.weatherUseCase
    val listViewData: MutableLiveData<List<WeatherDailyViewData>> = MutableLiveData()
    private var mLocation = ""

    fun onLocationChange(location:String) {
        mLocation = location

        weatherUseCase.getDailyWeather(location)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .subscribeBy(
                onSuccess = {
                    listViewData.value = weatherMapper.toWeatherDailyViewDataList( it  )
                },
                onError = { handleError(it) }
            )
            .addTo(compositeDisposable)

//        viewModelScope.launch {
//            val result = weatherUseCase.getDailyWeather(location).await()
//            when(result){
//                WeatherDailySummaryEntity.EMPTY -> {handleError()}
//                else -> {
//                    listViewData.value = weatherMapper.toWeatherDailyViewDataList( result  )
//                }
//            }
//        }
    }

    fun onGpsDisabled(){
        action.onNext(Action.StartGpsSetting(intentResolver.getGpsSetting()))
    }

    fun onItemClicked(data: WeatherDailyViewData){
        action.onNext(Action.StartNext(weatherIntentResolver.getHourly(location = mLocation, time = data.time )))
    }

}