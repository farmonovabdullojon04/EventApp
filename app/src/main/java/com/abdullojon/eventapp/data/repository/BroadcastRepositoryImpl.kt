package com.abdullojon.eventapp.data.repository

import android.content.Context
import android.content.Intent
import com.abdullojon.eventapp.data.MockData
import com.abdullojon.eventapp.data.local.dao.EventDao
import com.abdullojon.eventapp.data.local.entity.EventStateEntity
import com.abdullojon.eventapp.domain.model.BroadcastEvent
import com.abdullojon.eventapp.domain.model.EventIconType
import com.abdullojon.eventapp.domain.repository.BroadcastRepository
import com.abdullojon.eventapp.util.TtsManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class BroadcastRepositoryImpl @Inject constructor(
    private val eventDao: EventDao,
    private val ttsManager: TtsManager
) : BroadcastRepository {

    override fun getBroadcastEvents(): Flow<List<BroadcastEvent>> {
        return eventDao.getAllEventStates().map { entities ->
            entities.sortedBy { it.orderIndex }.map { entity ->
                BroadcastEvent(
                    id = entity.eventKey,
                    title = entity.title,
                    action = entity.action,
                    isOn = entity.isOn,
                    statusText = entity.statusText,
                    iconType = try { EventIconType.valueOf(entity.iconType) } catch (_: Exception) { EventIconType.WIFI },
                    androidVersion = entity.androidVersion,
                    description = entity.description,
                    totalOccurrences = entity.totalOccurrences,
                    lastReceived = entity.lastReceived
                )
            }
        }
    }

    override suspend fun processIntent(
        context: Context,
        intent: Intent
    ) {
        val action = intent.action ?: return
        val existingState = eventDao.getEventStateByAction(action)
            ?: eventDao.getEventStateByKey(getEventKeyForAction(action))
            ?: return

        // Only process and announce if monitoring for this event is turned ON in the app
        if (!existingState.isOn) {
            return
        }

        val now = System.currentTimeMillis()
        val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val formattedDateTime = dateTimeFormat.format(Date(now))

        val description = getTriggerDescription(intent, existingState)

        // Announce event via TextToSpeech
        ttsManager.speak(description)

        // Update event state (occurrences count & last received timestamp)
        val updatedState = existingState.copy(
            totalOccurrences = existingState.totalOccurrences + 1,
            lastReceived = formattedDateTime
        )
        eventDao.updateEventState(updatedState)
    }

    override suspend fun toggleEvent(eventId: String) {
        val existingState = eventDao.getEventStateByKey(eventId) ?: return
        val newIsOn = !existingState.isOn
        val newStatusText = if (newIsOn) "On" else "Off"
        val now = System.currentTimeMillis()

        val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val formattedDateTime = dateTimeFormat.format(Date(now))

        val updatedState = existingState.copy(
            isOn = newIsOn,
            statusText = newStatusText,
            lastReceived = formattedDateTime
        )
        eventDao.updateEventState(updatedState)

        // Speak toggle status change
        val toggleText = if (newIsOn) {
            "${existingState.title} monitoring turned on"
        } else {
            "${existingState.title} monitoring turned off"
        }
        ttsManager.speak(toggleText)
    }

    override suspend fun initializeDefaultEventsIfEmpty() {
        val wifiState = eventDao.getEventStateByKey("wifi")
        if (wifiState == null) {
            val initialEntities = MockData.broadcastEvents.mapIndexed { index, event ->
                EventStateEntity(
                    eventKey = event.id,
                    title = event.title,
                    action = event.action,
                    isOn = event.isOn,
                    statusText = event.statusText,
                    iconType = event.iconType.name,
                    androidVersion = event.androidVersion,
                    description = event.description,
                    totalOccurrences = event.totalOccurrences,
                    lastReceived = event.lastReceived,
                    orderIndex = index
                )
            }
            eventDao.insertOrUpdateEventStates(initialEntities)
        }
    }

    private fun getEventKeyForAction(action: String): String {
        return when (action) {
            Intent.ACTION_BATTERY_LOW, Intent.ACTION_BATTERY_OKAY -> "battery_low"
            Intent.ACTION_POWER_CONNECTED, Intent.ACTION_POWER_DISCONNECTED -> "charging"
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> "airplane_mode"
            Intent.ACTION_HEADSET_PLUG -> "headset"
            "android.net.wifi.WIFI_STATE_CHANGED" -> "wifi"
            "android.bluetooth.adapter.action.STATE_CHANGED" -> "bluetooth"
            "android.net.conn.CONNECTIVITY_CHANGE" -> "internet"
            "android.location.PROVIDERS_CHANGED" -> "location"
            "android.os.action.POWER_SAVE_MODE_CHANGED" -> "battery_saver"
            "android.media.RINGER_MODE_CHANGED" -> "silent"
            "android.app.action.INTERRUPTION_FILTER_CHANGED" -> "dnd"
            "android.hardware.usb.action.USB_DEVICE_ATTACHED" -> "usb"
            else -> ""
        }
    }

    private fun getTriggerDescription(intent: Intent, state: EventStateEntity): String {
        return when (intent.action) {
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val mode = intent.getBooleanExtra("state", false)
                if (mode) "Airplane mode enabled" else "Airplane mode disabled"
            }
            Intent.ACTION_POWER_CONNECTED -> "Charger plugged in"
            Intent.ACTION_POWER_DISCONNECTED -> "Charger unplugged"
            Intent.ACTION_BATTERY_LOW -> "Battery level low"
            Intent.ACTION_HEADSET_PLUG -> {
                val plugState = intent.getIntExtra("state", -1)
                if (plugState == 1) "Headset plugged in" else "Headset unplugged"
            }
            "android.net.wifi.WIFI_STATE_CHANGED" -> "Wi-Fi state changed"
            "android.bluetooth.adapter.action.STATE_CHANGED" -> "Bluetooth state changed"
            "android.net.conn.CONNECTIVITY_CHANGE" -> "Internet connection changed"
            "android.location.PROVIDERS_CHANGED" -> "Location setting changed"
            "android.os.action.POWER_SAVE_MODE_CHANGED" -> "Battery saver mode changed"
            "android.media.RINGER_MODE_CHANGED" -> "Ringer mode changed"
            "android.app.action.INTERRUPTION_FILTER_CHANGED" -> "Do not disturb mode changed"
            "android.hardware.usb.action.USB_DEVICE_ATTACHED" -> "USB device attached"
            else -> "${state.title} event triggered"
        }
    }
}
