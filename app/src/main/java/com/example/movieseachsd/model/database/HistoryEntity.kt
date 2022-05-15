package com.example.movieseachsd.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey (autoGenerate = true) val id: Long,
    val movieTitle: String,
    val releaseDate: String,
    val runtime: Int
)