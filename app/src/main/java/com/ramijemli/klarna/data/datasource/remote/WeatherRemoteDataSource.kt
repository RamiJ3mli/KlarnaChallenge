package com.ramijemli.klarna.data.datasource.remote

import com.ramijemli.klarna.data.rest.ApiEndpoints
import com.ramijemli.klarna.data.rest.response.WeatherResponse
import io.reactivex.Single
import retrofit2.Retrofit

class WeatherRemoteDataSource : RemoteDataSource() {

    private var service: ApiEndpoints? = null

    override fun configEndpoint(retrofit: Retrofit) {
        service = retrofit.create(ApiEndpoints::class.java)
    }

    fun get(lat: Double, lng: Double): Single<WeatherResponse> {
        return service?.getWeather(lat, lng)!!
    }

}
