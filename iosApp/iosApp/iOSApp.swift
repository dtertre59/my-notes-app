import SwiftUI
import SwiftData

@main
struct iOSApp: App {
    var body: some Scene{
        WindowGroup {
            ContentView()
            // ContentView2()
        }
        // Swift Database
        .modelContainer(for: Note.self)
    }
}
