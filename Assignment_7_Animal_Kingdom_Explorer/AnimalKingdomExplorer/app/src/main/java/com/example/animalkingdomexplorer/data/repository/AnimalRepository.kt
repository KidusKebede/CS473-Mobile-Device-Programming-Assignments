package com.example.animalkingdomexplorer.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.animalkingdomexplorer.data.dao.AnimalDao

import com.example.animalkingdomexplorer.data.model.Animal
import kotlinx.coroutines.flow.Flow

class AnimalRepository(private val animalDao: AnimalDao) {

    val allAnimals: Flow<List<Animal>> = animalDao.getAllAnimals()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getAnimalById(animalId: Int): LiveData<Animal> {
        return animalDao.getAnimalById(animalId)
    }

    suspend fun insert(animal: Animal) {
        animalDao.insertAnimal(animal)
    }
}