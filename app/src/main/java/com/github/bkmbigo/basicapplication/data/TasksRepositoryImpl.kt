package com.github.bkmbigo.basicapplication.data

import androidx.lifecycle.LiveData
import com.github.bkmbigo.basicapplication.di.ApplicationModule
import com.github.bkmbigo.basicapplication.di.RepositoryModule
import com.github.bkmbigo.basicapplication.domain.TasksRepository
import com.github.bkmbigo.basicapplication.domain.models.Task
import com.github.bkmbigo.basicapplication.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    @ApplicationModule.LocalDataSource private val tasksLocalDataSource: TasksDataSource,
    @ApplicationModule.RemoteDataSource private val tasksRemoteDataSource: TasksDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TasksRepository {

    override suspend fun refreshTasks() {

    }

    override fun observeTasks(): LiveData<Result<List<Task>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTasks(forceUpdate: Boolean): Result<List<Task>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTask(taskId: Long, forceUpdate: Boolean): Result<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun saveTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun completeTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun completeTask(taskId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun activateTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun activateTask(taskId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun clearCompletedTasks() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(taskId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllTasks() {
        TODO("Not yet implemented")
    }
}