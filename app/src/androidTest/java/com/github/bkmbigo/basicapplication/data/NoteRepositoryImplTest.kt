package com.github.bkmbigo.basicapplication.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.bkmbigo.basicapplication.domain.models.Note
import com.github.bkmbigo.basicapplication.domain.models.NotePriority
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteRepositoryImplTest {
    private lateinit var noteRepositoryImpl: NoteRepositoryImpl
    private lateinit var db: NoteDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java).build()
        noteRepositoryImpl = NoteRepositoryImpl(db)
    }

    @After
    fun closeDb() {
        db.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun writeNoteAndReadInList() = runTest {
        val note = Note(0, "My First Note", "This is my first note", NotePriority.NORMAL)

        noteRepositoryImpl.insertNote(note)

        val notes = noteRepositoryImpl.getAllNotes().first()

        assertEquals("Size of retrieved list", 1, notes.size)
        assertEquals("Value of first Note", "My First Note", notes[0].title)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun writeAndUpdateNote() = runTest {
        val notes = listOf(
            Note(1, "My First Note", "This is my first note", NotePriority.NORMAL),
            Note(2, "Second Note", "This is a second note", NotePriority.URGENT),
            Note(3, "The Third Note", "This is the third note", NotePriority.IMPORTANT),
        )

        notes.forEach { noteRepositoryImpl.insertNote(it) }

        assertEquals(
            "Original Note Title",
            notes[1].title,
            noteRepositoryImpl.getNote(notes[1].id).title
        )
        assertEquals(
            "Original Note Priority",
            NotePriority.URGENT,
            noteRepositoryImpl.getNote(notes[1].id).priority
        )

        val updatedNote2 =
            Note(2, "New Second Note", "This is a second note", NotePriority.IMPORTANT)

        noteRepositoryImpl.updateNote(updatedNote2)
        assertEquals(
            "New Note Title",
            updatedNote2.title,
            noteRepositoryImpl.getNote(notes[1].id).title
        )
        assertEquals(
            "New Note Priority",
            NotePriority.IMPORTANT,
            noteRepositoryImpl.getNote(notes[1].id).priority
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteNoteAndCheckSize() = runTest {
        val notes = listOf(
            Note(1, "My First Note", "This is my first note", NotePriority.NORMAL),
            Note(2, "Second Note", "This is a second note", NotePriority.URGENT),
            Note(3, "The Third Note", "This is the third note", NotePriority.IMPORTANT),
        )

        notes.forEach { noteRepositoryImpl.insertNote(it) }

        assertEquals(
            "Original Size",
            3,
            noteRepositoryImpl.getAllNotes().first().size
        )

        noteRepositoryImpl.deleteNote(notes[1])

        assertEquals(
            "New Size",
            2,
            noteRepositoryImpl.getAllNotes().first().size
        )
    }
}