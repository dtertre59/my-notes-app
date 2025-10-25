//
//  ContentView.swift
//  iosApp
//
//  Created by David Tertre on 12/10/25.
//

import SwiftUI



// This is posible to do in Shared Module (Kotlin)
enum AppRoute: Hashable {
    case home
    case edit(noteId: UUID)
}

final class AppRouter: ObservableObject {
    @Published var path = [AppRoute]()
    
    func navigate(to route: AppRoute) {
        path.append(route)
    }
    
    func pop() {
        guard !path.isEmpty else { return }
        path.removeLast()
    }
    
    func popAll() {
        path.removeAll()
    }
}

struct ContentView: View {
    
    @StateObject var router: AppRouter = AppRouter()
    
//    @StateObject private var notesRepository: LocalNotesRepository = LocalNotesRepository()
    @StateObject private var notesRepository: SwiftDataNotesRepository = SwiftDataNotesRepository()

    var body: some View {
       NavigationStack(path: $router.path) {
           
           // 1. Init view
           HomeView(notesRepository: notesRepository)
           
           // 2. Define App views for the router
           //    El NavigationStack sabrá qué vista mostrar en función de la última ruta en 'router.path'
           .navigationDestination(for: AppRoute.self) { route in
               switch route {
               case .home:
                   HomeView(notesRepository: notesRepository)
               case .edit(let noteId):
                   EditView(notesRepository: notesRepository, noteId: noteId) // Ejemplo de vista de detalle
               }
           }
       }
       // Available for any subviews
       .environmentObject(router)
       .environmentObject(notesRepository)
    }
}
