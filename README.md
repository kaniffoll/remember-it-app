# Remember It App

Android app prototype for creating notes with reminders.  
Built around the idea of **spaced repetition** to help users remember information more effectively.

---

## Tech Stack

- Kotlin & Jetpack Compose  
- Material 3 Theme  
- Navigation Compose 
- MVVM + Clean Architecture  
- Room Database  
- WorkManager  
- Dagger 2  
- Kotlinx Serialization  

---

## Features

### 1. Notes List Screen
- View, delete, or edit notes  
- Add new notes via floating action button  

### 2. About Screen
- Explains the app’s spaced repetition algorithm  

### 3. Note Screen
- Create/edit notes: title, text, reminder mode  
- Clear or save notes  

---

## UI & Architecture

- Material 3 theme with **dark mode support**  
- **MVVM + Clean Architecture**  
- Single Room table storing `id`, `title`, `text`, `mode`  
- CRUD operations via DAO and repository pattern  
- Reminders scheduled with **WorkManager**  

---

## Purpose

Practice modern Android development: Jetpack Compose, Room, WorkManager, and dependency injection.
