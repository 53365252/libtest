package com.allen.weather.extension

import android.icu.util.TimeUnit
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

private val DAILY_FORMAT: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

private val HOURLY_FORMAT: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

fun Long.weatherTimeToDailyFormat():String = DAILY_FORMAT.format(Date(this*1000))

fun Long.weatherTimeToHourlyFormat():String = HOURLY_FORMAT.format(Date(this*1000))

fun Long.weatherTimeBeforeNow() = this< Calendar.getInstance().timeInMillis/1000