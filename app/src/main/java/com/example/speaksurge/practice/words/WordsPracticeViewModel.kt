package com.example.speaksurge.practice.words

import android.content.Context
import com.example.speaksurge.practice.BasePracticeViewModel
import com.example.speaksurge.practice.PracticeDataStore

/**
 * ViewModel for words practice mode
 */
class WordsPracticeViewModel(
    context: Context
) : BasePracticeViewModel() {
    
    // Content collection (data source)
    private val contentCollection = WordsContentCollection()
    
    // DataStore for state persistence (using ApplicationContext to avoid memory leaks)
    private val dataStore = PracticeDataStore(context.applicationContext)
    
    // Initialize total items and restore state
    init {
        totalItems = contentCollection.size()
        restoreStateAndNotify {
            // Synchronize content collection with restored index
            contentCollection.setCurrentIndex(currentIndex)
        }
    }
    
    // Current word as computed property from content collection
    val currentWord: Word get() = contentCollection.getCurrentItem().let { item ->
        Word(
            sourceText = item.sourceText,
            transcription = item.transcription ?: "",
            translation = item.translation ?: ""
        )
    }
    
    // Custom navigation methods that update content collection
    fun goToNext() {
        super.next()
        contentCollection.setCurrentIndex(currentIndex)
    }
    
    fun goToPrevious() {
        super.previous()
        contentCollection.setCurrentIndex(currentIndex)
    }
    
    // DataStore implementation
    override fun getDataStore(): PracticeDataStore = dataStore
} 