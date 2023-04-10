package com.github.bkmbigo.basicapplication.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.github.bkmbigo.basicapplication.data.TasksDataSource
import com.github.bkmbigo.basicapplication.domain.models.Task
import com.github.bkmbigo.basicapplication.utils.Result
import com.github.bkmbigo.basicapplication.utils.Result.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TasksLocalDatabaseSource @Inject constructor(
    private val tasksDatabase: TasksDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TasksDataSource {
    override fun observeTasks(): LiveData<Result<List<Task>>> =
        tasksDatabase.tasksDao.observeTasks().map { Success(it) }

    override fun observeTask(taskId: Long): LiveData<Result<Task>> =
        tasksDatabase.tasksDao.observeTaskByID(taskId).map { Success(it) }

    override suspend fun refreshTasks() {
        // NO-OP
    }

    override suspend fun refreshTask(taskId: String) {
        // NO-OP
    }

    override suspend fun getTasks(): Result<List<Task>> = withContext(dispatcher) {
        return@withContext try {
            Success(tasksDatabase.tasksDao.getTasks())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getTask(taskId: Long): Result<Task> = withContext(dispatcher) {
        try {
            val task = tasksDatabase.tasksDao.getTaskById(taskId)
            if (task != null) {
                return@withContext Success(task)
            } else {
                return@withContext Result.Error(Exception("Task Not Found!!"))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun saveTask(task: Task) = withContext(dispatcher) {
        tasksDatabase.tasksDao.insertTask(task)
    }

    override suspend fun completeTask(task: Task) = withContext(dispatcher) {
        completeTask(task.id)
    }

    override suspend fun completeTask(taskId: Long) = withContext(dispatcher) {
        tasksDatabase.tasksDao.updateCompleted(taskId, true)
    }

    override suspend fun activateTask(task: Task) = withContext(dispatcher) {
        activateTask(task.id)
    }

    override suspend fun activateTask(taskId: Long) = withContext(dispatcher) {
        tasksDatabase.tasksDao.updateCompleted(taskId, false)
    }

    override suspend fun clearCompletedTasks() = withContext<Unit>(dispatcher) {
        tasksDatabase.tasksDao.deleteCompletedTasks()
    }

    override suspend fun deleteTask(taskId: Long) = withContext<Unit>(dispatcher) {
        tasksDatabase.tasksDao.deleteTaskById(taskId)
    }

    override suspend fun deleteAllTasks() = withContext(dispatcher) {
        tasksDatabase.tasksDao.deleteAllTasks()
    }

}