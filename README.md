# weather

![alt tag](https://user-images.githubusercontent.com/40331343/58585600-6a1fe780-826e-11e9-984d-62c1d4507172.jpg)

Sunshine is a weather app with simple UI to get current and future weather data of a city and save it in a database.
The app consists of two activities.

The first activity (the left side screenshot) is used to search for cities and show saved data. It has a LinearLayout as it's root element. Inside the 
LinearLayout there is a Toolbar on the top showing the app name and logo. Below it, there is a search bar to enable search for cities.
(The min idea to implement this search bar was to use Google Places API, but according to new Google rules it is essential to enable billing
on the project to be able to use the API Key and that is impossible with Iran bank accounts. Therefore an autocomplete TextView with a custom adapter is used instead 
[https://developers.google.com/places/web-service/usage-and-billing]). Rest of the
activity is filled by a RecyclerView showing weather information of the saved cities at a specific time. This data is saved in
a database using Room Persistence Library.

The second activity also uses a LinearLayout as it's root element. The first element of the LinearLayout is a Toolbar showing the city
name with a save menu button. Below that, a ConstraintLayout is used to show current weather data. Finally, at the bottom of the main layout, there
is a TabLayout along with a view pager. Each tab includes a fragment and the fragment has a RecyclerView to display the weather forecast
for the next 5 days.

The weather data is taken from https://openweathermap.com API using Retrofit and RxJava and the app is built in MVVM architecture. 
 
