package com.example.mynotesapp

data class MainUiState(
    val currentScreen: Screen = Screen.Main
)

enum class Screen {
    Main,
    Note,
}
