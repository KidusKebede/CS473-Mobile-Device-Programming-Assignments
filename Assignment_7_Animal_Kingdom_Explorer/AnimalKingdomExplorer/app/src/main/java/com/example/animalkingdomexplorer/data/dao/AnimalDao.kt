package com.example.animalkingdomexplorer.data.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animalkingdomexplorer.data.model.Animal
import kotlinx.coroutines.flow.Flow
@Dao
interface AnimalDao {
    @Query("SELECT * FROM animals")
    fun getAllAnimals(): Flow<List<Animal>>

    @Query("DELETE FROM animals")
    suspend fun deleteAll()


     @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAnimal(animal: Animal)

    @Query("SELECT * FROM animals WHERE id = :animalID")
    fun getAnimalById(animalID: Int): LiveData<Animal>
}
