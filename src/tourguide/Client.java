package tourguide;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Client {
	
	private static Queue<String> locationQ = new Queue<String>();
	
	private static String hbAddress;
	private static double hbLong;
	private static double hbLat;
	
	private static HashMap<Integer, Location> uids;
	
	private static Location hbLoc;
	
	public static void main(String args[]) throws FileNotFoundException{
		
		/////////////////////////////////////////////////////SETUP\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
				
		Scanner scanner = new Scanner(System.in);		
		System.out.print("Enter homebase address: ");		
		String hb = scanner.nextLine(); //homebase
		
		System.out.println("----------------------------------");
		System.out.println("Enter locations you would like to visit (comma seperated, i.e. 1,4,2): ");
		System.out.println("1) Res  2) Mus  3) Parks  4) Airports  5) Malls");
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
			
	
			System.out.println(hbAddress + ". Latitude: " + hbLat + ", Longitude: " + hbLong);
			
		}else{
			System.out.println("Sorry the entered address does not exist");
		}
		
		
		////////////////////////////////////////////////////////////JUST FOR TESTING\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		//hbloc = home base address
		
		final double MAX_RADIUS = 2;
		//sorts all categories selected
		HeapSortCategory.All(hbLoc);
		
		
		//Initializes Linear Search
		LinearSearch ls = new LinearSearch();
				
		ArrayList<Location> airports = new ArrayList<Location>();
		ArrayList<Location> alcohol = new ArrayList<Location>();
		ArrayList<Location> attractions = new ArrayList<Location>();
		ArrayList<Location> casinos = new ArrayList<Location>();
		ArrayList<Location> golf = new ArrayList<Location>();
		ArrayList<Location> hotels = new ArrayList<Location>();
		ArrayList<Location> lighthouses = new ArrayList<Location>();
		ArrayList<Location> majorCities = new ArrayList<Location>();
		ArrayList<Location> mountainPeaks = new ArrayList<Location>();
		ArrayList<Location> museumsAndArt = new ArrayList<Location>();
		ArrayList<Location> parksAndCampgrounds = new ArrayList<Location>();
		ArrayList<Location> restAreas = new ArrayList<Location>();
		ArrayList<Location> restaurants = new ArrayList<Location>();
		ArrayList<Location> skiing = new ArrayList<Location>();
		ArrayList<Location> touristInfo = new ArrayList<Location>();
		
		for (int i = 0; i < ls.floor(Gen.airports, MAX_RADIUS, hbLoc); i++) {
			airports.add(Gen.airports.get(i));
		}
		for (int i = 0; i < ls.floor(Gen.alcohol, MAX_RADIUS, hbLoc); i++) {
			alcohol.add(Gen.alcohol.get(i));
		}
		for (int i = 0; i < ls.floor(Gen.attractions, MAX_RADIUS, hbLoc); i++) {
			attractions.add(Gen.attractions.get(i));
		}
		for (int i = 0; i < ls.floor(Gen.casinos, MAX_RADIUS, hbLoc); i++) {
			casinos.add(Gen.casinos.get(i));
		}
		for (int i = 0; i < ls.floor(Gen.golf, MAX_RADIUS, hbLoc); i++) {
			golf.add(Gen.golf.get(i));
		}
		for (int i = 0; i < ls.floor(Gen.hotels, MAX_RADIUS, hbLoc); i++) {
			hotels.add(Gen.hotels.get(i));
		}
		for (int i = 0; i < ls.floor(Gen.lighthouses, MAX_RADIUS, hbLoc); i++) {
			lighthouses.add(Gen.lighthouses.get(i));
		}
		for (int i = 0; i < ls.floor(Gen.majorCities, MAX_RADIUS, hbLoc); i++) {
			majorCities.add(Gen.majorCities.get(i));
		}
		for (int i = 0; i < ls.floor(Gen.mountainPeaks, MAX_RADIUS, hbLoc); i++) {
			mountainPeaks.add(Gen.mountainPeaks.get(i));
		}
		for (int i = 0; i < ls.floor(Gen.museumsAndArt, MAX_RADIUS, hbLoc); i++) {
			museumsAndArt.add(Gen.museumsAndArt.get(i));
		}
		for (int i = 0; i < ls.floor(Gen.parksAndCampgrounds, MAX_RADIUS, hbLoc); i++) {
			parksAndCampgrounds.add(Gen.parksAndCampgrounds.get(i));
		}
		for (int i = 0; i < ls.floor(Gen.restAreas, MAX_RADIUS, hbLoc); i++) {
			restAreas.add(Gen.restAreas.get(i));
		}
		for (int i = 0; i < ls.floor(Gen.restaurants, MAX_RADIUS, hbLoc); i++) {
			restaurants.add(Gen.restaurants.get(i));
		}
		for (int i = 0; i < ls.floor(Gen.skiing, MAX_RADIUS, hbLoc); i++) {
			skiing.add(Gen.skiing.get(i));
		}
		for (int i = 0; i < ls.floor(Gen.touristInfo, MAX_RADIUS, hbLoc); i++) {
			touristInfo.add(Gen.touristInfo.get(i));
		}
		
		//creates 2 dimensional ArrayList called validLocs that contains sorted and searched ArrayList of categories in queue
		ArrayList<ArrayList<Location>> validLocs = new ArrayList<ArrayList<Location>>();
		for (int j = 0; j < locationQ.size(); j++){
			if (locationQ.dequeue().equals("airports")){
				validLocs.add(airports);
			}
			if (locationQ.dequeue().equals("alcohol")){
				validLocs.add(alcohol);
			}
			if (locationQ.dequeue().equals("attractions")){
				validLocs.add(attractions);
			}
			if (locationQ.dequeue().equals("casinos")){
				validLocs.add(casinos);
			}
			if (locationQ.dequeue().equals("golf")){
				validLocs.add(golf);
			}
			if (locationQ.dequeue().equals("hotels")){
				validLocs.add(hotels);
			}
			if (locationQ.dequeue().equals("lighthouses")){
				validLocs.add(lighthouses);
			}
			if (locationQ.dequeue().equals("majorCities")){
				validLocs.add(majorCities);
			}
			if (locationQ.dequeue().equals("mountainPeaks")){
				validLocs.add(mountainPeaks);
			}
			if (locationQ.dequeue().equals("museumsAndArt")){
				validLocs.add(museumsAndArt);
			}
			if (locationQ.dequeue().equals("parksAndCampgrounds")){
				validLocs.add(parksAndCampgrounds);
			}
			if (locationQ.dequeue().equals("restAreas")){
				validLocs.add(restAreas);
			}
			if (locationQ.dequeue().equals("restaurants")){
				validLocs.add(restaurants);
			}
			if (locationQ.dequeue().equals("skiing")){
				validLocs.add(skiing);
			}
			if (locationQ.dequeue().equals("touristInfo")){
				validLocs.add(touristInfo);
			}
			
		}
		
		

		
		/*
		int test = 5000;
		Location[] res = new Location[test];

		
		for(int i = 0; i < test; i++){
			res[i] = new Location("test"+i,i,i);
		}
		
		Location[] mus = {new Location("testMus",10,10), new Location("testMus1",1,1),new Location("testMus",10,10), new Location("testMus1",1,1),new Location("testMus",10,10), new Location("testMus1",1,1),new Location("testMus",10,10), new Location("testMus1",1,1),new Location("testMus",10,10), new Location("testMus1",1,1),new Location("testMus",10,10), new Location("testMus1",1,1)};
		Location[] park = {new Location("testPark",10,10), new Location("testPark1",1,1), new Location("testPark2",2,3), new Location("testPark3",100,100),new Location("testPark",10,10), new Location("testPark1",1,1), new Location("testPark2",2,3), new Location("testPark3",100,100)};
		Location[] mall = {new Location("testPark",10,10), new Location("testPark1",1,1), new Location("testPark2",2,3), new Location("testPark3",100,100),new Location("testPark",10,10), new Location("testPark1",1,1), new Location("testPark2",2,3), new Location("testPark3",100,100),new Location("testPark",10,10), new Location("testPark1",1,1), new Location("testPark2",2,3), new Location("testPark3",100,100),new Location("testPark",10,10), new Location("testPark1",1,1), new Location("testPark2",2,3), new Location("testPark3",100,100)};
		Location[] mall1 = {new Location("testPark",10,10), new Location("testPark1",1,1), new Location("testPark2",2,3), new Location("testPark3",100,100),new Location("testPark",10,10), new Location("testPark1",1,1), new Location("testPark2",2,3), new Location("testPark3",100,100),new Location("testPark",10,10), new Location("testPark1",1,1), new Location("testPark2",2,3), new Location("testPark3",100,100),new Location("testPark",10,10), new Location("testPark1",1,1), new Location("testPark2",2,3), new Location("testPark3",100,100)};
		Location[] mall2 = {new Location("testPark",10,10), new Location("testPark1",1,1), new Location("testPark2",2,3), new Location("testPark3",100,100),new Location("testPark",10,10), new Location("testPark1",1,1), new Location("testPark2",2,3), new Location("testPark3",100,100),new Location("testPark",10,10), new Location("testPark1",1,1), new Location("testPark2",2,3), new Location("testPark3",100,100),new Location("testPark",10,10), new Location("testPark1",1,1), new Location("testPark2",2,3), new Location("testPark3",100,100)};

		Location[][] validLocs = {res,mus,park,mall,mall1,mall2}; //array of valid locations
		*/
		/////////////////////////////////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		
		int v = validLocs.get(validLocs.size()-1).size() + 1; //number of vertices
		int e = 0; //number of edges
		
		//calculate the number of edges/vertices
		for (int i = 0; i < validLocs.size()-1; i++){			
			e += validLocs.get(i).size() * validLocs.get(i+1).size();
			v += validLocs.get(i).size();
		}
		
		e += validLocs.get(0).size() + validLocs.get(validLocs.size()-1).size();
		
		
		
		DirectedEdge[] de = new DirectedEdge[e];
		int uidCounter = 0; //used to assign unique ids to vertices (Location type objects)
		int counter = 0;
		
		hbLoc.setUid(uidCounter); //give homebase location a uid
		
		
		//Connect the homebase to all the valid locations (type is the first element in the preference q)
		for(int i = 0; i < validLocs.get(0).size(); i++){
			validLocs.get(0).get(i).setUid(++uidCounter);
			uids.put(uidCounter, validLocs.get(0).get(i));
			de[counter++] = new DirectedEdge(hbLoc.getUid(),validLocs.get(0).get(i).getUid(),hbLoc.distTo(validLocs.get(0).get(i)));
		}
		
		//Connect all places to adjacent places (ie. if preference q is restaurant, museums, parks, it will connect all the restaurants to all the museums
		// and all the museums to all the parks.
		for(int i = 0; i < validLocs.size() - 1; i++){
			for(int j = 0; j < validLocs.get(i).size(); j++){
				for(int k = 0; k < validLocs.get(i+1).size(); k++){
					if(j == 0){
						validLocs.get(i+1).get(k).setUid(++uidCounter);
						uids.put(uidCounter, validLocs.get(i).get(j));
					}
					de[counter++] = new DirectedEdge(validLocs.get(i).get(j).getUid(),validLocs.get(i+1).get(k).getUid(),validLocs.get(i).get(j).distTo(validLocs.get(i+1).get(k)));	
				}
				
			}
		}
		
		//Connnect all the valid locations (last location in the queue) back to the homebase location
		for(int i = 0; i < validLocs.get(validLocs.size()-1).size(); i++){
			de[counter++] = new DirectedEdge(validLocs.get(validLocs.size()-1).get(i).getUid(),hbLoc.getUid(),validLocs.get(validLocs.size()-1).get(i).distTo(hbLoc));
		}
	
		
		//Create an edge weighted digraph
		EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(de,v,e);
		
		//Print out edges
		for(DirectedEdge edd : de){
			System.out.println(edd.toString());
		}
		

		
	}

}
