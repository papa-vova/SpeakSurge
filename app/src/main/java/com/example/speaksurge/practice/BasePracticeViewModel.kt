package com.example.speaksurge.practice

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * Abstract base ViewModel for all practice modes.
 * Provides common state management and navigation.
 */
abstract class BasePracticeViewModel : ViewModel() {
    
    // State management
    var currentIndex by mutableIntStateOf(BasePracticeState.DEFAULT_CURRENT_INDEX)
        private set
    
    var totalItems by mutableIntStateOf(0)
        protected set
    
    var isPlaying by mutableStateOf(false)
        private set
    
    var isRecording by mutableStateOf(false)
        private set
    
    val canGoPrevious by derivedStateOf { currentIndex > 0 }
    val canGoNext by derivedStateOf { currentIndex < totalItems - 1 }
    
    // Navigation actions
    fun previous() {
        if (canGoPrevious) {
            currentIndex--
            saveState()
        }
    }
    
    fun next() {
        if (canGoNext) {
            currentIndex++
            saveState()
        }
    }
    
    // Media actions (no persistence)
    fun togglePlay() {
        isPlaying = !isPlaying
        if (isPlaying) {
            isRecording = false // Stop recording if playing
        }
    }
    
    fun toggleRecord() {
        isRecording = !isRecording
        if (isRecording) {
            isPlaying = false // Stop playing if recording
        }
    }
    
    // Reset media state when exiting practice
    fun resetMediaState() {
        isPlaying = false
        isRecording = false
    }
    
    // State persistence (only for current index)
    protected abstract fun getDataStore(): PracticeDataStore

    private fun saveState() {
        viewModelScope.launch {
            getDataStore().saveCurrentIndex(currentIndex)
        }
    }
    
    protected fun restoreStateAndNotify(onRestored: () -> Unit) {
        viewModelScope.launch {
            currentIndex = getDataStore().currentIndex.first()
            onRestored()
        }
    }
} 
