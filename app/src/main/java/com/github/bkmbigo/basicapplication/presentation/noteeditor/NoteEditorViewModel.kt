package com.github.bkmbigo.basicapplication.presentation.noteeditor

import androidx.lifecycle.ViewModel
import com.github.bkmbigo.basicapplication.domain.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NoteEditorViewModel @Inject constructor(
    private val tasksRepository: TasksRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NoteEditorState.NewNoteEditorState)
    val state: StateFlow<NoteEditorState> get() = _state


}