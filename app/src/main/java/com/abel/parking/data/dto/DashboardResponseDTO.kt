package com.abel.parking.data.dto

data class DashboardResponseDto(
    val spots: List<SpotDto>,
    val finance: FinanceDto
)