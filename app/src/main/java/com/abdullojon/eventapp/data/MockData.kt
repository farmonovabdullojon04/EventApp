package com.abdullojon.eventapp.data

import com.abdullojon.eventapp.domain.model.BroadcastEvent
import com.abdullojon.eventapp.domain.model.EventIconType

object MockData {

    val broadcastEvents = listOf(
        BroadcastEvent(
            id = "wifi",
            title = "Wi-Fi",
            action = "android.net.wifi.WIFI_STATE_CHANGED",
            isOn = true,
            statusText = "On",
            iconType = EventIconType.WIFI,
            description = "Broadcast when Wi-Fi state has changed.",
            totalOccurrences = 18,
            lastReceived = "2025-06-09 11:42:10"
        ),
        BroadcastEvent(
            id = "bluetooth",
            title = "Bluetooth",
            action = "android.bluetooth.adapter.action.STATE_CHANGED",
            isOn = true,
            statusText = "On",
            iconType = EventIconType.BLUETOOTH,
            description = "Broadcast when Bluetooth state has changed.",
            totalOccurrences = 12,
            lastReceived = "2025-06-09 07:41:00"
        ),
        BroadcastEvent(
            id = "airplane_mode",
            title = "Airplane Mode",
            action = "android.intent.action.AIRPLANE_MODE",
            isOn = false,
            statusText = "Off",
            iconType = EventIconType.AIRPLANE_MODE,
            description = "Broadcast when Airplane mode is toggled on or off.",
            totalOccurrences = 4,
            lastReceived = "2025-06-09 08:56:00"
        ),
        BroadcastEvent(
            id = "internet",
            title = "Internet",
            action = "android.net.conn.CONNECTIVITY_CHANGE",
            isOn = true,
            statusText = "On",
            iconType = EventIconType.INTERNET,
            description = "Broadcast when network connectivity state changes.",
            totalOccurrences = 25,
            lastReceived = "2025-06-09 11:42:15"
        ),
        BroadcastEvent(
            id = "location",
            title = "Location",
            action = "android.location.PROVIDERS_CHANGED",
            isOn = true,
            statusText = "On",
            iconType = EventIconType.LOCATION,
            description = "Broadcast when device location mode changes.",
            totalOccurrences = 8,
            lastReceived = "2025-06-09 09:18:00"
        ),
        BroadcastEvent(
            id = "charging",
            title = "Charging",
            action = "android.intent.action.ACTION_POWER_CONNECTED",
            isOn = true,
            statusText = "On",
            iconType = EventIconType.CHARGING,
            description = "Broadcast when external power is connected.",
            totalOccurrences = 14,
            lastReceived = "2025-06-09 12:15:00"
        ),
        BroadcastEvent(
            id = "headset",
            title = "Headset",
            action = "android.intent.action.HEADSET_PLUG",
            isOn = false,
            statusText = "Off",
            iconType = EventIconType.HEADSET,
            description = "Broadcast when headset is plugged or unplugged.",
            totalOccurrences = 3,
            lastReceived = "2025-06-07 18:20:00"
        ),
        BroadcastEvent(
            id = "battery_saver",
            title = "Battery Saver",
            action = "android.os.action.POWER_SAVE_MODE_CHANGED",
            isOn = false,
            statusText = "Off",
            iconType = EventIconType.BATTERY_SAVER,
            description = "Broadcast when battery saver mode is enabled or disabled.",
            totalOccurrences = 5,
            lastReceived = "2025-06-08 22:00:00"
        ),
        BroadcastEvent(
            id = "dnd",
            title = "Do Not Disturb",
            action = "android.app.action.INTERRUPTION_FILTER_CHANGED",
            isOn = false,
            statusText = "Off",
            iconType = EventIconType.DO_NOT_DISTURB,
            description = "Broadcast when Do Not Disturb mode changes.",
            totalOccurrences = 2,
            lastReceived = "2025-06-05 23:00:00"
        ),
        BroadcastEvent(
            id = "silent",
            title = "Silent",
            action = "android.media.RINGER_MODE_CHANGED",
            isOn = false,
            statusText = "Off",
            iconType = EventIconType.SILENT,
            description = "Broadcast when device ringer mode changes.",
            totalOccurrences = 7,
            lastReceived = "2025-06-08 10:15:00"
        ),
        BroadcastEvent(
            id = "nfc",
            title = "NFC",
            action = "android.nfc.action.ADAPTER_STATE_CHANGED",
            isOn = false,
            statusText = "Off",
            iconType = EventIconType.NFC,
            description = "Broadcast when NFC state changes.",
            totalOccurrences = 1,
            lastReceived = "2025-06-01 14:00:00"
        ),
        BroadcastEvent(
            id = "data_saver",
            title = "Data Saver",
            action = "android.net.action.RESTRICT_BACKGROUND_CHANGED",
            isOn = false,
            statusText = "Off",
            iconType = EventIconType.DATA_SAVER,
            description = "Broadcast when background data saver setting changes.",
            totalOccurrences = 0,
            lastReceived = "N/A"
        ),
        BroadcastEvent(
            id = "battery_low",
            title = "Battery Low",
            action = "ACTION_BATTERY_LOW",
            isOn = false,
            statusText = "Off",
            iconType = EventIconType.BATTERY_LOW,
            androidVersion = "API 21+",
            description = "Triggered when the battery level falls below a certain threshold (usually 15%).",
            totalOccurrences = 6,
            lastReceived = "2025-06-09 14:32:18"
        ),
        BroadcastEvent(
            id = "usb",
            title = "USB",
            action = "android.hardware.usb.action.USB_DEVICE_ATTACHED",
            isOn = true,
            statusText = "On",
            iconType = EventIconType.USB,
            description = "Broadcast when a USB device is attached.",
            totalOccurrences = 9,
            lastReceived = "2025-06-09 03:47:00"
        )
    )
}
