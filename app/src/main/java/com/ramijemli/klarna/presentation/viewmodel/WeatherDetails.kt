package com.ramijemli.klarna.presentation.viewmodel

data class WeatherDetails(val time: String,
                          val summary: String,
                          val icon: String,
                          val precipIntensity: Byte,
                          val precipProbability: Byte,
                          val temperature: Float,
                          val apparentTemperature: Float,
                          val dewPoint: Float,
                          val humidity: Float,
                          val pressure: Float,
                          val windSpeed: Float,
                          val windGust: Float,
                          val windBearing: Int,
                          val cloudCover: Float,
                          val uvIndex: Byte,
                          val visibility: Float,
                          val ozone: Float)
