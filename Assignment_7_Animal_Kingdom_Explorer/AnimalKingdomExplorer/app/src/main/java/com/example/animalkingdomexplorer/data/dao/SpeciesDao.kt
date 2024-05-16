package com.example.animalkingdomexplorer.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animalkingdomexplorer.data.model.Species
import kotlinx.coroutines.flow.Flow

@Dao
interface SpeciesDao {
    @Query("SELECT * FROM species")
    fun getAllSpecies(): Flow<List<Species>>

    @Query("DELETE FROM species")
    suspend fun deleteAll()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSpecies(species: Species)

    @Query("SELECT * FROM species WHERE id = :speciesID")
    fun getSpeciesById(speciesID: Int): LiveData<Species>
}
