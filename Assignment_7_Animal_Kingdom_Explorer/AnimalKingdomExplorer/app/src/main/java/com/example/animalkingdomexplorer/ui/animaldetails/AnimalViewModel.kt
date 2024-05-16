package com.example.animalkingdomexplorer.ui.animaldetails

import androidx.lifecycle.*
import com.example.animalkingdomexplorer.data.model.Animal
import com.example.animalkingdomexplorer.data.repository.AnimalRepository
import kotlinx.coroutines.launch

class AnimalViewModel(private val repository: AnimalRepository) : ViewModel() {

    val allAnimals: LiveData<List<Animal>> = repository.allAnimals.asLiveData()

    fun insert(animal: Animal) = viewModelScope.launch {
        repository.insert(animal)
    }

    fun getAnimalById(animalId: Int): LiveData<Animal> {
        return repository.getAnimalById(animalId)
    }
}

class AnimalViewModelFactory(private val repository: AnimalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


