# MusicSearch
# iTunes Music Search
A simple Master-Detail Android Application using search results from iTunes Store API

# Overview
Users are able to use the application to do the following
Search for music using keywords
View track details from the results
Go to the iTunes website for song, artist, or collection details

# Architecture
This application uses Model-View-ViewModel (MVVM) architecture with the following package structure

data - Data classes for mapping API responses, type converters, and database management classes
di - Dependency provider modules using Dagger
network - Service for accessing API
ui - View classes, adapters, and their respective view models

# Data Persistence
The user's last search results and the date and time of when that search occurred are retained so that the user can tell how up to date the data is the next time they return to the app.

The list of music tracks was retained using Room which was chosen for good synergy with the other Jetpack components and the date and time of when the last search occurred was retained using SharedPreferences which was chosen because it's very straightforward and easy to use for primitive data types with it's key/value pairs.

