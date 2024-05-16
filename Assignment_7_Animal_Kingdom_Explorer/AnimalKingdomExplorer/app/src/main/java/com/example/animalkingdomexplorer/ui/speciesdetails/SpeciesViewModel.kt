package com.example.animalkingdomexplorer.ui.speciesdetails

import androidx.lifecycle.*
import com.example.animalkingdomexplorer.data.model.Species
import com.example.animalkingdomexplorer.data.repository.SpeciesRepository
import kotlinx.coroutines.launch

class SpeciesViewModel(private val repository: SpeciesRepository) : ViewModel() {

    val allSpecies: LiveData<List<Species>> = repository.allSpecies.asLiveData()

    fun insert(species: Species) = viewModelScope.launch {
        repository.insert(species)
    }

    fun getSpeciesById(speciesID: Int): LiveData<Species> {
        return repository.getSpeciesById(speciesID)
    }
}

class SpeciesViewModelFactory(private val repository: SpeciesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpeciesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SpeciesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


