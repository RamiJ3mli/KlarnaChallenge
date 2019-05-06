package com.ramijemli.klarna.data.repository

import com.ramijemli.klarna.data.datasource.remote.WeatherRemoteDataSource
import com.ramijemli.klarna.data.rest.response.WeatherResponse
import io.reactivex.Single

class WeatherRepository : IWeatherRepository {

    private var remoteDataSource: WeatherRemoteDataSource? = null

    override fun get(lat: Double, lng: Double): Single<WeatherResponse> {
        this.remoteDataSource = WeatherRemoteDataSource()
        return this.remoteDataSource?.get(lat, lng)!!
    }

}
