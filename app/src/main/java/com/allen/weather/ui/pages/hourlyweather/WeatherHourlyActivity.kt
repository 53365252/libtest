package com.allen.weather.ui.pages.hourlyweather

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.allen.weather.R
import com.allen.weather.app.base.BaseActivity
import com.allen.weather.di.ui.bindComponent
import com.allen.weather.ui.common.recyclerview.DividerDecoration
import com.allen.weather.ui.common.recyclerview.RecyclerViewAdapter
import com.allen.weather.ui.viewdata.WeatherHourlyViewData
import kotlinx.android.synthetic.main.activity_weather_hourly.*
import javax.inject.Inject

class WeatherHourlyActivity : BaseActivity<WeatherHourlyViewModel>() {
    @Inject
    lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_hourly)
        this.bindComponent()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherHourlyViewModel::class.java)

        bindViewToModel()
        bindModelToView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.start(intent)
    }

    private fun bindModelToView() {
        viewModel.listViewData.observe(this, {
              adapter.setAdapterItems(it)
        })
    }

    private fun bindViewToModel() {
        adapter.register(
            WeatherHourlyViewData::class.java,
            WeatherHourlyViewBinder()
        )

        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(DividerDecoration(this))
        recycler_view.itemAnimator = null
    }

}