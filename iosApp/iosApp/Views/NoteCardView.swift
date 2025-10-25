//
//  NoteCardView.swift
//  iosApp
//
//  Created by David Tertre on 12/10/25.
//


import SwiftUI

struct NoteCardView: View {
    let note: Note
    
    var body: some View {
        
        VStack(alignment: .leading, spacing: 8) {
            Text(note.title)
                .font(.headline)
                .foregroundColor(.primary)
                .lineLimit(1)
                .truncationMode(.tail)
            
            Text(note.content.isEmpty ? "Without content" : note.content)
                .font(.body)
                .foregroundColor(.secondary)
                .lineLimit(1)
                .truncationMode(.tail)
                .italic(note.content.isEmpty)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        // .frame(maxHeight: 100)
        .padding()
        .background(Color(.systemGray6))
        .cornerRadius(12)
        .shadow(radius: 4)
    }
}
