package com.ramijemli.klarna.presentation.viewmodel

data class Weather(val latitude: Double,
                   val longitude: Double,
                   val timezone: String,
                   val details: WeatherDetails)
