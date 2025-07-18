package com.example.speaksurge.practice.words

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speaksurge.practice.PracticeMediaControls
import com.example.speaksurge.practice.PracticeProgressBar

/**
 * Words practice screen using the new architecture
 */
@Composable
fun WordsPracticeScreen(
    modifier: Modifier = Modifier,
    providedViewModel: WordsPracticeViewModel? = null
) {
    val context = LocalContext.current
    val actualViewModel = providedViewModel ?: viewModel { 
        WordsPracticeViewModel(context) 
    }
    
    // Collect state from ViewModel - these are State objects, so we access them directly
    val currentIndex = actualViewModel.currentIndex
    val isPlaying = actualViewModel.isPlaying
    val isRecording = actualViewModel.isRecording
    val totalItems = actualViewModel.totalItems
    val canGoPrevious = actualViewModel.canGoPrevious
    val canGoNext = actualViewModel.canGoNext
    
    // Calculate progress
    val progress = (currentIndex + 1).toFloat() / totalItems.toFloat()
    
    // Create content component with viewModel
    val contentComponent = WordsContentComponent(viewModel = actualViewModel)
    
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top content area - takes all available space
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Display content using the content component
            contentComponent.DisplayContent(
                currentIndex = currentIndex,
                modifier = Modifier.fillMaxSize()
            )
        }
        
        // Bottom fixed area - progress + media controls
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Progress bar - fixed height
            PracticeProgressBar(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            
            // Media Controls - fixed height
            PracticeMediaControls(
                isPlaying = isPlaying,
                isRecording = isRecording,
                canGoPrevious = canGoPrevious,
                canGoNext = canGoNext,
                onPlayPause = { actualViewModel.togglePlay() },
                onRecordStop = { actualViewModel.toggleRecord() },
                onPrevious = { actualViewModel.goToPrevious() },
                onNext = { actualViewModel.goToNext() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )
        }
    }
} 