package com.abdullojon.eventapp.di

import android.content.Context
import androidx.room.Room
import com.abdullojon.eventapp.data.local.dao.EventDao
import com.abdullojon.eventapp.data.local.database.AppDatabase
import com.abdullojon.eventapp.data.repository.BroadcastRepositoryImpl
import com.abdullojon.eventapp.domain.repository.BroadcastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "event_app_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideEventDao(db: AppDatabase): EventDao=db.eventDao()

    @Provides
    @Singleton
    fun provideBroadcastRepository(impl: BroadcastRepositoryImpl): BroadcastRepository=impl
}