package com.github.bkmbigo.basicapplication.data

import com.github.bkmbigo.basicapplication.domain.NoteRepository
import com.github.bkmbigo.basicapplication.domain.models.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(
    private val database: NoteDatabase
) : NoteRepository {
    override suspend fun getAllNotes(): Flow<List<Note>> = database.dao.getAllNotes()

    override suspend fun getNote(id: Long): Note = database.dao.getNote(id)

    override suspend fun insertNote(note: Note): Long = database.dao.insertNote(note)

    override suspend fun updateNote(note: Note) = database.dao.updateNote(note)

    override suspend fun deleteNote(note: Note) = database.dao.deleteNote(note)
}