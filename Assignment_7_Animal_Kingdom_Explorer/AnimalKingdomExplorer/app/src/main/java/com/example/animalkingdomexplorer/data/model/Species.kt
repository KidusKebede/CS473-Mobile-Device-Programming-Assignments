package com.example.animalkingdomexplorer.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "species")
data class Species(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String
)
