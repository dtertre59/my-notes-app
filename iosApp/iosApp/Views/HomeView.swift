//
//  HomeView.swift
//  iosApp
//
//  Created by David Tertre on 12/10/25.
//

import SwiftUI

// import Shared    // KMP

// extension NoteEntity: Identifiable{} // KMP

struct HomeView: View {
    
    @EnvironmentObject var router: AppRouter
    
    @StateObject private var homeViewModel: HomeViewModel
    
    init(notesRepository: NotesRepository) {
        _homeViewModel = StateObject(wrappedValue: HomeViewModel(notesRepository: notesRepository))
    }
    
    var body: some View {
        
        VStack {
            ScrollView {
                LazyVStack (spacing: 5) {
                    ForEach(homeViewModel.notes) { note in
                        Button(action: {
                            router.navigate(to: .edit(noteId: note.id))
                        }) {
                            NoteCardView(note: note)
                        }.buttonStyle(.plain)
                        
                    }
                }
                .padding(.horizontal)
            }
            .padding()
            .navigationBarTitle("My Notes App")
            // 👇 AÑADIR EL BOTÓN FLOTANTE
            .safeAreaInset(edge: .bottom) {
                HStack {
                    Spacer() // Empuja el botón a la derecha
                    
                    Button(action: {
                        // Acción para navegar a la vista de creación
                        router.navigate(to: .edit(noteId: UUID()))
                    }) {
                        Image(systemName: "plus.circle.fill")
                            .resizable()
                            .frame(width: 56, height: 56) // Tamaño estándar para un FAB
                            .foregroundColor(.blue) // Color distintivo
                            .background(Color.white) // Fondo para separarlo
                            .clipShape(Circle())
                            .shadow(radius: 6) // Sombra para darle efecto flotante
                    }
                    .padding(.trailing, 30) // Margen desde el borde derecho
                    .padding(.bottom, 5)  // Margen desde el borde inferior
                }
            }
        }
    }
}
