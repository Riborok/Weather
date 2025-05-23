# Weather Application Overview

A modern Android weather application with the following key features:

## Core Features
___

### Weather Information
- Current weather conditions display
- Hourly forecast
- Daily forecast with sunrise/sunset times
- Detailed weather metrics (temperature, humidity, pressure, wind)

### Weather AI Chat
- Built-in chat interface for weather queries
- AI-powered responses (GPT-4 integration)
- Support for popular questions and custom user queries
- Context-aware responses based on current weather data

### System Integration
- Home screen widgets
- Weather notifications
- Automated hourly updates via WorkManager

## APIs & Services Used
___

### Weather Data
- **[Open-Meteo API](https://open-meteo.com/en/docs)**
    - Endpoint: `api.open-meteo.com/v1/forecast`
    - Used for retrieving detailed weather forecasts including daily and hourly data
    - Features: Current conditions, multi-day forecasts, weather parameters

### AI Chat Integration
- **[AI Chat API](https://docs.aimlapi.com)**
    - Endpoint: `api.aimlapi.com/v1/chat/completions`
    - Model: GPT-4o-mini
    - Features: Context-aware weather conversations, customized responses

### Location Services
- **[Google Places API](https://developers.google.com/maps/documentation/places/web-service/overview)**
    - Used for location suggestions and address autocompletion
    - Features:
        - Places Autocomplete for city search
        - Geocoding for coordinate-to-address conversion
        - Detailed place information

- **[Google Maps Platform](https://developers.google.com/maps)**
    - Map integration for location visualization
    - Location services for current user position
    - Features:
        - FusedLocationProvider for precise user location
        - Reverse geocoding
        - Maps visual interface

### Authentication & Keys
> Note: API keys are required for all external services. Configure them in `secrets.properties`

## Technical Implementation
___

### Architecture
- Built with Kotlin
- Clean Architecture principles
- Repository pattern
- Domain-driven design

### Key Technologies
- Jetpack Compose for UI
- Hilt for dependency injection
- WorkManager for background tasks
- Ktor for network requests
- Local caching system

### Additional Features
- Multi-language support
- Location-based weather data
- Custom weather data formatting
- Error handling and retry mechanisms

The project follows modern Android development practices with clear separation of concerns and maintainable code structure.

##  [📺 Watch the Weather App overview](https://www.youtube.com/shorts/-Qt_2KbSYX0)
___

## [📥 Download Weather App Presentation](./speech/presentation.pdf)
___

## Authors
___
* Egor Pankratiev
* Stanislau Habrus

## License
___
This project is released under the Apache License 2.0. See the [LICENSE](http://www.apache.org/licenses/) file for details.

Copyright 2025 Egor Pankratiev
