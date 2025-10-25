//
//  NotesRepository.swift
//  iosApp
//
//  Created by David Tertre on 15/10/25.
//

import SwiftUI
import Foundation
import Combine


// It Losses info when the app is closed
class LocalNotesRepository: NotesRepository, ObservableObject {
    
    // El corazón: @Published actualiza automáticamente los suscriptores.
    @Published private var notes: [Note] = []
    
    // Cumple el contrato del Protocolo al transformar el @Published en un AnyPublisher
    var notesPublisher: AnyPublisher<[Note], Never> {
            // $notes es el Publisher del array, lo convertimos a AnyPublisher
            return $notes.eraseToAnyPublisher()
        }
    
    // Init samples
    
    init() {
        fetchNotes()
    }
    
    // Acciones: Todas las modificaciones deben hacerse a la propiedad @Published
    
    func fetchNotes() {
        self.notes = [
            Note(id: UUID(), title: "Comprar pan", content: "No olvidar que sea integral.", date: Date()),
            Note(id: UUID(), title: "Reunión de proyecto", content: "Preparar la presentación para el lunes a la 10:00 AM.", date: Date())
        ]
    }
    
    func getNotes() -> [Note] {
        return notes
    }
    
    func addNote(_ note: Note) {
        notes.append(note)
    }
    
    func updateNote(_ note: Note) {
        if let index = notes.firstIndex(where: { $0.id == note.id }) {
            notes[index] = note
        }
    }
    
    func deleteNote(_ note: Note) {
        notes.removeAll { $0.id == note.id }
    }
}
