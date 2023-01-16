package com.github.bkmbigo.basicapplication.presentation.noteeditor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.bkmbigo.basicapplication.domain.NoteRepository
import com.github.bkmbigo.basicapplication.domain.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteEditorViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NoteEditorState.NewNoteEditorState)
    val state: StateFlow<NoteEditorState> get() = _state

    fun saveNote(note: Note) {
        when (state.value) {
            is NoteEditorState.EditingNoteEditorState -> {
                updateNote(note)
            }

            NoteEditorState.NewNoteEditorState -> {
                insertNote(note)
            }
        }
    }

    private fun insertNote(note: Note, dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch(dispatcher) {
            noteRepository.insertNote(note)
        }
    }

    private fun updateNote(note: Note, dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch(dispatcher) {
            noteRepository.updateNote(note)
        }
    }
}