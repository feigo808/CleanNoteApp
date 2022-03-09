package com.feiyatsu.cleannoteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.feiyatsu.cleannoteapp.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, RedPink, BabyBlue, Violet, LightGreen, LightGrey)
    }
}

class InvalidNoteException(message: String) : Exception(message)
