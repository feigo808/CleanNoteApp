package com.feiyatsu.cleannoteapp.feature_note.domain.use_case

import com.feiyatsu.cleannoteapp.feature_note.domain.model.InvalidNoteException
import com.feiyatsu.cleannoteapp.feature_note.domain.model.Note
import com.feiyatsu.cleannoteapp.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        // Validate if the Note is valid. This is business logic, should not be in the VM, it belongs to this use case
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }

        repository.insertNote(note)
    }
}