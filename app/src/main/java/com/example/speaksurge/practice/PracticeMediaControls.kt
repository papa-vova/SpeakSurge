package com.example.speaksurge.practice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.speaksurge.R

/**
 * Reusable media controls for practice screens
 */
@Composable
fun PracticeMediaControls(
    isPlaying: Boolean,
    isRecording: Boolean,
    canGoPrevious: Boolean,
    canGoNext: Boolean,
    onPlayPause: () -> Unit,
    onRecordStop: () -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Previous button (smaller, secondary)
        FloatingActionButton(
            onClick = onPrevious,
            modifier = Modifier.size(56.dp),
            shape = CircleShape,
            containerColor = if (canGoPrevious) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = if (canGoPrevious) 2.dp else 0.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = stringResource(R.string.previous),
                modifier = Modifier.size(24.dp),
                tint = if (canGoPrevious) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
        
        // Play/Pause button (prominent)
        FloatingActionButton(
            onClick = onPlayPause,
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            containerColor = if (isPlaying) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) stringResource(R.string.pause) else stringResource(R.string.play),
                modifier = Modifier.size(32.dp),
                tint = Color.White
            )
        }
        
        // Record/Stop button (prominent)
        FloatingActionButton(
            onClick = onRecordStop,
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            containerColor = if (isRecording) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary
        ) {
            Icon(
                imageVector = if (isRecording) Icons.Default.Stop else Icons.Default.Mic,
                contentDescription = if (isRecording) stringResource(R.string.stop_recording) else stringResource(R.string.record),
                modifier = Modifier.size(32.dp),
                tint = Color.White
            )
        }
        
        // Next button (smaller, secondary)
        FloatingActionButton(
            onClick = onNext,
            modifier = Modifier.size(56.dp),
            shape = CircleShape,
            containerColor = if (canGoNext) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = if (canGoNext) 2.dp else 0.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = stringResource(R.string.next),
                modifier = Modifier.size(24.dp),
                tint = if (canGoNext) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
} 