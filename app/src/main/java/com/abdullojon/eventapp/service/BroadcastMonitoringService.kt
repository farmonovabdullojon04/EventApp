package com.abdullojon.eventapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.abdullojon.eventapp.data.receiver.observeBroadcasts
import com.abdullojon.eventapp.domain.repository.BroadcastRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BroadcastMonitoringService : Service() {

    @Inject
    lateinit var broadcastRepository: BroadcastRepository

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        startForegroundServiceNotification()
        startListeningToBroadcasts()
    }

    private fun startForegroundServiceNotification() {
        val channelId = "broadcast_monitor_channel"
        val notificationManager = getSystemService(NotificationManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "System Event Monitoring",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Monitoring System Events")
            .setContentText("Listening to broadcast events in real-time.")
            .setSmallIcon(android.R.drawable.stat_notify_sync)
            .setOngoing(true)
            .build()

        startForeground(1001, notification)
    }

    private fun startListeningToBroadcasts() {
        val actions = listOf(
            Intent.ACTION_BATTERY_LOW,
            Intent.ACTION_BATTERY_OKAY,
            Intent.ACTION_POWER_CONNECTED,
            Intent.ACTION_POWER_DISCONNECTED,
            Intent.ACTION_AIRPLANE_MODE_CHANGED,
            Intent.ACTION_HEADSET_PLUG,
            "android.net.wifi.WIFI_STATE_CHANGED",
            "android.bluetooth.adapter.action.STATE_CHANGED",
            "android.net.conn.CONNECTIVITY_CHANGE",
            "android.location.PROVIDERS_CHANGED",
            "android.os.action.POWER_SAVE_MODE_CHANGED",
            "android.media.RINGER_MODE_CHANGED",
            "android.app.action.INTERRUPTION_FILTER_CHANGED",
            "android.hardware.usb.action.USB_DEVICE_ATTACHED"
        )

        serviceScope.launch {
            broadcastRepository.initializeDefaultEventsIfEmpty()
            observeBroadcasts(actions).collectLatest { intent ->
                broadcastRepository.processIntent(applicationContext, intent)
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
}