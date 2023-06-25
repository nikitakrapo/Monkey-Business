# Monkey-Business

## Tech stack
- Navigation: Decompose
- UI: Jetpack Compose, SwiftUI
- Networking: Ktor
- Data storage: SQLDelight
- Analytics, Auth: Firebase

## Features
### Multiplatform
- Navigation
- Custom MVI module
- Material color system with Dark theme (Platform-specific)
- Account Manager (Firebase Auth)
- Analytics (Firebase Analytics & Crashlytics)
### iOS
- Fully on SwiftUI
### Android
- Fully on Compose
- Baseline Profiles
- Per-app language settings
- Cool floating debug panel

## How to build & start project
1. Add google-services.json and GoogleService-Info.plist to the project

## Project folder structure

### /core - Core module that links all the components into one application
### /components - User-interactive components (UI components, business logic, etc.) 
### /features - Modules that not directly linked to main app functionality, such as authorization, analytics, common network and DB logic