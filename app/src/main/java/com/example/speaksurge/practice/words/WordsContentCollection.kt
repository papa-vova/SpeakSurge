package com.example.speaksurge.practice.words

import com.example.speaksurge.practice.BasePracticeContentCollection

/**
 * Content collection implementation for words practice.
 * Manages navigation and retrieval of word items.
 */
class WordsContentCollection : BasePracticeContentCollection<WordContentItem> {
    
    // Words data - in the future this would come from storage
    private val words = listOf(
        Word("Hello", "/həˈloʊ/", "Привет"),
        Word("Goodbye", "/ˌɡʊdˈbaɪ/", "До свидания"),
        Word("Thank you", "/ˈθæŋk juː/", "Спасибо"),
        Word("Please", "/pliːz/", "Пожалуйста"),
        Word("Sorry", "/ˈsɒri/", "Извините")
    )
    
    private var currentIndex = 0
    
    override fun size(): Int = words.size
    
    override fun getItem(index: Int): WordContentItem {
        if (index !in words.indices) {
            throw IndexOutOfBoundsException("Index $index is out of bounds for collection of size ${words.size}")
        }
        val word = words[index]
        return WordContentItem(
            id = word.sourceText,
            sourceText = word.sourceText,
            transcription = word.transcription,
            translation = word.translation
        )
    }
    
    override fun getCurrentIndex(): Int = currentIndex
    
    override fun setCurrentIndex(index: Int) {
        if (index in words.indices) {
            currentIndex = index
        }
    }
    
    override fun getCurrentItem(): WordContentItem = getItem(currentIndex)
    
    override fun next(): WordContentItem? {
        return if (currentIndex < words.size - 1) {
            getItem(currentIndex + 1)
        } else {
            null
        }
    }
    
    override fun previous(): WordContentItem? {
        return if (currentIndex > 0) {
            getItem(currentIndex - 1)
        } else {
            null
        }
    }
} 