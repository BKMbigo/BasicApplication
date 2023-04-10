package com.github.bkmbigo.basicapplication.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.bkmbigo.basicapplication.domain.TasksRepository
import com.github.bkmbigo.basicapplication.domain.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tasksRepository: TasksRepository
) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Task>>(emptyList())
    val noteList: StateFlow<List<Task>> = _noteList

}