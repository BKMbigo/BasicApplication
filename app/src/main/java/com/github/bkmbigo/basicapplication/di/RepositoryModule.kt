package com.github.bkmbigo.basicapplication.di

import com.github.bkmbigo.basicapplication.data.TasksDataSource
import com.github.bkmbigo.basicapplication.data.TasksRepositoryImpl
import com.github.bkmbigo.basicapplication.data.local.TasksLocalDatabaseSource
import com.github.bkmbigo.basicapplication.data.remote.TasksRemoteDataSource
import com.github.bkmbigo.basicapplication.domain.TasksRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @ApplicationModule.LocalDataSource
    @Binds
    @Singleton
    abstract fun bindLocalDatabaseSource(
        tasksLocalDatabaseSource: TasksLocalDatabaseSource
    ): TasksDataSource

    @ApplicationModule.RemoteDataSource
    @Binds
    @Singleton
    abstract fun bindRemoteDatabaseSource(
        tasksRemoteDataSource: TasksRemoteDataSource
    ): TasksDataSource

    @Binds
    @Singleton
    abstract fun bindTaskRepository(
        tasksRepositoryImpl: TasksRepositoryImpl
    ): TasksRepository
}