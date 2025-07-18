package com.example.speaksurge.practice

/**
 * Base interface for individual practice content items.
 * Defines common properties that all practice content items must implement.
 */
interface BasePracticeContentItem {
    /** Unique identifier for the content item */
    val id: String
    
    /** Source language text to display (e.g., word, dialog line, topic title) */
    val sourceText: String
    
    /** Phonetic transcription (optional) */
    val transcription: String?
    
    /** Translation (optional) */
    val translation: String?
} 