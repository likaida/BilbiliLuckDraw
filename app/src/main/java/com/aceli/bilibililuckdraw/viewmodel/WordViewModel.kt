package com.aceli.bilibililuckdraw.viewmodel

import androidx.lifecycle.*
import com.aceli.bilibililuckdraw.database.AceRepository
import com.aceli.bilibililuckdraw.database.entity.WordEntity
import kotlinx.coroutines.launch

class WordViewModel(private val repository: AceRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<WordEntity>> = repository.allWords.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: WordEntity) = viewModelScope.launch {
        repository.insert(word)
    }
}

class WordViewModelFactory(private val repository: AceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}