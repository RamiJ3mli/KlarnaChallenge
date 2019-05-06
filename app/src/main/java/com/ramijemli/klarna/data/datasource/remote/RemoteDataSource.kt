package com.ramijemli.klarna.data.datasource.remote

import com.ramijemli.klarna.data.rest.Config
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class RemoteDataSource {

    companion object {
        private val httpClient = OkHttpClient.Builder()
                .build()
    }

    init {
        this.configure(Config.BASE_URL)
    }

    private fun configure(baseUrl: String) {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient)
                .build()

        this.configEndpoint(retrofit)
    }

    protected abstract fun configEndpoint(retrofit: Retrofit)

}
