package com.example.speaksurge.practice

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Interface for practice content display components
 * Each practice mode (words, dialogs, topics) will implement this
 */
interface PracticeContentComponent {
    
    /**
     * Display the current practice content
     * @param currentIndex Current item index
     * @param modifier Compose modifier for styling
     */
    @Composable
    fun DisplayContent(
        currentIndex: Int,
        modifier: Modifier = Modifier
    )
    
    /**
     * Get the total number of items in this practice session
     */
    fun getTotalItems(): Int
    
    /**
     * Check if the current item has audio available
     */
    fun hasAudio(currentIndex: Int): Boolean
    
    /**
     * Check if the current item has transcription available
     */
    fun hasTranscription(currentIndex: Int): Boolean
    
    /**
     * Check if the current item has translation available
     */
    fun hasTranslation(currentIndex: Int): Boolean
} 