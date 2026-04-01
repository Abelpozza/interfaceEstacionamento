package com.abel.parking.data.dto

data class FinanceEntryDto(
    val plate: String,
    val amount: Double,
    val exitTime: String
)