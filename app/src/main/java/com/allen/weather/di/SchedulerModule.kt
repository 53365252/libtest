package com.allen.weather.di

import com.allen.weather.app.ForBackground
import com.allen.weather.app.ForForeground
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class SchedulerModule {

    @Provides
    @ForForeground
    fun providesUiForegroundScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @ForBackground
    @Reusable
    fun providesBackgroundScheduler(): Scheduler {
        return Schedulers.io()
    }

}
