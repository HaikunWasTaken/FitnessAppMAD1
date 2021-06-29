package de.hdmstuttgart.fitnessapp.database.viewmodels

import androidx.lifecycle.*
import de.hdmstuttgart.fitnessapp.database.entities.Discipline
import de.hdmstuttgart.fitnessapp.database.entities.Exercise
import de.hdmstuttgart.fitnessapp.database.entities.TrainingsPlan
import de.hdmstuttgart.fitnessapp.database.entities.relations.TrainingsPlanWithExercises
import de.hdmstuttgart.fitnessapp.database.repositories.TrainingsPlanRepository
import kotlinx.coroutines.launch

class TrainingsPlanViewModel(private val repository: TrainingsPlanRepository) : ViewModel() {


    fun insertTrainingsPlan(trainingsPlan: TrainingsPlan): LiveData<Long> {
        val result = MutableLiveData<Long>()
        viewModelScope.launch {
            result.postValue(repository.insertTrainingsPlan(trainingsPlan))
        }
        return result
    }

    fun updateTrainingsPlan(trainingsPlan: TrainingsPlan) = viewModelScope.launch {
        repository.updateTrainingsPlan(trainingsPlan)
    }

    fun deleteTrainingsPlan(trainingsPlan: TrainingsPlan) = viewModelScope.launch {
        repository.deleteTrainingsPlan(trainingsPlan)
    }

    fun getTrainingsPlanById(id: Int): LiveData<TrainingsPlan> {
        val result = MutableLiveData<TrainingsPlan>()
        viewModelScope.launch {
            result.postValue(repository.getTrainingsPlanById(id))
        }
        return result
    }

    fun getAllTrainingsPlans(): LiveData<List<TrainingsPlan>> {
        val result = MutableLiveData<List<TrainingsPlan>>()
        viewModelScope.launch {
            result.postValue(repository.getAllTrainingsPlans().asLiveData().value)
        }
        return result
    }

    fun getExercisesForTrainingsPlan(trainingsPlan: TrainingsPlan): LiveData<List<TrainingsPlanWithExercises>> {
        val result = MutableLiveData<List<TrainingsPlanWithExercises>>()
        viewModelScope.launch {
            result.postValue(repository.getExercisesForTrainingsPlan(trainingsPlan).asLiveData().value)
        }
        return result
    }
}