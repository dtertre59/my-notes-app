//
//  NotesRepository.swift
//  iosApp
//
//  Created by David Tertre on 15/10/25.
//

import SwiftData
import Foundation
import Combine



// It Losses info when the app is closed
class SwiftDataNotesRepository: NotesRepository, ObservableObject {
    
    // El corazón: @Published actualiza automáticamente los suscriptores.
    @Published private var notes: [Note] = []
    
    // Cumple el contrato del Protocolo al transformar el @Published en un AnyPublisher
    var notesPublisher: AnyPublisher<[Note], Never> {
        // $notes es el Publisher del array, lo convertimos a AnyPublisher
        return $notes.eraseToAnyPublisher()
    }
    
    // Referencia al contexto de SwiftData
    private let modelContainer: ModelContainer
    private let modelContext: ModelContext
    
    init() {
        let container: ModelContainer
        do {
            container = try ModelContainer(for: Note.self)
        } catch {
            fatalError("Failed to create ModelContainer for Note: \(error)")
        }
        self.modelContainer = container
        // Creamos el contexto principal
        self.modelContext = ModelContext(container)
        
        // Cargar los datos iniciales al arrancar el repositorio
        self.fetchNotes()
    }
    
    // Acciones: Todas las modificaciones deben hacerse a la propiedad @Published
    
    func fetchNotes() {
        do {
            let descriptor = FetchDescriptor<Note>(sortBy: [SortDescriptor(\Note.date, order: .reverse)])
            let fetchedNotes = try modelContext.fetch(descriptor)
            
            // ⚠️ Actualiza el Publisher para notificar a todos los suscriptores
            DispatchQueue.main.async {
                self.notes = fetchedNotes
            }
        } catch {
            print("Error fetching notes: \(error)")
        }
    }
    
    func getNotes() -> [Note] {
        return notes
    }
    
    func addNote(_ note: Note) {
        modelContext.insert(note)
        saveContext()
    }
    
    func updateNote(_ note: Note) {
        if let existingNote = notes.first(where: { $0.id == note.id }) {
            // Actualizar las propiedades del objeto rastreado
            existingNote.title = note.title
            existingNote.content = note.content
            existingNote.date = Date()
            saveContext()
        }
    }
    
    func deleteNote(_ note: Note) {
        modelContext.delete(note)
        saveContext()
    }
    
    private func saveContext() {
        do {
            try modelContext.save()
            // ⚠️ Fundamental: Volver a cargar para actualizar el Publisher con los datos guardados
            fetchNotes()
        } catch {
            print("Error on saving in SwiftData: \(error)")
        }
    }

}
