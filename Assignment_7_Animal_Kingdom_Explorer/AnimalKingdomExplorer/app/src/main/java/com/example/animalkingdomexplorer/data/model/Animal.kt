package com.example.animalkingdomexplorer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animals")
data class Animal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val habitat: String,
    val diet: String
)
