# Source Tree Structure

## Tree
```
speaksurge/
├── MainActivity.kt                    # Main app entry point with navigation
├── MainMenuViewModel.kt               # ViewModel for main menu state management
└── practice/                          # Practice mode architecture
    ├── BasePracticeViewModel.kt       # Abstract base ViewModel for all practice modes
    ├── BasePracticeContentItem.kt     # Interface for individual practice content items
    ├── BasePracticeContentCollection.kt # Interface for practice content collection management
    ├── BasePracticeState.kt           # DataStore keys and default values for state persistence
    ├── PracticeDataStore.kt           # DataStore implementation for practice state persistence
    ├── PracticeProgressBar.kt         # Reusable progress bar component
    ├── PracticeMediaControls.kt       # Reusable media controls component
    ├── PracticeContentComponent.kt    # Interface for practice content display components
    └── words/                         # Words practice mode implementation
        ├── WordsPracticeViewModel.kt  # ViewModel extending BasePracticeViewModel for words
        ├── Word.kt                    # Data class for word structure
        ├── WordContentItem.kt         # Implements BasePracticeContentItem for words
        ├── WordsContentCollection.kt  # Implements BasePracticeContentCollection for words
        ├── WordsContentComponent.kt   # Implements PracticeContentComponent for words UI display
        └── WordsPracticeScreen.kt     # Words practice screen using new architecture
```

## File Explanations

### Core App Files

* `MainActivity.kt`: Main entry point with navigation between screens (MainMenu ↔ WordsPractice)

* `MainMenuViewModel.kt`: Manages main menu state, button interactions, and exit dialog

### Practice Architecture (Core)

* `BasePracticeViewModel.kt`: Abstract base class providing common state management, navigation, and media controls for all practice modes

* `BasePracticeContentItem.kt`: Interface defining common properties (id, sourceText, transcription, translation) that all practice content items must implement

* `BasePracticeContentCollection.kt`: Interface for managing collections of practice content items, handling navigation and item retrieval

* `BasePracticeState.kt`: DataStore keys and default values for persisting practice state (currentIndex, isPlaying, isRecording)

* `PracticeDataStore.kt`: DataStore implementation providing persistent storage for practice state, handles saving and retrieving practice session data

* `PracticeProgressBar.kt`: Reusable progress indicator component with Material 3 styling

* `PracticeMediaControls.kt`: Reusable media controls (play/pause, record/stop, previous/next) with exact styling from original

* `PracticeContentComponent.kt`: Interface for practice content display components

### Words Practice Implementation

* `WordsPracticeViewModel.kt`: Extends BasePracticeViewModel, manages words-specific state and data

* `Word.kt`: Data class defining word structure (sourceText, transcription, translation)

* `WordContentItem.kt`: Implements BasePracticeContentItem, provides immutable word content items

* `WordsContentCollection.kt`: Implements BasePracticeContentCollection, manages word data and navigation (currently with dummy data, future storage integration point - will receive injected data sources like repositories, databases, or API services)

* `WordsContentComponent.kt`: Implements PracticeContentComponent, handles UI display logic for words (cards, typography, layout)

* `WordsPracticeScreen.kt`: Main words practice screen using the new architecture components