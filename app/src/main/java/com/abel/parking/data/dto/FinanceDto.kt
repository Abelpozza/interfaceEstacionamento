package com.abel.parking.data.dto

data class FinanceDto(
    val totalAmount: Double,
    val entries: List<FinanceEntryDto>
)