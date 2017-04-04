package tourguide;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;





public class Client {
	
	private static Queue<String> locationQ = new Queue<String>();
	
	private static String hbAddress;
	private static double hbLong;
	private static double hbLat;
	
	private static HashMap<Integer, Location> uids;
	
	private static Location hbLoc;
	private static Location hbCopy;
	
	public static void main(String args[]) throws FileNotFoundException{
		
//		/////////////////////////////////////////////////////SETUP\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//				
//		Scanner scanner = new Scanner(System.in);		
//		System.out.print("Enter homebase address: ");		
//		String hb = scanner.nextLine(); //homebase
//		
//		System.out.println("----------------------------------");
//		System.out.println("Enter locations you would like to visit (comma seperated, i.e. 1,4,2): ");
//		System.out.println("1) Res  2) Mus  3) Parks  4) Airports  5) Malls");
//		String loc = scanner.nextLine();		
//
//		//setup  q of locations
//		for(String i : loc.split(","))
//			locationQ.enqueue(i);
//		
//		/////////////////////////////////////////////////END OF SETUP\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//
//		
//		////////////////////////////////////////////////PLACE CODE HERE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//		
//		int selection = 1; //used to select correct address if multiple are returned
//		
//		GeoCoding gc = new GeoCoding(hb);		
//
//		
//		//If the address exists
//		if(gc.exists()){
//			//If google maps api returns more than 1 address
//			if(gc.results() > 1){
//				for(int i = 0; i < gc.results(); i++)
//					System.out.println((i+1) + ") " +  gc.formattedAddress()[i]); //print out results
//				
//				System.out.print("Enter the integer corresponding to the correct address: ");
//				selection = scanner.nextInt();	
//			}
//		
//		    hbAddress = gc.formattedAddress()[selection-1];
//			hbLong = gc.longitude()[selection-1];
//			hbLat = gc.latitude()[selection-1];
//			
//			hbLoc = new Location(hbAddress,hbLat,hbLong);
//			
//	
//			System.out.println(hbAddress + ". Latitude: " + hbLat + ", Longitude: " + hbLong);
//			
//		}else{
//			System.out.println("Sorry the entered address does not exist");
//		}
		
		
		////////////////////////////////////////////////////////////JUST FOR TESTING\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		uids = new HashMap<Integer,Location>();
		final double MAX_RADIUS = 445;

		//Just for testing
		Location hbLoc = new Location("HomeBase",40.7589, -73.9851);
		Location hbCopy = new Location("HomeBase",40.7589, -73.9851); //Create an identical point to the homebase

		//Just for testing
		locationQ.enqueue("alcohol");
		locationQ.enqueue("parksAndCampgrounds");
		locationQ.enqueue("restaurants");

 
		//sorts all categories
		//HeapSortCategory.All(hbLoc);
		HeapSortCategory.Alcohol(hbLoc);
		HeapSortCategory.Restaurants(hbLoc);
		HeapSortCategory.ParksAndCampgrounds(hbLoc);
		
		
		//Initializes Linear Search
		LinearSearch ls = new LinearSearch();
		
		//creates 2 dimensional ArrayList called validLocs that contains sorted and searched ArrayList of categories in queue
		ArrayList<ArrayList<Location>> validLocs = new ArrayList<ArrayList<Location>>();
		String deq;
		int locationQSize = locationQ.size();
		int uidCounter = 0;
		
		hbLoc.setUid(uidCounter); //give homebase location a uid
		uids.put(uidCounter, hbLoc);
		
		hbCopy.setUid(++uidCounter);
		uids.put(uidCounter, hbCopy);
		
		//For every element in the preference Q (everything the user wants to see)
		for (int j = 0; j < locationQSize; j++){
			
			deq = locationQ.dequeue();

			if (deq.equals("airports")){
				
				ArrayList<Location> airports = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.airports, MAX_RADIUS, hbLoc); i++) {
					airports.add(Gen.airports.get(i));
					Gen.airports.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.airports.get(i));
				}
				
				validLocs.add(airports);
			}
			else if (deq.equals("alcohol")){
				
				ArrayList<Location> alcohol = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.alcohol, MAX_RADIUS, hbLoc); i++) {
					alcohol.add(Gen.alcohol.get(i));
					Gen.alcohol.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.alcohol.get(i));
				}

				validLocs.add(alcohol);
			}
			else if (deq.equals("attractions")){
				
				ArrayList<Location> attractions = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.attractions, MAX_RADIUS, hbLoc); i++) {
					attractions.add(Gen.attractions.get(i));
					Gen.attractions.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.attractions.get(i));
				}

				validLocs.add(attractions);
			}
			else if (deq.equals("casinos")){
				
				ArrayList<Location> casinos = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.casinos, MAX_RADIUS, hbLoc); i++) {
					casinos.add(Gen.casinos.get(i));
					Gen.casinos.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.casinos.get(i));
				}
				
				validLocs.add(casinos);
			}
			else if (deq.equals("golf")){
				
				ArrayList<Location> golf = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.golf, MAX_RADIUS, hbLoc); i++) {
					golf.add(Gen.golf.get(i));
					Gen.golf.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.golf.get(i));
				}

				validLocs.add(golf);
			}
			else if (deq.equals("hotels")){
				
				ArrayList<Location> hotels = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.hotels, MAX_RADIUS, hbLoc); i++) {
					hotels.add(Gen.hotels.get(i));
					Gen.hotels.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.hotels.get(i));
				}

				validLocs.add(hotels);
			}
			else if (deq.equals("lighthouses")){
				
				ArrayList<Location> lighthouses = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.lighthouses, MAX_RADIUS, hbLoc); i++) {
					lighthouses.add(Gen.lighthouses.get(i));
					Gen.lighthouses.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.lighthouses.get(i));
				}

				validLocs.add(lighthouses);
			}
			else if (deq.equals("majorCities")){
				
				ArrayList<Location> majorCities = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.majorCities, MAX_RADIUS, hbLoc); i++) {
					majorCities.add(Gen.majorCities.get(i));
					Gen.majorCities.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.majorCities.get(i));
				}

				validLocs.add(majorCities);
			}
			else if (deq.equals("mountainPeaks")){
				
				ArrayList<Location> mountainPeaks = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.mountainPeaks, MAX_RADIUS, hbLoc); i++) {
					mountainPeaks.add(Gen.mountainPeaks.get(i));
					Gen.mountainPeaks.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.mountainPeaks.get(i));
				}

				validLocs.add(mountainPeaks);
			}
			else if (deq.equals("museumsAndArt")){
				
				ArrayList<Location> museumsAndArt = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.museumsAndArt, MAX_RADIUS, hbLoc); i++) {
					museumsAndArt.add(Gen.museumsAndArt.get(i));
					Gen.museumsAndArt.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.museumsAndArt.get(i));
				}

				validLocs.add(museumsAndArt);
			}
			else if (deq.equals("parksAndCampgrounds")){
				
				ArrayList<Location> parksAndCampgrounds = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.parksAndCampgrounds, MAX_RADIUS, hbLoc); i++) {
					parksAndCampgrounds.add(Gen.parksAndCampgrounds.get(i));
					Gen.parksAndCampgrounds.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.parksAndCampgrounds.get(i));

				}

				validLocs.add(parksAndCampgrounds);
			}
			else if (deq.equals("restAreas")){
				
				ArrayList<Location> restAreas = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.restAreas, MAX_RADIUS, hbLoc); i++) {
					restAreas.add(Gen.restAreas.get(i));
					Gen.restAreas.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.restAreas.get(i));
				}

				validLocs.add(restAreas);
			}
			else if (deq.equals("restaurants")){
				
				ArrayList<Location> restaurants = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.restaurants, MAX_RADIUS, hbLoc); i++) {
					restaurants.add(Gen.restaurants.get(i));
					Gen.restaurants.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.restaurants.get(i));
				}

				validLocs.add(restaurants);
			}
			else if (deq.equals("skiing")){
				
				ArrayList<Location> skiing = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.skiing, MAX_RADIUS, hbLoc); i++) {
					skiing.add(Gen.skiing.get(i));
					Gen.skiing.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.skiing.get(i));
				}

				validLocs.add(skiing);
			}
			else if (deq.equals("touristInfo")){
				
				ArrayList<Location> touristInfo = new ArrayList<Location>();
				
				for (int i = 0; i < ls.floor(Gen.touristInfo, MAX_RADIUS, hbLoc); i++) {
					touristInfo.add(Gen.touristInfo.get(i));
					Gen.touristInfo.get(i).setUid(++uidCounter);
					uids.put(uidCounter, Gen.touristInfo.get(i));
				}

				validLocs.add(touristInfo);
			}
			
		}


		Collections.reverse(validLocs); //reverse arraylist so order stays in tact
		
		int v = validLocs.get(validLocs.size()-1).size() + 2; //number of vertices
		int e = 0; //number of edges
		
		//calculate the number of edges/vertices
		for (int i = 0; i < validLocs.size()-1; i++){			
			e += validLocs.get(i).size() * validLocs.get(i+1).size();
			v += validLocs.get(i).size();
		}
		
		e += validLocs.get(0).size() + validLocs.get(validLocs.size()-1).size();
		
		
		DirectedEdge[] de = new DirectedEdge[e];
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
		
		
		for(DirectedEdge dee : z)
			System.out.println(uids.get(dee.from()).getName() + " -> " + uids.get(dee.to()).getName());
		

		
	}

}
