package com.abel.parking.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abel.parking.data.repository.ParkingRepository
import com.abel.parking.presentation.model.DashboardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ParkingViewModel : ViewModel() {

    private val repository = ParkingRepository()

    private val _uiState = MutableStateFlow(DashboardUiState(isLoading = true))
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboard()
    }

    fun loadDashboard() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            try {
                val (spots, finance) = repository.getDashboard()

                _uiState.value = DashboardUiState(
                    spots = spots,
                    finance = finance,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = DashboardUiState(
                    spots = emptyList(),
                    finance = _uiState.value.finance,
                    isLoading = false,
                    error = e.message ?: "Erro ao carregar dashboard"
                )
            }
        }
    }
}