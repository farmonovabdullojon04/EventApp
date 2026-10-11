package com.abdullojon.eventapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_states")
data class EventStateEntity(
    @PrimaryKey
    val eventKey: String,
    val title: String,
    val action: String,
    val isOn: Boolean,
    val statusText: String,
    val iconType: String,
    val androidVersion: String = "API 21+",
    val description: String,
    val totalOccurrences: Int = 0,
    val lastReceived: String = "N/A",
    val orderIndex: Int = 0
)
