# Star-Gazers-Project
Khangerel Batzul: **knzyrl**

Shiqi Kuang: **ck-7725**

Aryan Hrishikesh Nair: **AryanNair-24**

Erin Xu: **xuerin**

Alex Tin Long Young: **alexxxtl**

## User Stories:

1. Alice is interested in seeing what the sky will look like tonight at their location. She inputs her coordinates and time and receives a star chart with those parameters. _(Team User Story)_

   - Interactor: User inputs coordinates and time.

   - Controller: Receives coordinates and time, calls the AstronomyAPI to fetch celestial body positions, and processes the response.

   - Presenter: Formats and displays the star chart on the screen.
   
2. Bob would like to see the current phase of the moon so that they can plan stargazing activities. He uses the “Moon Phase” function and inputs his current location to receive an answer. _(Assigned to: Aryan)_

   - Interactor: User selects the “Moon Phase” function and enters location.

   - Controller: Fetches current moon phase data from AstronomyAPI based on location.
     
   - Presenter: Displays the moon phase and illumination percentage.
   
3. Alice would like to learn about upcoming celestial events that she can see from her location. She uses the “Events” function and inputs her current location to receive a list of events and dates. _(Assigned to: Cissy)_

   - Interactor: User selects the “Events” function and enters location.

   - Controller: Calls AstronomyAPI to retrieve a list of upcoming events visible from the location.

   - Presenter: Formats and displays the event names, dates, and times.
   
4. Bob wants to see what the sky looked like during past events from anywhere in the world. He inputs precise coordinates and a time and receives a star chart according to those parameters. _(Assigned to: Alex)_

   - Interactor: User inputs coordinates and a historical time.

   - Controller: Calls AstronomyAPI to retrieve celestial body positions for the specified date, time, and location.

   - Presenter: Displays the star chart reflecting the past sky.
   
5. Alice wants to know the locations of nearby astronomical objects (asteroids, etc.) and if they may be viewed. She uses the “Near-Earth Objects” function and inputs her current location to receive a list of objects. _(Assigned to: Khangerel)_

   - Interactor: User selects “Near-Earth Objects” and inputs location.

   - Controller: Calls NASA’s NeoWs API to fetch nearby astronomical objects based on location.
     
   - Presenter: Formats and displays a list of objects, including names and distances.
   
6. Bob would like to be exposed to new and pretty pictures of space and learn fun facts about astronomical phenomena. This is achieved through the NASA APOD API which allows the user to generate a random astronomical image along with a short description. _(Assigned to: Erin)_

   - Interactor: User selects “Astronomy Picture of the Day” function.

   - Controller: Calls NASA’s APOD API to fetch a random image and description.

   - Presenter: Displays the image along with the description and fun fact.
   
7. Alice does not know what her precise coordinates are. She uses the “Geocoding” function to convert her address into coordinates to use in the other functions. _(Assigned to: Cissy)_

    - Interactor: User inputs an address for geocoding.

   - Controller: Calls Google Maps API to convert the address to coordinates.

   - Presenter: Displays the resulting latitude and longitude for use in other features.
   
8. **Extension**: Alice would like to receive explanations about visible planets and stars on the sky map so that they can gain more knowledge about astronomy.

   - Interactor: User hovers or clicks on a celestial body on the star chart.

   - Controller: Identifies the celestial body, calls AstronomyAPI to fetch details, or uses preloaded data.

   - Presenter: Displays a tooltip or info panel with details about the celestial body, such as name, type, and magnitude.
   
