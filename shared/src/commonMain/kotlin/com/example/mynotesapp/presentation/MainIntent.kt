package com.example.mynotesapp.presentation

sealed class MainIntent {
    object GoToNoteScreen : MainIntent()
    object GoToMainScreen : MainIntent()
}