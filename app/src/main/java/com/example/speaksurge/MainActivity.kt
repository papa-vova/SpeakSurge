package com.example.speaksurge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.activity.OnBackPressedCallback
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speaksurge.practice.words.WordsPracticeScreen
import com.example.speaksurge.ui.theme.SpeakSurgeTheme
import androidx.compose.ui.platform.LocalContext
import com.example.speaksurge.practice.words.WordsPracticeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeakSurgeTheme {
                var currentScreen by remember { mutableStateOf<Screen>(Screen.MainMenu) }
                val mainMenuViewModel: MainMenuViewModel = viewModel()
                val context = LocalContext.current
                val wordsPracticeViewModel: WordsPracticeViewModel = viewModel { 
                    WordsPracticeViewModel(context) 
                }
                
                // Handle back button press
                val onBackPressed = {
                    when (currentScreen) {
                        Screen.MainMenu -> {
                            mainMenuViewModel.showExitDialog()
                        }
                        Screen.WordsPractice -> {
                            wordsPracticeViewModel.resetMediaState() // Reset media state
                            currentScreen = Screen.MainMenu // Go back to main menu
                        }
                    }
                }
                
                // Register back button callback
                onBackPressedDispatcher.addCallback(
                    this,
                    object : OnBackPressedCallback(true) {
                        override fun handleOnBackPressed() {
                            onBackPressed()
                        }
                    }
                )
                
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (currentScreen) {
                        Screen.MainMenu -> {
                            MainMenu(
                                modifier = Modifier.padding(innerPadding),
                                onExit = { finish() },
                                onNavigateToWords = { currentScreen = Screen.WordsPractice },
                                viewModel = mainMenuViewModel
                            )
                        }
                        Screen.WordsPractice -> {
                            WordsPracticeScreen(
                                modifier = Modifier.padding(innerPadding),
                                providedViewModel = wordsPracticeViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen {
    object MainMenu : Screen()
    object WordsPractice : Screen()
}

@Composable
fun MainButtonsSection(
    modifier: Modifier = Modifier,
    onWordsClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onWordsClick,
            enabled = true,
            modifier = Modifier.mainButtonModifier(),
            content = {
                Text(text = stringResource(R.string.words))
            }
        )
        
        Button(
            onClick = { 
                // TODO: Navigate to Topics
            },
            enabled = false,
            modifier = Modifier.mainButtonModifier(),
            content = {
                Text(text = stringResource(R.string.topics))
            }
        )
        
        Button(
            onClick = { 
                // TODO: Navigate to Dialogs
            },
            enabled = false,
            modifier = Modifier.mainButtonModifier(),
            content = {
                Text(text = stringResource(R.string.dialogs))
            }
        )
    }
}

@Composable
fun BottomBar(
    onExitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { 
                    // TODO: Navigate to Stats
                },
                enabled = false,
                modifier = Modifier
                    .weight(1f)
                    .bottomBarButtonModifier(),
                content = {
                    Text(text = stringResource(R.string.stats))
                }
            )
            
            Button(
                onClick = { 
                    // TODO: Navigate to Config
                },
                enabled = false,
                modifier = Modifier
                    .weight(1f)
                    .bottomBarButtonModifier(),
                content = {
                    Text(text = stringResource(R.string.config))
                }
            )
            
            Button(
                onClick = onExitClick,
                enabled = true,
                modifier = Modifier
                    .weight(1f)
                    .bottomBarButtonModifier(),
                content = {
                    Text(text = stringResource(R.string.exit))
                }
            )
        }
    }
}

// Common modifier functions
private fun Modifier.mainButtonModifier(): Modifier = this
    .fillMaxWidth()
    .padding(horizontal = 32.dp, vertical = 8.dp)
    .height(64.dp)

private fun Modifier.bottomBarButtonModifier(): Modifier = this
    .padding(horizontal = 4.dp)

@Composable
fun ExitDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Exit?") },
        text = { Text("Do you want to exit the app?") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("No")
            }
        }
    )
}

@Composable
fun MainMenu(
    modifier: Modifier = Modifier,
    onExit: () -> Unit = {},
    onNavigateToWords: () -> Unit = {},
    viewModel: MainMenuViewModel = viewModel()
) {
    val showExitDialog by viewModel.showExitDialog.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainButtonsSection(
            onWordsClick = onNavigateToWords,
            modifier = Modifier.weight(1f)
        )
        
        BottomBar(
            onExitClick = { viewModel.showExitDialog() }
        )
    }

    // Exit confirmation dialog
    if (showExitDialog) {
        ExitDialog(
            onDismiss = { viewModel.hideExitDialog() },
            onConfirm = {
                viewModel.hideExitDialog()
                onExit()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuPreview() {
    SpeakSurgeTheme {
        MainMenu()
    }
}