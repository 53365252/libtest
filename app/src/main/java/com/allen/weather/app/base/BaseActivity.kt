package com.allen.weather.app.base

import androidx.appcompat.app.AppCompatActivity
import com.allen.weather.ui.common.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
abstract  class BaseActivity<T : BaseViewModel> : AppCompatActivity(){
    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory

    lateinit var viewModel: T

    val disposable: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

    fun handleError(throwable: Throwable){

    }
}
