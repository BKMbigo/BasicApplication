package com.github.bkmbigo.basicapplication.domain

import androidx.lifecycle.LiveData
import com.github.bkmbigo.basicapplication.domain.models.Task
import com.github.bkmbigo.basicapplication.utils.Result

interface TasksRepository {
    suspend fun refreshTasks()
    fun observeTasks(): LiveData<Result<List<Task>>>
    suspend fun getTasks(forceUpdate: Boolean = false): Result<List<Task>>
    suspend fun getTask(taskId: Long, forceUpdate: Boolean = false): Result<Task>
    suspend fun saveTask(task: Task)
    suspend fun completeTask(task: Task)
    suspend fun completeTask(taskId: Long)
    suspend fun activateTask(task: Task)
    suspend fun activateTask(taskId: Long)
    suspend fun clearCompletedTasks()
    suspend fun deleteTask(taskId: Long)
    suspend fun deleteAllTasks()
}