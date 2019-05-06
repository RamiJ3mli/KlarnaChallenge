package com.ramijemli.klarna.data.rest

import com.ramijemli.klarna.data.rest.response.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndpoints {

    @GET(Config.FORECAST + "{lat},{lng}")
    fun getWeather(@Path("lat") lat: Double, @Path("lng") lng: Double): Single<WeatherResponse>

}
