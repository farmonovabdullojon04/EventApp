package com.abdullojon.eventapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.abdullojon.eventapp.presentation.main.MainScreen
import com.abdullojon.eventapp.service.BroadcastMonitoringService
import com.abdullojon.eventapp.ui.theme.EventAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        startMonitoringService()

        setContent {
            EventAppTheme {
                Navigator(MainScreen()) {
                    CurrentScreen()
                }
            }
        }
    }

    private fun startMonitoringService() {
        val serviceIntent = Intent(this, BroadcastMonitoringService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
    }
}
