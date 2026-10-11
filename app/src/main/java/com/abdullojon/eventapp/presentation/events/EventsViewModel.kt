package com.abdullojon.eventapp.presentation.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdullojon.eventapp.domain.model.BroadcastEvent
import com.abdullojon.eventapp.domain.repository.BroadcastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val repository: BroadcastRepository,
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.initializeDefaultEventsIfEmpty()
        }
    }

    val events: StateFlow<List<BroadcastEvent>> = repository.getBroadcastEvents()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun toggleEvent(eventId: String) {
        viewModelScope.launch {
            repository.toggleEvent(eventId)
        }
    }
}