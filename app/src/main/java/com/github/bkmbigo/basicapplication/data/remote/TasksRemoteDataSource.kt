package com.github.bkmbigo.basicapplication.data.remote

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.github.bkmbigo.basicapplication.data.TasksDataSource
import com.github.bkmbigo.basicapplication.domain.models.Task
import com.github.bkmbigo.basicapplication.utils.Result
import kotlinx.coroutines.delay
import javax.inject.Inject

class TasksRemoteDataSource @Inject constructor() : TasksDataSource {

    private val SERVICE_LATENCY_TIME_IN_MILLIS = 2000L

    private var TASKS_SERVICE_DATA = LinkedHashMap<Long, Task>(2)

    private val observableTasks = MutableLiveData<Result<List<Task>>>()

    init {
        addTask(
            1,
            "Build a simple Android Application",
            "Build a simple Android Task Management Application"
        )
        addTask(
            2,
            "Test the new android application",
            "Generate appropriate tests for the new application"
        )
        addTask(
            3,
            "Prepare a presentation",
            "Prepare the presentation to share with the audience",
            true
        )
    }

    private fun addTask(
        id: Long,
        title: String,
        description: String,
        isCompleted: Boolean = false
    ) {
        val newTask = Task(id, title, description, isCompleted)
        TASKS_SERVICE_DATA[newTask.id] = newTask
    }

    override fun observeTasks(): LiveData<Result<List<Task>>> {
        TODO()
    }

    override fun observeTask(taskId: Long): LiveData<Result<Task>> {
        return observableTasks.map { tasks ->
            when (tasks) {
                is Result.Error -> Result.Error(tasks.exception)
                Result.Loading -> Result.Loading
                is Result.Success -> {
                    val task = tasks.data.firstOrNull { it.id == taskId }
                        ?: return@map Result.Error(Exception("Not Found"))
                    Result.Success(task)
                }
            }
        }
    }

    @SuppressLint("NullSafeMutableLiveData")
    override suspend fun refreshTasks() {
        observableTasks.value = getTasks()
    }

    override suspend fun refreshTask(taskId: String) {
        refreshTasks()
    }

    override suspend fun getTasks(): Result<List<Task>> {
        val tasks = TASKS_SERVICE_DATA.values.toList()
        delay(SERVICE_LATENCY_TIME_IN_MILLIS)
        return Result.Success(tasks)
    }

    override suspend fun getTask(taskId: Long): Result<Task> {
        delay(SERVICE_LATENCY_TIME_IN_MILLIS)
        TASKS_SERVICE_DATA[taskId]?.let { return Result.Success(it) }
        return Result.Error(Exception("Task not found"))
    }

    override suspend fun saveTask(task: Task) {
        TASKS_SERVICE_DATA[task.id] = task
    }

    override suspend fun completeTask(task: Task) {
        val completedTask = TASKS_SERVICE_DATA[task.id]
        TASKS_SERVICE_DATA[task.id] = completedTask!!.copy(isCompleted = true)
    }

    override suspend fun completeTask(taskId: Long) {
        val completedTask = TASKS_SERVICE_DATA[taskId]
        TASKS_SERVICE_DATA[taskId] = completedTask!!.copy(isCompleted = true)
    }

    override suspend fun activateTask(task: Task) {
        val completedTask = TASKS_SERVICE_DATA[task.id]
        TASKS_SERVICE_DATA[task.id] = completedTask!!.copy(isCompleted = true)
    }

    override suspend fun activateTask(taskId: Long) {
        val completedTask = TASKS_SERVICE_DATA[taskId]
        TASKS_SERVICE_DATA[taskId] = completedTask!!.copy(isCompleted = true)
    }

    override suspend fun clearCompletedTasks() {
        TASKS_SERVICE_DATA = TASKS_SERVICE_DATA.filterValues {
            !it.isCompleted
        } as LinkedHashMap<Long, Task>
    }

    override suspend fun deleteTask(taskId: Long) {
        TASKS_SERVICE_DATA.remove(taskId)
    }

    override suspend fun deleteAllTasks() {
        TASKS_SERVICE_DATA.clear()
    }
}