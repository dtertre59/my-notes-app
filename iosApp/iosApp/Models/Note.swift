//
//  Note.swift
//  iosApp
//
//  Created by David Tertre on 13/10/25.
//

import Foundation
import SwiftData

//struct Note: Identifiable, Codable {
//    var id: UUID
//    var title: String
//    var content: String
//    var date: Date
//}

@Model
final class Note {
    @Attribute(.unique) var id: UUID = UUID()
    var title: String
    var content: String
    var date: Date
    
    // Inicializer
    init(id: UUID = UUID(), title: String, content: String, date: Date) {
        self.id = id
        self.title = title
        self.content = content
        self.date = date
    }
}
