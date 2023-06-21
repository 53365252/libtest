package com.allen.weather.data.api

import com.allen.weather.data.entity.WeatherSummaryEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    /**
     * note: since there just one API endpoint, so we use the key as the part of url directly
     * example:https://api.darksky.net/forecast/a4b5a4fc6021b28ff0a1bd4f30cfd2c2/37.8267,-122.4233,1563957390?exclude=minutely,alerts,flags
     * @param location latitude and longitude, example: "37.8267,-122.4233"
     * @param exclude
     * @return
     */
    @GET("forecast/a4b5a4fc6021b28ff0a1bd4f30cfd2c2/{location}")
    fun getWeather(
        @Path(value = "location", encoded = true) location: String,
        @Query(value = "exclude", encoded = true) exclude: String
    ): Single<WeatherSummaryEntity>

    companion object{
        const val EXCLUDE_DAILY = "minutely,hourly,flags,currently"
        const val EXCLUDE_HOURLY = "minutely,flags,currently"
    }
}