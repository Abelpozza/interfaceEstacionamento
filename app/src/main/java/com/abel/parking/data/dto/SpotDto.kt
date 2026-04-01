package com.abel.parking.data.dto

data class SpotDto(
    val number: String,
    val spotId: Long,
    val occupied: Boolean,
    val plate: String? = null,
    val entryTime: String? = null
)