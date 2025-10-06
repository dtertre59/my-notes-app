package com.example.mynotesapp.presentation

sealed class NavigationEvent {
    // Outbut event to
    data class GoToNote(val noteId: String) : NavigationEvent()
    // 🔑 NUEVO EVENTO: Ordena a la capa nativa retroceder (pop).
    data object PopScreen : NavigationEvent()
}