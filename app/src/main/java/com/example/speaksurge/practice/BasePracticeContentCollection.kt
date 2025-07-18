package com.example.speaksurge.practice

/**
 * Base interface for practice content collections.
 * Handles navigation and item retrieval for practice content sets.
 */
interface BasePracticeContentCollection<T : BasePracticeContentItem> {
    /** Total number of items in the collection */
    fun size(): Int
    
    /** Get item at specific index */
    fun getItem(index: Int): T
    
    /** Get current navigation index */
    fun getCurrentIndex(): Int
    
    /** Set current navigation index */
    fun setCurrentIndex(index: Int)
    
    /** Get current item based on current index */
    fun getCurrentItem(): T
    
    /** Get next item, returns null if at end */
    fun next(): T?
    
    /** Get previous item, returns null if at start */
    fun previous(): T?
} 