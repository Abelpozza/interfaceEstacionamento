package com.abel.parking.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitClient {private const val BASE_URL = "http://10.0.2.2:3003/"
    val api: ParkingApiService by lazy {Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ParkingApiService::class.java) }
}