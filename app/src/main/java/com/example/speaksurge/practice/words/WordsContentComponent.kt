package com.example.speaksurge.practice.words

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speaksurge.practice.PracticeContentComponent

/**
 * Content component for words practice mode
 */
class WordsContentComponent(
    private val viewModel: WordsPracticeViewModel
) : PracticeContentComponent {
    
    override fun getTotalItems(): Int = viewModel.totalItems
    
    override fun hasAudio(currentIndex: Int): Boolean = true // TODO: Implement actual check
    
    override fun hasTranscription(currentIndex: Int): Boolean = true
    
    override fun hasTranslation(currentIndex: Int): Boolean = true
    
    @Composable
    override fun DisplayContent(
        currentIndex: Int,
        modifier: Modifier
    ) {
        val currentWord = viewModel.currentWord
        
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top content area - takes all available space
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Word Display Section
                WordDisplaySection(
                    word = currentWord,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                
                // Translation Section
                TranslationSection(
                    word = currentWord,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }
    }
    
    @Composable
    private fun WordDisplaySection(
        word: Word,
        modifier: Modifier = Modifier
    ) {
        Card(
            modifier = modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Word text
                Text(
                    text = word.sourceText,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                
                // Transcription
                Text(
                    text = word.transcription,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
    
    @Composable
    private fun TranslationSection(
        word: Word,
        modifier: Modifier = Modifier
    ) {
        Card(
            modifier = modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = word.translation,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
} 