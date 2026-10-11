package com.abdullojon.eventapp.domain.model

enum class EventIconType {
    WIFI,
    BLUETOOTH,
    AIRPLANE_MODE,
    INTERNET,
    LOCATION,
    CHARGING,
    HEADSET,
    BATTERY_SAVER,
    DO_NOT_DISTURB,
    SILENT,
    NFC,
    DATA_SAVER,
    BATTERY_LOW,
    USB,
    POWER_CONNECTED,
    SCREEN_ON,
    TIME_CHANGED,
    LOCALE_CHANGED
}

data class BroadcastEvent(
    val id: String,
    val title: String,
    val action: String,
    val isOn: Boolean,
    val statusText: String,
    val iconType: EventIconType,
    val androidVersion: String = "API 21+",
    val description: String,
    val totalOccurrences: Int = 0,
    val lastReceived: String = "N/A"
)
