package com.github.bkmbigo.basicapplication.presentation.noteeditor

sealed class NoteEditorState {
    object NewNoteEditorState : NoteEditorState()
    data class EditingNoteEditorState(val noteId: Int) : NoteEditorState()
}
