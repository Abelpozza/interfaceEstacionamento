package com.abel.parking.data.repository

import com.abel.parking.data.remote.RetrofitClient
import com.abel.parking.presentation.model.FinanceEntryUiModel
import com.abel.parking.presentation.model.FinanceUiModel
import com.abel.parking.presentation.model.SpotUiModel

class ParkingRepository {

    suspend fun getDashboard(): Pair<List<SpotUiModel>, FinanceUiModel> {
        val response = RetrofitClient.api.getDashboard()

        val spots = response.spots.map { dto ->
            SpotUiModel(
                number = dto.number,
                spotId = dto.spotId,
                isOccupied = dto.occupied,
                plate = dto.plate,
                entryTime = dto.entryTime
            )
        }

        val finance = FinanceUiModel(
            totalAmount = response.finance.totalAmount,
            entries = response.finance.entries.map { entry ->
                FinanceEntryUiModel(
                    plate = entry.plate,
                    amount = entry.amount,
                    exitTime = entry.exitTime
                )
            }
        )

        return spots to finance
    }
}