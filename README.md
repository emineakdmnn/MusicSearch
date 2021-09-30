# MusicSearch
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

<img src="https://user-images.githubusercontent.com/82970461/135449697-e95f91cb-d066-4a66-b43b-65fdfa95b00c.jpg" width=30% height=30%> <img src="https://user-images.githubusercontent.com/82970461/135449973-f1575c0f-c2c3-4a8b-9e09-92ae8dfda4da.jpg" width=30% height=30%> <img src="https://user-images.githubusercontent.com/82970461/135449777-1e25fe58-837f-4ef0-9b91-4db33141e4ec.jpg" width=30% height=30%> <img src="https://user-images.githubusercontent.com/82970461/135449870-f2ba3835-fee0-4b72-adb8-6ab9f6feb4ae.jpg" width=30% height=30%>





