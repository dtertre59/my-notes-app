//
//  screentwo.swift
//  iosApp
//
//  Created by David Tertre on 28/9/25.
//

import SwiftUI

struct ScreenTwo: View {
    // ⬅️ Recibe el dato enviado desde Kotlin
    let noteId: String
    
    var body: some View {
        VStack {
            Text("Detalle de la Nota")
                .font(.largeTitle)
            Text("ID de la Nota (desde Kotlin): \(noteId)")
                .font(.title3)
        }
        .navigationTitle("Nota \(noteId)")
    }
}
