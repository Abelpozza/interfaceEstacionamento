package com.abel.parking.presentation.model

data class DashboardUiState(
    val spots: List<SpotUiModel> = emptyList(),
    val finance: FinanceUiModel = FinanceUiModel(
        totalAmount = 0.0,
        entries = emptyList()
    ),
    val isLoading: Boolean = false,
    val error: String? = null
)