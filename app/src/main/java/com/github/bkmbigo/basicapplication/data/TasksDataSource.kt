package com.github.bkmbigo.basicapplication.data

import androidx.lifecycle.LiveData
import com.github.bkmbigo.basicapplication.utils.Result
import com.github.bkmbigo.basicapplication.domain.models.Task

/**
 * Defines the entry points for accessing tasks data
 */
interface TasksDataSource {

    fun observeTasks(): LiveData<Result<List<Task>>>

    fun observeTask(taskId: Long): LiveData<Result<Task>>

    suspend fun refreshTasks()

    suspend fun refreshTask(taskId: String)

    suspend fun getTasks(): Result<List<Task>>

    suspend fun getTask(taskId: Long): Result<Task>

    suspend fun saveTask(task: Task)

    suspend fun completeTask(task: Task)

    suspend fun completeTask(taskId: Long)

    suspend fun activateTask(task: Task)

    suspend fun activateTask(taskId: Long)

    suspend fun clearCompletedTasks()

    suspend fun deleteTask(taskId: Long)

    suspend fun deleteAllTasks()
}