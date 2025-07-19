# Chess Player Viewer

## Description
This is my playground for researching or implementing new features in Kotlin Multiplatform. The app is designed for showing chess players and allowing you to save your favourite players. 

## Architecture
![image of architecture](/screenshot/Architecture.png)

Data Layer: responsible for handling all data-related operations. It consists of a RemoteSource for fetching data from APIs, a LocalSource for accessing local storage such as databases or preferences, and a Repository implementation that combines both sources and provides a consistent interface to the domain layer.

Domain Layer: contains the core business logic of the application. It includes UseCases, each representing a single unit of work, such as fetching user data or updating a record. It also defines repository interfaces that the data layer must implement. 

Presentation Layer: handles the user interface and state management. It includes ViewModels that manage UI state and interact with UseCases, and Compose-based UI components that observe state changes and render the appropriate UI


## Structure
This app implements Clean Architecture, but without separating the layers into different modules. You can see the folder structure below:

````
├── data
│   ├── local
│   │   ├── driver
│   │   └── entity
│   ├── remote
│   │   └── dto
│   ├── repository
│   └── service
├── di
├── domain
│   ├── model
│   ├── repository
│   └── usecase
├── ui
│   ├── component
│   ├── feature
│   │   ├── favorite_player
│   │   ├── home
│   │   │   └── component
│   │   ├── leaderboard
│   │   │   └── component
│   │   ├── profile
│   │   │   └── component
│   │   └── streamer
│   │       └── component
│   ├── navigation
│   └── style
└── utils
````

## Libraries
- Compose
- Navigation Compose
- Ktor
- SQLDelight
- Koin
- Kotlinx DateTime

## Future works
I have many things that I want to implement, and this is the checklist for my future work in this repo.

* Caching strategy
* Migrate to MVI
* Custom Animation
