//
//  HomeViewModel.swift
//  iosApp
//
//  Created by David Tertre on 13/10/25.
//

import SwiftUI
import Foundation
import Combine


class HomeViewModel: ObservableObject {
    
    private let notesRepository: NotesRepository
    
    @Published var notes: [Note] = []
    private var cancellables = Set<AnyCancellable>()

    
    init(notesRepository: NotesRepository) {
            
        self.notesRepository = notesRepository
            // At init of viewmodel, load sample data
            // Later, you will call a funcion to load saved notes.
            //loadSampleNotes()
        notesRepository.notesPublisher
            .assign(to: \.notes, on: self)
            .store(in: &cancellables)
        }
    
    func addNote(title: String, content: String) {
            let newNote = Note(
                id: UUID(),
                title: title.isEmpty ? "Nueva Nota" : title,
                content: content,
                date: Date() // La fecha actual
            )
            notes.append(newNote)
        }
    
    func deleteNote(note: Note) {
        notes.removeAll { $0.id == note.id }
    }
    
    func deleteNotev2(at offsets: IndexSet) {
            notes.remove(atOffsets: offsets)
        }
    
//    private func loadSampleNotesloadSampleNotes() {
//        notes.append(Note(id: UUID(), title: "First Note", content: "This is the content of the first note.", date: Date()))
//        notes.append(Note(id: UUID(), title: "Second Note", content: "This is the content of the second note.", date: Date().addingTimeInterval(-86400)))
//    }
}
