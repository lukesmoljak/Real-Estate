# Real-Estate

App functionality:
Displays list of properties and in detail screen displays the id of the property.

Architecture / Architecture Features:
- Clean Architecture
- Top level package name by Clean Architecture layer (plus di for dependency injection). For a very small project I prefer this approach, otherwise I prefer the top level package to be package-by-feature.
- MVVM presentational layer architecture
- Jetpack components
- State Management visible from View Model and Fragment

Common Libraries:
- Dagger 2. A faster way than implementing a manual dependency injection system. There are many benefits of using dependency injection my personal favourite is the increase in testability; being able to switch out implementation classes in testing for fakes. It also promotes code to be written with single responsibility principle. 
- Retrofit 2. A widely used networking library that simplifies networking calls. Particularly good for REST calls that don't require prioritisation, retries or caching. 
- Glide. A widely used image handling library. It handles caching of images and simplifies the process of loading images into the UI and offers useful features like adding placeholders and cropping images in a variety of ways.
- Leak Canary. Detect leaks in debug builds. 
- Okhttp3 Logging Interceptor. Track network calls in log for debugging purposes. 
- MockWebServer. To mock network call responses. Used for testing.
- Jupiter / Junit4. Provides functionality for unit testing.

UX:
- Slide in/out animations
- Indeterminate progress bar while network request is being processed
- Snackbar displayed on error

Screens:
1. Property Listings Screen
2. Property Detail Screen

Unit Tests - Testing for network calls
