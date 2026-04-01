package com.abel.parking.presentation.model

import androidx.compose.ui.graphics.Color




data class SpotUiModel(
    val number: String,
    val spotId: Long,
    val isOccupied: Boolean,
    val plate: String? = null,
    val entryTime: String? = null,
    val carColor: Color? = null
)


data class FinanceEntryUiModel(
    val plate: String,
    val amount: Double,
    val exitTime: String
)

data class FinanceUiModel(
    val totalAmount: Double,
    val entries: List<FinanceEntryUiModel>
)