## JULO Assignment: Weather App
This Android application was created as a coding test case for the company JULO. Its main functionality is to provide weather predictions for temperature, wind speed, and humidity at a given location for today and up to 3 days later. The app uses two open APIs: OpenWeather and ApiNinjas.

The application is built with a multi-module architecture, which includes the following submodules:
- app
- core
- features

The core module contains the following submodules:
- core:ui
- core:common
- core:network

The features module contains the following submodules:
- features:splash
- features:dashboard
- features:location

There are no horizontal dependencies between the features submodules, which helps to reduce spaghetti dependencies and prevent cyclic dependencies.

The application also uses SOLID and clean architecture principles to ensure code quality and maintainability.

## External Libraries
The main external libraries used in this app include:
- Kotlin Coroutines for multithreading and async tasks
- MVVM architecture for data flow
- Retrofit for networking, and OkHttp and RetrofitUrlManager to switch between base URLs in a clean way
- Splitties as utility
- Dagger Hilt for dependencies injection
- Jetpack Navigation for navigation and to apply a single activity pattern
- Unit tests using JUnit and io.mockk
- Diktat as code quality gate and static analysis
- Gradle Version catalog for gradle dependencies single source of truth
- Room for storing local database


## How to Build and Install
To build and install this application, follow these steps:
 - Clone this repository to your local machine using Git.
bash
```
git clone https://github.com/JosephSanjaya/julo-test-cases.git
```
- Open Android Studio and select "Open an existing Android Studio project".
- Navigate to the root directory of the cloned repository and select the build.gradle file. 
- Wait for Android Studio to sync the project and install any necessary dependencies.
- Once the project has been synced, you can build the APK file by selecting "Build" from the top menu and then "Build Bundle(s) / APK(s)".
- Select "APK" and choose your desired build variant (e.g. debug, release).
- Once the build is complete, the APK file will be generated and can be found in the app/build/outputs/apk directory.
- Transfer the APK file to your Android device and install it.

That's it! You can now use the JULO Weather App on your Android device.
