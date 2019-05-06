package com.ramijemli.klarna.data.repository

import com.ramijemli.klarna.data.rest.response.WeatherResponse
import io.reactivex.Single

interface IWeatherRepository {

    fun get(lat: Double, lng: Double): Single<WeatherResponse>

}

