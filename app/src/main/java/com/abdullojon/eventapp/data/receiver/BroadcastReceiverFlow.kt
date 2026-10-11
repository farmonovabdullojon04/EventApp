package com.abdullojon.eventapp.data.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.core.content.ContextCompat
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun Context.observeBroadcasts(actions: List<String>): Flow<Intent> = callbackFlow {
    val receiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let { trySend(it) }
        }
    }
    val filter= IntentFilter().apply {
        actions.forEach { addAction(it) }
    }

    ContextCompat.registerReceiver(
        this@observeBroadcasts,
        receiver,
        filter,
        ContextCompat.RECEIVER_NOT_EXPORTED
    )
    awaitClose {
        try {
            unregisterReceiver(receiver)
        }catch (_: Exception) {}
    }

}