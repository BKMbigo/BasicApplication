package com.github.bkmbigo.basicapplication.presentation.adapters

import com.github.bkmbigo.basicapplication.domain.models.Note

data class NoteAdapterItem(
    val note: Note,
    val onItemClicked: (Note) -> Unit
)