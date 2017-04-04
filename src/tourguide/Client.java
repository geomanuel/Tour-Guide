package tourguide;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Client {
	
	private static Queue<String> locationQ = new Queue<String>();
	
	private static String hbAddress;
	private static double hbLong;
	private static double hbLat;
	
	private static HashMap<Integer, Location> uids;
	
	private static Location hbLoc;
	private static Location hbCopy;
	
	public static void main(String args[]) throws FileNotFoundException{
		
		/////////////////////////////////////////////////////SETUP\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
				
		Scanner scanner = new Scanner(System.in);		
		System.out.print("Enter homebase address: ");		
		String hb = scanner.nextLine(); //homebase
		
		System.out.println("----------------------------------");
		System.out.println("Enter locations you would like to visit (comma seperated): ");
		System.out.println("CHOICES: parksAndCampgrounds, alcohol, restaurants");
		String loc = scanner.nextLine();		

		//setup  q of locations
		for(String i : loc.split(","))
			locationQ.enqueue(i);
		
		/////////////////////////////////////////////////END OF SETUP\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		
		////////////////////////////////////////////////PLACE CODE HERE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		int selection = 1; //used to select correct address if multiple are returned
		
		GeoCoding gc = new GeoCoding(hb);		

		
		//If the address exists
		if(gc.exists()){
			//If google maps api returns more than 1 address
			if(gc.results() > 1){
				for(int i = 0; i < gc.results(); i++)
					System.out.println((i+1) + ") " +  gc.formattedAddress()[i]); //print out results
				
				System.out.print("Enter the integer corresponding to the correct address: ");
				selection = scanner.nextInt();	
			}
		
		    hbAddress = gc.formattedAddress()[selection-1];
			hbLong = gc.longitude()[selection-1];
			hbLat = gc.latitude()[selection-1];
			
			hbLoc = new Location(hbAddress,hbLat,hbLong);
			hbCopy = new Location(hbAddress,hbLat,hbLong); //Copy homebase Algorithm (used when implementing SP algorithm)
						
		}else{
			System.out.println("Sorry the entered address does not exist");
		}
		
		
		////////////////////////////////////////////////////////////JUST FOR TESTING\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		uids = new HashMap<Integer,Location>();
		final double MAX_RADIUS = 10;

		//sorts all categories
		//HeapSortCategory.All(hbLoc);

		//Initializes Linear Search
		LinearSearch ls = new LinearSearch();
		
		//creates 2 dimensional ArrayList called validLocs that contains sorted and searched ArrayList of categories in queue
		ArrayList<ArrayList<Location>> validLocs = new ArrayList<ArrayList<Location>>();
		String deq;
		int locationQSize = locationQ.size();
		int uidCounter = 0;
		
		String[] previous = new String[locationQSize];
		
		hbLoc.setUid(uidCounter); //give homebase location a uid
		uids.put(uidCounter, hbLoc);
		
		hbCopy.setUid(++uidCounter); 
		uids.put(uidCounter, hbCopy);
		
		//For every element in the preference Q (everything the user wants to see)
		for (int j = 0; j < locationQSize; j++){
						
			deq = locationQ.dequeue();
			
			if (deq.equals("airports")){ 
				
				HeapSortCategory.Airports(hbLoc); //Generate and sort list of Airports (by distance to hbLoc in km)
				
				ArrayList<Location> airports = new ArrayList<Location>(); //Initialize a new array to hold all airports within the radius
				
				for (int i = 0; i < ls.floor(Gen.airports, MAX_RADIUS, hbLoc); i++) {
					airports.add(Gen.airports.get(i));
					Gen.airports.get(i).setUid(++uidCounter); //Give Location object a unique integer id
					uids.put(uidCounter, Gen.airports.get(i)); //Store unique id and Location object in hashmap
				}
				
				if(!airports.isEmpty()) validLocs.add(airports); //If airports exist within the radius, add to validLocsS
				
			}
			else if (deq.equals("alcohol")){
				
				HeapSortCategory.Alcohol(hbLoc);
				
				ArrayList<Location> alcohol = new ArrayList<Location>();
				
					for (int i = 0; i < ls.floor(Gen.alcohol, MAX_RADIUS, hbLoc); i++) {
						alcohol.add(Gen.alcohol.get(i));
						Gen.alcohol.get(i).setUid(++uidCounter);
						uids.put(uidCounter, Gen.alcohol.get(i));
					}
				
				if(!alcohol.isEmpty()) validLocs.add(alcohol);
				
			}
			else if (deq.equals("attractions")){
				
				HeapSortCategory.Attractions(hbLoc);
				
				ArrayList<Location> attractions = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.attractions, MAX_RADIUS, hbLoc); i++) {
					attractions.add(Gen.attractions.get(i));
					Gen.attractions.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.attractions.get(i));
				}

				if(!attractions.isEmpty()) validLocs.add(attractions);
			}
			else if (deq.equals("casinos")){
				
				HeapSortCategory.Casinos(hbLoc);

				ArrayList<Location> casinos = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.casinos, MAX_RADIUS, hbLoc); i++) {
					casinos.add(Gen.casinos.get(i));
					Gen.casinos.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.casinos.get(i));
				}
				
				if(!casinos.isEmpty()) validLocs.add(casinos);
			}
			else if (deq.equals("golf")){
				
				HeapSortCategory.Golf(hbLoc);
				
				ArrayList<Location> golf = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.golf, MAX_RADIUS, hbLoc); i++) {
					golf.add(Gen.golf.get(i));
					Gen.golf.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.golf.get(i));
				}

				if(!golf.isEmpty()) validLocs.add(golf);
			}
			else if (deq.equals("hotels")){
				
				HeapSortCategory.Hotels(hbLoc);

				ArrayList<Location> hotels = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.hotels, MAX_RADIUS, hbLoc); i++) {
					hotels.add(Gen.hotels.get(i));
					Gen.hotels.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.hotels.get(i));
				}

				if(!hotels.isEmpty()) validLocs.add(hotels);
			}
			else if (deq.equals("lighthouses")){
				
				HeapSortCategory.Lighthouses(hbLoc);

				ArrayList<Location> lighthouses = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.lighthouses, MAX_RADIUS, hbLoc); i++) {
					lighthouses.add(Gen.lighthouses.get(i));
					Gen.lighthouses.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.lighthouses.get(i));
				}

				if(!lighthouses.isEmpty()) validLocs.add(lighthouses);
			}
			else if (deq.equals("majorCities")){
				
				HeapSortCategory.MajorCities(hbLoc);

				ArrayList<Location> majorCities = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.majorCities, MAX_RADIUS, hbLoc); i++) {
					majorCities.add(Gen.majorCities.get(i));
					Gen.majorCities.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.majorCities.get(i));
				}

				if(!majorCities.isEmpty()) validLocs.add(majorCities);
			}
			else if (deq.equals("mountainPeaks")){
				
				HeapSortCategory.MountainPeaks(hbLoc);

				ArrayList<Location> mountainPeaks = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.mountainPeaks, MAX_RADIUS, hbLoc); i++) {
					mountainPeaks.add(Gen.mountainPeaks.get(i));
					Gen.mountainPeaks.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.mountainPeaks.get(i));
				}

				if(!mountainPeaks.isEmpty()) validLocs.add(mountainPeaks);
			}
			else if (deq.equals("museumsAndArt")){
				
				HeapSortCategory.MuseumsAndArt(hbLoc);

				ArrayList<Location> museumsAndArt = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.museumsAndArt, MAX_RADIUS, hbLoc); i++) {
					museumsAndArt.add(Gen.museumsAndArt.get(i));
					Gen.museumsAndArt.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.museumsAndArt.get(i));
				}

				if(!museumsAndArt.isEmpty()) validLocs.add(museumsAndArt);
			}
			else if (deq.equals("parksAndCampgrounds")){
				
				HeapSortCategory.ParksAndCampgrounds(hbLoc);

				ArrayList<Location> parksAndCampgrounds = new ArrayList<Location>();
				
					for (int i = 0; i < ls.floor(Gen.parksAndCampgrounds, MAX_RADIUS, hbLoc); i++) {
						parksAndCampgrounds.add(Gen.parksAndCampgrounds.get(i));
						Gen.parksAndCampgrounds.get(i).setUid(++uidCounter);
						uids.put(uidCounter, Gen.parksAndCampgrounds.get(i));	
					
				}
				
				if(!parksAndCampgrounds.isEmpty()) validLocs.add(parksAndCampgrounds);
			}
			else if (deq.equals("restAreas")){
				
				HeapSortCategory.RestAreas(hbLoc);

				ArrayList<Location> restAreas = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.restAreas, MAX_RADIUS, hbLoc); i++) {
					restAreas.add(Gen.restAreas.get(i));
					Gen.restAreas.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.restAreas.get(i));
				}

				if(!restAreas.isEmpty()) validLocs.add(restAreas);
			}
			else if (deq.equals("restaurants")){
				
				HeapSortCategory.Restaurants(hbLoc);

				ArrayList<Location> restaurants = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.restaurants, MAX_RADIUS, hbLoc); i++) {
					restaurants.add(Gen.restaurants.get(i));
					Gen.restaurants.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.restaurants.get(i));
				}

				if(!restaurants.isEmpty()) validLocs.add(restaurants);
			}
			else if (deq.equals("skiing")){
				
				HeapSortCategory.Skiing(hbLoc);

				ArrayList<Location> skiing = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.skiing, MAX_RADIUS, hbLoc); i++) {
					skiing.add(Gen.skiing.get(i));
					Gen.skiing.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.skiing.get(i));
				}

				if(!skiing.isEmpty()) validLocs.add(skiing);
			}
			else if (deq.equals("touristInfo")){
				
				HeapSortCategory.TouristInfo(hbLoc);

				ArrayList<Location> touristInfo = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.touristInfo, MAX_RADIUS, hbLoc); i++) {
					touristInfo.add(Gen.touristInfo.get(i));
					Gen.touristInfo.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.touristInfo.get(i));
				}

				if(!touristInfo.isEmpty()) validLocs.add(touristInfo);
			}
			
		}


		Collections.reverse(validLocs); //reverse arraylist so order stays in tact
		
		//Make sure validLocs contains something
		if(!validLocs.isEmpty()){
			
			int v = validLocs.get(validLocs.size()-1).size() + 2; //number of vertices
			int e = 0; //number of edges
			
			//calculate the number of edges/vertices
			for (int i = 0; i < validLocs.size()-1; i++){			
				e += validLocs.get(i).size() * validLocs.get(i+1).size();
				v += validLocs.get(i).size();
			}
			
			e += validLocs.get(0).size() + validLocs.get(validLocs.size()-1).size();
			
			
			DirectedEdge[] de = new DirectedEdge[e]; //Array of Directed Edges
			int counter = 0;
				
			
			//Connect the homebase to all the valid locations (type is the first element in the preference q)
			for(int i = 0; i < validLocs.get(0).size(); i++)
				de[counter++] = new DirectedEdge(hbLoc.getUid(),validLocs.get(0).get(i).getUid(),hbLoc.distTo(validLocs.get(0).get(i)));
					
			//Connect all places to adjacent places (ie. if preference q is restaurant, museums, parks, it will connect all the restaurants to all the museums
			// and all the museums to all the parks.
			for(int i = 0; i < validLocs.size() - 1; i++)
				for(int j = 0; j < validLocs.get(i).size(); j++)
					for(int k = 0; k < validLocs.get(i+1).size(); k++)
						de[counter++] = new DirectedEdge(validLocs.get(i).get(j).getUid(),validLocs.get(i+1).get(k).getUid(),validLocs.get(i).get(j).distTo(validLocs.get(i+1).get(k)));	
	
			//Connnect all the valid locations (last location in the queue) back to the homebase location
			for(int i = 0; i < validLocs.get(validLocs.size()-1).size(); i++)
				de[counter++] = new DirectedEdge(validLocs.get(validLocs.size()-1).get(i).getUid(),hbCopy.getUid(),validLocs.get(validLocs.size()-1).get(i).distTo(hbLoc));

			//Create an edge weighted digraph
			EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(de,v,e);	
			
			//Implement Dijkstra
			Dijkstra d = new Dijkstra(ewd,hbCopy.getUid());		
			
			
			Iterable<DirectedEdge> z= d.pathTo(hbLoc.getUid());
			
			//Print out path
			for(DirectedEdge dee : z)
				System.out.println(uids.get(dee.from()).getName() + " -> " + uids.get(dee.to()).getName());
		}

		
	}

}
