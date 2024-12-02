# Star-Gazers-Project
## **Authors and Contributors**
| Name                     | GitHub Username          |
|---------------------------|--------------------------|
| **Khangerel Batzul**     | [**knzyrl**](https://github.com/knzyrl)          |
| **Shiqi Kuang**          | [**ck-7725**](https://github.com/ck-7725)        |
| **Aryan Hrishikesh Nair**| [**AryanNair-24**](https://github.com/AryanNair-24)|
| **Erin Xu**              | [**xuerin**](https://github.com/xuerin)          |
| **Alex Tin Long Young**  | [**alexxxtl**](https://github.com/alexxxtl)      |

## 🌌 Summary
This project was created to make astronomy more accessible and engaging for people of all backgrounds, whether they are casual stargazers, students, or enthusiasts. By providing users with real-time astronomical data and visualizations, it addresses the common problem of finding out about accurate and up-to-date information about celestial phenomena in one place. This previously required specialized knowledge or expensive equipment. With this project, users can explore and learn about stars, planets, and other celestial events directly from their computers, making it an invaluable tool for those who want to deepen their understanding of the universe without needing prior expertise or advanced tools. Whether you're curious about the constellations visible tonight, tracking the phases of the moon, or planning a stargazing event, this project offers a simple yet powerful solution tailored to your needs.

## 🌃 Table of Contents
### [Authors and Contributors](#authors-and-contributors)
### [Summary](#-summary)
### [Features](#-features)
### [Installation](#%EF%B8%8F-installation)
### [Usage Guide](#-usage-guide)
### [License](#-license)
### [Contributions](#-contributions)
### [User Stories](#-user-stories)

## ✨ Features 
1. 🌠 **Generate Star Chart**
   - Inputs: **Longitude**, **latitude**, **date**.
   - Outputs: A detailed visualization of the night sky.

2. 🌙 **Visualize Moon Phase**
   - Inputs: **Date**, **location**.
   - Outputs: Current moon phase, illumination percentage.

3. 🌞 **Show Events**
   - Inputs: **Date range**, **location**, **celestial body**.
   - Outputs: List of past/upcoming celestial events.

4. ☄️ **Near-Earth Objects**
   - Inputs: **Date range**.
   - Outputs: Asteroid details—name, distance, velocity.

5. 🖼️ **Astronomy Picture of the Day (APOD)**
   - Inputs: **Date**. 
   - Outputs: Space pictures/videos with **descriptions**.

6. 🌎 **Geocoding**
   - Inputs: **Address**, **title of location**. 
   - Output: Precise coordinates.

Full walkthrough can be viewed ***[here!](https://www.loom.com/share/2884e8eeb202459ab3725b459c6b9d84?sid=dd837459-0795-4d0b-bc22-86443b1315e5)***

## 🛠️ Installation
1. Before installing the project, ensure the following are installed on your system:
   - [Java Development Kit (JDK): Version 11 or higher](https://www.oracle.com/java/technologies/downloads/#java11?er=221886)
   - [Apache Maven: Latest version](https://maven.apache.org/download.cgi)
   - [Git (optional)](https://git-scm.com/)
   - [IDE: IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=mac)
2. Clone/download the project:
   - Clone the repository using Git:
     ```
     git clone https://github.com/knzyrl/Star-Gazers-Project.git
     ```
   - Or, download the ZIP file from the repository to your computer and extract it.
3. Open your IDE and import the project:
   - For IntelliJ IDEA:
Go to File > Open and select the project directory.
Confirm when prompted to open it as a Maven project.
4. Build the project:
   - Open a terminal, navigate to the project directory, and execute the following command:
     ```
     mvn clean install
     ```
5. Locate the **Main.java** file and run it
   - The file directory is src/main/java
   - To run the file: Right click the file and press run, or run via command line.
6. Common issues:
   - Maven not recognized: Ensure Maven is added to your system's PATH.
   - Dependency errors: Run ```mvn clean install``` to resolve missing dependencies.
   - IDE errors: Ensure the correct JDK version is configured for the project.
7. Technical requirements:
   - Operating System: Windows, macOS, or Linux.
   - Hardware: Minimum 4GB RAM and an internet connection for fetching astronomical data.

## 📖 Usage Guide
1. Launch the application:
   - Run the program by executing the **Main.java** file in your IDE or via the command line.
2. Interact with the Graphical User Interface (GUI) that pops up:
   - Click on the buttons depending on what you want to do: Generate star charts, view the astronomy picture of the day (APOD), check moon phases, or learn an interesting astronomy fact!
   - Press the **Back** button to go back to the home screen, which will allow you to explore the other features we have.

Example usage: View a star chart
1. Open the application GUI
2. Click the **Generate Star Chart** button
3. The main interface for generating a star chart allows you to input longtitude, latitude, and date.
4. If you don't know your precise coordinates, you can use the **Geocoding** function.
5. After entering your details and clicking the **Generate** button, the GUI will display an interactive star chart visualization.
6. The visualization includes constellations, stars, and other celestial phenomena visible at the specified time and location.

Full walkthrough can be viewed ***[here!](https://www.loom.com/share/2884e8eeb202459ab3725b459c6b9d84?sid=dd837459-0795-4d0b-bc22-86443b1315e5)***

## 📜 License
This project is licensed under the MIT License. See the [LICENSE.md](LICENSE.md) file for details.

## 📈 Feedback
We appreciate your interest in the Star Gazers project! However, we are not accepting feedback at this time.

## 🌟 Contributions
At the moment, we are not accepting external contributions. 

## 🌠 User Stories:

1. Alice is interested in seeing what the sky will look like tonight at their location. She inputs her coordinates and date and receives a star chart with those parameters. _(Team User Story)_

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
   
5. Alice wants to know details about nearby astronomical objects (such as asteroids). She uses the “Near-Earth Objects” function, inputs the desired date range, and receives their closest approach, distance, and relative velocity. _(Assigned to: Khangerel)_

   - Interactor: User selects “Near-Earth Objects” and inputs a date range for observation.

   - Controller: Calls NASA’s NeoWs API to fetch nearby astronomical objects based on the input date range.
     
   - Presenter: Formats and displays a list of objects, including names, distances, and relative velocities for user readability.
   
6. Bob would like to be exposed to new and pretty pictures of space and learn fun facts about astronomical phenomena. This is achieved through the NASA APOD API which allows the user to generate a random astronomical image along with a short description. _(Assigned to: Erin)_

   - Interactor: User selects “Fetch Astronomy Picture of the Day” function or enters a date in the past.

   - Controller: Calls NASA’s APOD API to fetch the corresponding image/video and description.

   - Presenter: Displays the image along with the description, title, URL to image/video.
   
7. Alice does not know what her precise coordinates are. She uses the “Geocoding” function to convert her address into coordinates to use in the other functions. _(Assigned to: Alex)_

    - Interactor: User inputs an address for geocoding.

   - Controller: Calls Geocoding API to convert the address to coordinates.

   - Presenter: Displays the resulting latitude and longitude for use in other features.
   
8. **Extension**: Alice would like to receive explanations about visible planets and stars on the sky map so that they can gain more knowledge about astronomy.

   - Interactor: User hovers or clicks on a celestial body on the star chart.

   - Controller: Identifies the celestial body, calls AstronomyAPI to fetch details, or uses preloaded data.

   - Presenter: Displays a tooltip or info panel with details about the celestial body, such as name, type, and magnitude.
   
