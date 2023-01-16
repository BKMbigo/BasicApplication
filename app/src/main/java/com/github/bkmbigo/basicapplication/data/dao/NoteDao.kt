package com.github.bkmbigo.basicapplication.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.github.bkmbigo.basicapplication.domain.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(noteDto: Note): Long

    @Update
    suspend fun updateNote(noteDto: Note)

    @Delete
    suspend fun deleteNote(noteDto: Note)

    @Query("DELETE FROM note")
    suspend fun deleteAll()

    @Query("SELECT * FROM note ORDER BY priority")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNote(id: Long): Note
}