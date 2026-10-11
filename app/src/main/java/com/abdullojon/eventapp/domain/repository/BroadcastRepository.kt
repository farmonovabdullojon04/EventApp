package com.abdullojon.eventapp.domain.repository

import android.content.Context
import android.content.Intent
import com.abdullojon.eventapp.domain.model.BroadcastEvent
import kotlinx.coroutines.flow.Flow

interface BroadcastRepository {
    fun getBroadcastEvents(): Flow<List<BroadcastEvent>>
    suspend fun toggleEvent(eventId: String)
    suspend fun processIntent(context: Context, intent: Intent)
    suspend fun initializeDefaultEventsIfEmpty()
}
