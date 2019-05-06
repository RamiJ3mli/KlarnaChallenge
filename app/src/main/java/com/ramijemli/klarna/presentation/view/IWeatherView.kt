package com.ramijemli.klarna.presentation.view

import com.ramijemli.klarna.presentation.viewmodel.Weather

interface IWeatherView {
    fun renderWeather(data: Weather)
    fun showError(msg: String)
    fun showNetworkError()
}
