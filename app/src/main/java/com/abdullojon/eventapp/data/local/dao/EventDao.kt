package com.abdullojon.eventapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abdullojon.eventapp.data.local.entity.EventStateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT * FROM event_states ORDER BY orderIndex ASC")
    fun getAllEventStates(): Flow<List<EventStateEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateEventStates(states: List<EventStateEntity>)

    @Query("SELECT * FROM event_states WHERE eventKey = :key LIMIT 1")
    suspend fun getEventStateByKey(key: String): EventStateEntity?

    @Query("SELECT * FROM event_states WHERE `action` = :actionName LIMIT 1")
    suspend fun getEventStateByAction(actionName: String): EventStateEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEventState(state: EventStateEntity)
}
