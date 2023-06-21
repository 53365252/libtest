package com.allen.weather.ui.pages.dailyweather

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.allen.weather.di.ui.bindComponent
import com.allen.weather.R
import com.allen.weather.app.base.BaseActivity
import com.allen.weather.ui.common.recyclerview.DividerDecoration
import com.allen.weather.ui.common.recyclerview.RecyclerViewAdapter
import com.allen.weather.ui.viewdata.WeatherDailyViewData
import com.allen.weather.utils.GPSUtils
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_weather_daily.*
import javax.inject.Inject

class WeatherDailyActivity : BaseActivity<WeatherDailyViewModel>() {
    @Inject
    lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_daily)
        this.bindComponent()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherDailyViewModel::class.java)

        bindViewToModel()
        bindModelToView()
    }

    private fun bindModelToView() {
        viewModel.listViewData.observe(this, {
               adapter.setAdapterItems(it)
        })

        viewModel.action
            .subscribeBy(
                onNext = { execute(it) },
                onError = { handleError(it) }
            )
            .addTo(disposable)
    }

    private fun bindViewToModel() {
        adapter.register(
            WeatherDailyViewData::class.java,
            WeatherDailyViewBinder(
                onItemClicked = { _: View, item: WeatherDailyViewData ->
                    viewModel.onItemClicked(item)
                })
        )

        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(DividerDecoration(this))
        recycler_view.itemAnimator = null

        if (GPSUtils.isPermissionGranted(this)) {
            requestLocation()
        } else {
            GPSUtils.requestPermission(this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GPSUtils.LOCATION_REQUEST_CODE && GPSUtils.isPermissionGranted(this)) {
            requestLocation()
            if (!GPSUtils.isEnableGPS(this)) {
                GPSUtils.toggleUserOpenGps(this, onOk = {
                    viewModel.onGpsDisabled()
                })
            }
        }
    }

    private fun requestLocation() = GPSUtils.requestLocationUpdate(
        this,
        onLocationChange = {
            viewModel.onLocationChange(it)
        }
    )

    private fun execute(action: Action) {
        when (action) {
            is Action.StartNext -> {
                startActivity(action.destination(this))
            }
            is Action.StartGpsSetting -> {
               startActivityForResult(action.destination(this),GPSUtils.LOCATION_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GPSUtils.LOCATION_REQUEST_CODE && GPSUtils.isEnableGPS(this)) {
            if (!GPSUtils.isPermissionGranted(this)) {
                GPSUtils.requestPermission(this)
            }
        }
    }
}