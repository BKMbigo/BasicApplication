package com.github.bkmbigo.basicapplication.presentation.home

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
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList: StateFlow<List<Note>> = _noteList

    init {
        populateList(Dispatchers.IO)
    }

    internal fun populateList(dispatcher: CoroutineDispatcher) {
        viewModelScope.launch(dispatcher) {
            noteRepository.getAllNotes().collect { list -> _noteList.value = list }
        }
    }
}