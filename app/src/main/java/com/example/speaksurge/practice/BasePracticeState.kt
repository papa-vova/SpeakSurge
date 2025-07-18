package com.example.speaksurge.practice

import androidx.datastore.preferences.core.intPreferencesKey

/**
 * State management for practice persistence.
 * Defines preferences keys and state restoration logic.
 */
object BasePracticeState {
    // Preferences keys for state persistence
    val CURRENT_INDEX = intPreferencesKey("practice_current_index")
    
    // Default values
    const val DEFAULT_CURRENT_INDEX = 0
} 