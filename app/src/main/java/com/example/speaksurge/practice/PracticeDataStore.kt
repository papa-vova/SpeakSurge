package com.example.speaksurge.practice

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * DataStore provider for practice state persistence.
 * Manages saving and restoring practice state across app sessions.
 */
class PracticeDataStore(private val context: Context) {
    
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "practice_state")
    }
    
    /**
     * Get the current practice index from DataStore
     */
    val currentIndex: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[BasePracticeState.CURRENT_INDEX] ?: BasePracticeState.DEFAULT_CURRENT_INDEX
    }
    
    /**
     * Save the current practice index to DataStore
     */
    suspend fun saveCurrentIndex(index: Int) {
        context.dataStore.edit { preferences ->
            preferences[BasePracticeState.CURRENT_INDEX] = index
        }
    }
} 