package com.example.mynotesapp.viewModels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

import com.example.mynotesapp.MainUiState
import com.example.mynotesapp.Screen
import com.example.mynotesapp.presentation.MainIntent
import com.example.mynotesapp.presentation.NavigationEvent
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asStateFlow


class MainViewModel: CoroutineScope {
    private val job = Job()
    // Combina el Job con el Dispatcher principal.
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val _uiState = MutableStateFlow(MainUiState())
    @NativeCoroutinesState
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    // Flujo de Eventos (SharedFlow): La orden de navegación.
    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    @NativeCoroutines
    val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvents.asSharedFlow()

    // ** Logica: Procesear las intenciones **
    fun process(intent: MainIntent) {
        launch {
            when (intent) {
                MainIntent.GoToNoteScreen -> {
                    val noteId = "123"
                    _uiState.update { it.copy(currentScreen = Screen.Note) }
                    _navigationEvents.emit(NavigationEvent.GoToNote(noteId))
                }
                MainIntent.GoToMainScreen -> _uiState.update { it.copy(currentScreen = Screen.Main) }
            }
        }
    }
    fun clear() {
        job.cancel()
    }

    // CON SKIE NO HACE FALTA ESTA FUNCION
    @NativeCoroutines
    fun navigationEventsAsAsyncSequence(): Flow<NavigationEvent> {
        return navigationEvents
    }
}