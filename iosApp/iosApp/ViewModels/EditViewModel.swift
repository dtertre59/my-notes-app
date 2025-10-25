//
//  HomeViewModel.swift
//  iosApp
//
//  Created by David Tertre on 13/10/25.
//

import SwiftUI
import Foundation
import Combine


class EditViewModel: ObservableObject {
    
    // State properties
    @Published var title: String = ""
    @Published var content: String = ""
    
    private let notesRepository: NotesRepository
    private let noteId: UUID
    private var isDeleting: Bool = false
    
    
    init(notesRepository: NotesRepository, noteId: UUID) {
        
        self.notesRepository = notesRepository
        self.noteId = noteId
        
        loadNote()
    }
    
    func loadNote() {
        let note = notesRepository.getNotes().first(where: { $0.id == self.noteId })
        if let note = note { // unWrap optional value
            self.title = note.title
            self.content = note.content
        }
    }
    
    func saveNote() {
        let newNote = Note(
            id: noteId,
            title: title,   // .isEmpty ? "New Note" : title,
            content: content,
            date: Date() // Now
        )
        
        if isDeleting { return }
        
        if  notesRepository.getNotes().first(where: { $0.id == self.noteId }) == nil {
            if title != "" {
                notesRepository.addNote(newNote)
            }
        }
        else {
            notesRepository.updateNote(newNote)
        }
        
        func deleteNote(note: Note) {
            notesRepository.deleteNote(note)
        }
    }
    
    func deleteNote() {
        let note = notesRepository.getNotes().first(where: { $0.id == self.noteId })
        if let note = note { // unWrap optional value
            notesRepository.deleteNote(note)
            isDeleting = true
        }

    }
}
