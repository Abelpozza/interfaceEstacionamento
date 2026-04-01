package com.abel.parking.data.remote

import com.abel.parking.data.dto.DashboardResponseDto
import retrofit2.http.GET

interface ParkingApiService {

    @GET("dashboard")
    suspend fun getDashboard(): DashboardResponseDto
}