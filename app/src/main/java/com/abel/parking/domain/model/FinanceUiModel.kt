package com.abel.parking.domain.model

data class FinanceEntryUiModel(
    val plate: String,
    val amount: Double,
    val exitTime: String
)

data class FinanceUiModel(
    val totalAmount: Double,
    val entries: List<FinanceEntryUiModel>
)