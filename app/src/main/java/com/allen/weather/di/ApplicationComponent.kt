package com.allen.weather.di

import android.content.Context
import android.content.res.Resources
import com.allen.weather.domain.usecase.UseCases
import com.allen.weather.app.MyApplication
import com.allen.weather.app.base.BaseViewModelFactory
import com.allen.weather.di.data.ApiModule
import com.allen.weather.di.data.ProviderModule
import com.allen.weather.di.ui.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ApiModule::class,
        SchedulerModule::class,
        ProviderModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(baseApplication: MyApplication)

    val baseViewModelFactory: BaseViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApplicationComponent
    }

}
