package com.example.animalkingdomexplorer.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.animalkingdomexplorer.data.dao.AnimalDao
import com.example.animalkingdomexplorer.data.dao.SpeciesDao
import com.example.animalkingdomexplorer.data.model.Animal
import com.example.animalkingdomexplorer.data.model.Species
import kotlinx.coroutines.flow.Flow



class SpeciesRepository(private val speciesDao: SpeciesDao) {

    val allSpecies: Flow<List<Species>> = speciesDao.getAllSpecies()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getSpeciesById(speciesID: Int): LiveData<Species> {
        return speciesDao.getSpeciesById(speciesID)
    }

    suspend fun insert(species: Species) {
        speciesDao.insertSpecies(species)
    }
}