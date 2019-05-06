package com.ramijemli.klarna.data.rest.response

import com.squareup.moshi.Json

data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @field:Json(name = "currently") val weatherDetails: WeatherDetailsResponse
)





