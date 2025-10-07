# My Notes App

## Author

David Tertre

## Description

Notes app for Android and iOS using Kotlin Multiplatform.

## Por donde voy

Room database. Compatible con kmp.

## Dudas

iosX64() por que esto el el  build gradle de shared
por que hemos quitado esto: add("kspIosX64", libs.androidx.room.compiler)?


## Docs

Quick start: https://www.jetbrains.com/help/kotlin-multiplatform-dev/quickstart.html

Share logic (coroutines): https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-upgrade-app.html#next-step

Official examples: https://github.com/android/kotlin-multiplatform-samples

ViewModel: https://developer.android.com/kotlin/multiplatform/viewmodel

Room Database: https://developer.android.com/kotlin/multiplatform/room

Decompose (Navigation Stack and View Model Solution): https://arkivanov.github.io/Decompose/getting-started/quick-start/




## Patrones de diseño

SwiftUI tiene como patron de diseño principal el Modelo Vista (MV) MOdel View
MVVM es mas antiguo / estricto. El VM de SwiftUI es más directo 

Modelo Vista (MV):
- Enfoque directo. 
- En el patron solo hay dos componentes principales: Modelo (datos puros) y Vista (Interfaz usuario).
  - Model
  - View
Modelo-Vista-ViewModel (MVVM):
- El patron incluye un tercer componente
- ViewModel (VM): es el intermediario:
  - Contiene la lógica de la presentacion
  - Expone el estado que la vista necesita, ya preparado para ser mostrado
  - Maneja las acciones del usuario que la vista le notifica
  - No sabe nada sobre la interfaz de usuario (ui)
- La vista pasa a ser mas 'tonta':
  - Solo muestra datos e informa sobre acciones.

En KOtlin Multiplatform, MVVM es practicamente el standar.
No hay duda. hay que utilizarlo.

Lo normal es tener un ViewModel por cada pantalla.
La navegacion va dentro de la parte nativa (SwiftUI). El viewMOdel puede emitir eventos para controlarla.
La navegacion solo controla el salto de pantallas. No sabe nada de la UI.
El viewModel es el que tiene la logica del pulso de boton, la marcha atras, etc.

En kotlin MP ya es soportada la instancia de viewModel. Ver el la doc.
Tambien soporta Room Database

## SwiftUI

El punto de entrada de la app es: 
- @main (marca la entrada). App (Herencia del protocolo). WindowGroup(contenedor principal de jerarquia de vistas)
- View es el componente principal. Todo es una vista. Es como un widget.
- Cada vez que se cambia el estado SwiftUI reconstruye la vista.
- ContentView suele ser la vista raiz, pero puede tener otro nombre. Es como al MainScreen
- Buscar concepto de router. Se suele utilizar. Es un enum que te va diciendo las pantallas posibles a las que puedes ir desde cada pantalla.
- NavigationStack + NavigationPath

### Estructura de carpestas comun

iosApp/                     # Proyecto iOS nativo (Swift)
│   ├── MyAppApp.swift          # Punto de entrada
│   │
│   ├── Views/                  # Pantallas SwiftUI
│   │   ├── HomeView.swift
│   │   ├── DetailView.swift
│   │   └── Components/
│   │       └── CustomButton.swift
│   │
│   ├── Wrappers/               # Adaptadores iOS <-> KMP
│   │   ├── HomeViewModelWrapper.swift
│   │   └── DetailViewModelWrapper.swift
│   │
│   ├── Utils/
│   │   └── FlowExtensions.swift   # Funciones para convertir Flow → Combine/@Published
│   │
│   └── Resources/              # Assets de iOS
│       └── Assets.xcassets 

## Nomenclatura

Como exponer el StateFlow.
Corrutinas
Variables que cambian

ViewModel
- KMP: 
- Swift:

Navegación:
- KMP
- Swift:

## Dudas

ViewModel en KOtlin Shared

Navegacion: Intencion de Navegacion en Shareed
 Ejecucion de la navegacion en nativo (NavControler en Android y NavigationStack en IOS)
Opciones:
1. Patron de eventos con mapeo nativo. Swift tiene un @state del viewmodel para disparar el push de pantalla
2. Framework de gestion de componentes compartidos (Decompose): centraliza la lógica de la pila (Stack) de navegacion de kotlin
La pila (Stack), sirve para organizar cronologicamente las pantallas que ha ido viendo el usuario.
- push: accion de navegar hacia delante
- pop: accion de navegar hacia atras
- ciclo de vida: la pila asegura que la pantalla actualmente visible ( la que esta en la cima) este activa.
- las ocultas pueden pasar a suspension o ser destruidas para liberar memoria.
En kotlin Multiplatform es el limite de la logica compartida.
- En la opcion 1. Kotlin intencion pero la pila de navegacion es nativa
- En la opcion 2. (decompose) kotlin gestiona la pila logica. La UI nativa solo lee el estado de la pila y dibuja la pantalla superior, con animaciones nativas.

View model hereda de CoroutineScope para dar opcion de destruir la pestaña cuando deja de ser la principal (mirar mejor)

- ERxplicacion de como utilziar KMP native corrutindes: https://www.youtube.com/watch?v=bg4iPhyKpYw


### Para corrutinas

Libreia de terceros: KMP-NativeCoroutines
esta libreria debe estar en la parte share del proyecto. Es un puente de interoperabilidad en KMM qyue resuelve la limitacion de conectar la logica asincrona de kotlin en Swift
Traducir los tipos asíncronos de Kotlin (suspend fun y Flow) a sus equivalentes nativos de Swift (async/await y AsyncSequence), garantizando el soporte de cancelación.

Lo funciona nada. https://github.com/rickclephas/KMP-NativeCoroutines
Revisar porque no va

Build gradle:
id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-47"
In sourcesets: iosMain.dependencies { implementation("com.rickclephas.kmp:kmp-native-coroutines-ios:1.0.0-ALPHA-47")}



## Libraries and dependencies

### Root build Gradle

- common plugins and repos

### Root settings Gradle

- repositories

### Gradle libs.versions.toml

- versions

### Shared build Gradle

- dependencies

## Design

- Clean Architecture (Robert C. Martin, “Uncle Bob”)
- MVVM (Model–View–ViewModel)
- MVI (Model–View–Intent): Best for big projects

### Clean Architecture + MVI

- Inmutable state: StateFlow emite un nuevo estado. Compose detecta el cambio y se recompone.
- ACad estado es un snapshot -> predecible y testeable.

### Domain layer (common)

Only real kotlin code should go here.
Don´t know anything about DDBB, APIs, frameworks or UIs.

- models/entities (e.g. Note(id, title, content))
- repositories interfaces (e.g. NoteRepository)
- use cases (e.g. AddNote, GetNote, DeleteNote)

### Data Layer (common)

Depends on domain layer.
Use Frameworks, libraries and platform details

- repositories -> implementations
- data sources -> local/remote

### Presentation layer (shared?)

- ViewModel
- State
- Intent (actions)

### UI (shared)

- Android Compose
- SwiftUI

## Android

- Entry point: defined in manifest [MainActivity](./androidApp/src/androidMain/kotlin/com/example/mynotesapp/MainActivity.kt)
- UI Root [App](./androidApp/src/androidMain/kotlin/com/example/mynotesapp/App.kt)

### View Model Options

- Si se instancia en el entrypoint, se guarda el estado.
- Instanciar el MainViewModel del shared. Si se instancia en el composable app, aunque pongas rememeber, se pierde el estado al rotar la pantalla. 
- Tambien se puede poner dentro del ViewModel proporcionado por Android. Este gestiona todo.

### Navigation Options


## Default 

This is a Kotlin Multiplatform project targeting Android, iOS.

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* [/shared](./shared/src) is for the code that will be shared between all targets in the project.
  The most important subfolder is [commonMain](./shared/src/commonMain/kotlin). If preferred, you
  can add code to the platform-specific folders here too.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…