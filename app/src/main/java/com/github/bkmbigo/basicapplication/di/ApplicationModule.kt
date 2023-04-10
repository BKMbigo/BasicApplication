package com.github.bkmbigo.basicapplication.di

import com.github.bkmbigo.basicapplication.data.TasksDataSource
import com.github.bkmbigo.basicapplication.data.remote.TasksRemoteDataSource
import com.github.bkmbigo.basicapplication.presentation.adapters.NoteAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object ApplicationModule {

    @Qualifier
    @Retention
    annotation class LocalDataSource

    @Qualifier
    @Retention
    annotation class RemoteDataSource

    @Provides
    @ActivityScoped
    fun getNoteAdapter(): NoteAdapter {
        return NoteAdapter()
    }

}