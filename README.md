# Tour-Guide
Tour Guide will take a user selected location and a list of categorical locations and return a route or “tour” with the shortest path to allow the user to visit all locations/points of interest specified.  

The main function of Tour Guide is that it will be able to generate from user input the “shortest path” of locations to visit starting and ending at the same coordinates. This is intended to be used by tourists to create an efficient plan for their day.


## How to run it:
If you wish to run Tour Guide without the UI, simply run the Client.java file and follow the
instructions on the console.

If you wish to run Tour Guide with the UI, you will need to add all the external jar files found in
src\\TourGuideUI\\jxmaps-1.3.1\\lib to your build path.
Once you have added all the external jar files, simply run the TourGuidUI.java file.

## Algorithms Used:
Sorting: Tour Guide uses heap sort to sort all the valid locations based on their distance from
the home base location.

Searching: Tour Guide uses linear search to locate all locations that are within the radius of the
homebase.

Graphing: Tour Guide uses an Edge Weighted Directional Graph, where the vertices represent locations and the weight represents the distance between the two locations.

Shortest Path: Tour Guide uses Dijkstras algoirthm to find the shortest path from the homebase location, back to the homebase location (after visiting all the user-selected categories)

## External Libraries Used:
JxMaps (https://www.teamdev.com/jxmaps) - Used to display the generated tour on the Java Swing application

org.JSON (https://github.com/stleary/JSON-java) - Used to parse the JSON returned from the google maps API.

http://algs4.cs.princeton.edu/code/ - Used to implement several DataTypes and algorithms (Queue, Stack, etc.)

GoogleMaps GeoCoding API (https://developers.google.com/maps/documentation/geocoding/start) - Used to get information about the address the user enters (Longitude, Latitude, Formatted Address, etc.)
