//
//  NotesRepository.swift
//  iosApp
//
//  Created by David Tertre on 15/10/25.
//

import SwiftUI
import Foundation
import Combine

protocol NotesRepository {
    
    var notesPublisher: AnyPublisher<[Note], Never> { get }
    
    func fetchNotes()
    func getNotes() -> [Note]
    func addNote(_ note: Note)
    func updateNote(_ note: Note)
    func deleteNote(_ note: Note)
}
