//
//  EditView.swift
//  iosApp
//
//  Created by David Tertre on 12/10/25.
//

import SwiftUI


struct EditView: View {
    
    
    @EnvironmentObject var router: AppRouter
    
    @StateObject private var editViewModel: EditViewModel
    
    init(notesRepository: NotesRepository, noteId: UUID) {
        _editViewModel = StateObject(
            wrappedValue: EditViewModel(
                notesRepository: notesRepository,
                noteId: noteId,
            )
        )
    }
    
    var body: some View {
        VStack {
//            Button("Save and back") {
//                editViewModel.saveNote()
//                router.pop()
//            }
//            Button("Delete") {
//                editViewModel.deleteNote()
//                router.pop()
//            }
            // 2. TextField para el Título (una sola línea)
            TextField("Title", text: $editViewModel.title)
                .font(.largeTitle)
                .padding(.horizontal)
            // .textFieldStyle(.roundedBorder) // Opcional: añade un borde visual
            ZStack(alignment: .topLeading) {
                // 1. Placeholder (Texto que se muestra cuando está vacío)
                if editViewModel.content.isEmpty {
                    Text("Content") // El texto de tu placeholder
                        .foregroundColor(Color(uiColor: .placeholderText)) // Color estándar de placeholder
                        .padding(.top, 8) // Ajuste vertical para coincidir con el TextEditor
                        .padding(.leading, 4) // Ajuste horizontal
                }
//                // 2. TextEditor (La vista de edición real)
                TextEditor(text: $editViewModel.content)
                // Hacemos el fondo claro para que el texto de abajo se vea
//                    .background(Color.clear)
                    .scrollContentBackground(.hidden)
            }
            .padding(.leading, 12) // Ajuste horizontal
        }
        .frame(maxHeight: .infinity, alignment: .top)
        .padding()
//        .navigationBarTitle("Edit View Note Bar")
        // ⚠️ KEY CORRECTION: Hides the automatic back button arrow
//        .navigationBarBackButtonHidden(true)
        .onAppear(){
        }
        .onDisappear {
            editViewModel.saveNote()
        }
        // 🚀 Opcional: Añade botones de navegación personalizados
        .toolbar {
            // Botón de Cancelar o Atrás (para salir sin guardar forzado)
            ToolbarItemGroup(placement: .navigationBarTrailing) {
                
                Button("Delete") {
                    editViewModel.deleteNote()
                    router.pop()
                }
                Button("Save") {
                    editViewModel.saveNote() // Llama a saveNote()
                    router.path.removeLast() // Navega hacia atrás
                }
            }
            ToolbarItem(placement: .navigationBarTrailing) {
                
            }
        }
    }
}
