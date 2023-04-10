package com.github.bkmbigo.basicapplication.presentation.adapters

import com.github.bkmbigo.basicapplication.domain.models.Task

data class NoteAdapterItem(
    val note: Task,
    val onItemClicked: (Task) -> Unit
)