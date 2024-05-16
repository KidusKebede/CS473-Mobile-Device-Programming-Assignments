package com.example.animalkingdomexplorer

import android.app.Application
import com.example.animalkingdomexplorer.data.database.AnimalKingdomDatabase
import com.example.animalkingdomexplorer.data.repository.AnimalRepository
import com.example.animalkingdomexplorer.data.repository.SpeciesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AnimalKingdomApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    private val animalDatabase by lazy { AnimalKingdomDatabase.getDatabase(this, applicationScope) }

    val speciesRepository by lazy { SpeciesRepository(animalDatabase.speciesDao()) }

    val animalRepository by lazy { AnimalRepository(animalDatabase.animalDao()) }
}
