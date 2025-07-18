package com.example.speaksurge.practice.words

import com.example.speaksurge.practice.BasePracticeContentItem

/**
 * Content item implementation for words practice.
 * Immutable data class representing a single word item.
 */
data class WordContentItem(
    override val id: String,
    override val sourceText: String,
    override val transcription: String?,
    override val translation: String?
) : BasePracticeContentItem 