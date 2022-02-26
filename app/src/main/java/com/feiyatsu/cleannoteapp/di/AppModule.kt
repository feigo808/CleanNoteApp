package com.feiyatsu.cleannoteapp.di

import android.app.Application
import androidx.room.Room
import com.feiyatsu.cleannoteapp.feature_note.data.data_source.NoteDatabase
import com.feiyatsu.cleannoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.feiyatsu.cleannoteapp.feature_note.domain.repository.NoteRepository
import com.feiyatsu.cleannoteapp.feature_note.domain.use_case.DeleteNote
import com.feiyatsu.cleannoteapp.feature_note.domain.use_case.GetNotes
import com.feiyatsu.cleannoteapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository = NoteRepositoryImpl(db.noteDao)

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases =
        NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository)
        )
}
