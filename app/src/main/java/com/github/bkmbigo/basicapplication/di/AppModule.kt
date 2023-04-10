package com.github.bkmbigo.basicapplication.di

import android.app.Application
import androidx.room.Room
import com.github.bkmbigo.basicapplication.data.local.TasksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): TasksDatabase {
        return Room.databaseBuilder(
            application,
            TasksDatabase::class.java,
            "tasks_database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoroutinesDispatchers(): CoroutineDispatcher{
        return Dispatchers.IO
    }
}