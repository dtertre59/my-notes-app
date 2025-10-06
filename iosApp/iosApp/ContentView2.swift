//
//  ContentView2.swift
//  iosApp
//
//  Created by David Tertre on 28/9/25.
//

import SwiftUI
import Shared // ⬅️ ¡CRUCIAL! Importa tu módulo de Kotlin
import KMPNativeCoroutinesAsync


struct ContentView2: View {
    
    // Instancia del ViewModel de Kotlin
    private let viewModel = MainViewModel()
    
    // Estado Nativo para la Navegación.
    // SwiftUI usa esto para saber si el NavigationLink debe estar activo.
    @State private var isDetailActive = false
    @State private var detailId: String? = nil

    // Puedes usar una clase wrapper para observar el uiState si lo necesitas,
    // pero para los eventos nos centramos en el .task

    var body: some View {
        // Necesitas un NavigationStack para gestionar la pila de vistas
        NavigationStack {
            VStack(spacing: 30) {
                
                Text("Pantalla Principal (KMM Events)")
                    .font(.title)

                // ⬅️ Envía la Intención (Intent) a Kotlin
                Button("Abrir Nota (Lógica en Kotlin)") {
                    viewModel.process(intent: MainIntent.GoToNoteScreen.shared)
                }
                .buttonStyle(.borderedProminent)
                
                // ⬅️ NavigationLink: Es el mecanismo nativo que hace la transición visual.
                NavigationLink(
                    destination: ScreenTwo(noteId: detailId ?? "Error"),
                    isActive: $isDetailActive // Se activa cuando esDetailActive es true
                ) {
                    EmptyView() // Esconde el enlace visualmente, lo controlamos programáticamente.
                }
            }
            .navigationTitle("Principal")
            
            // ⬅️ EL ROUTER ASÍNCRONO: Escucha el SharedFlow de Kotlin
            .task {
                do {
                    // Llama a la función que expone el SharedFlow
                    // let events = try viewModel.navigationEventsAsAsyncSequence()
                    let events = try asyncSequence(for: viewModel.navigationEventsAsAsyncSequence())
                    
                    // 🔑 Bucle que espera a que Kotlin emita un valor
                    for try await event in events {
                        // Verifica qué evento ha llegado
                        if let noteEvent = event as? NavigationEvent.GoToNote {
                            
                            // 1. Guarda el dato del evento
                            self.detailId = noteEvent.noteId
                            
                            // 2. 🔑 Dispara la acción nativa de navegación
                            self.isDetailActive = true
                        }
                    }
                } catch {
                    print("Error al escuchar eventos: \(error)")
                }
            }
        }
        // ⬅️ CRÍTICO: Limpia el ViewModel cuando la vista se cierra
        .onDisappear {
            viewModel.clear()
        }
    }
}
