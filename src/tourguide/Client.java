package tourguide;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	
	private static Queue<String> locationQ = new Queue<String>();
	
	private static String hbAddress;
	private static double hbLong;
	private static double hbLat;
	
	private static Location hbLoc;
	
	public static void main(String args[]){
		
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
		/////////////////////////////////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		
		int v = validLocs[validLocs.length-1].length + 1; //number of vertices
		int e = 0; //number of edges
		
		//calculate the number of edges/vertices
		for (int i = 0; i < validLocs.length-1; i++){			
			e += validLocs[i].length * validLocs[i+1].length;
			v += validLocs[i].length;
		}
		
		e += validLocs[0].length + validLocs[validLocs.length-1].length;
		
		
		
		DirectedEdge[] de = new DirectedEdge[e];
		int uidCounter = 0; //used to assign unique ids to vertices (Location type objects)
		int counter = 0;
		
		hbLoc.setUid(uidCounter); //give homebase location a uid
		
		
		//Connect the homebase to all the valid locations (type is the first element in the preference q)
		for(int i = 0; i < validLocs[0].length; i++){
			validLocs[0][i].setUid(++uidCounter);
			de[counter++] = new DirectedEdge(hbLoc.getUid(),validLocs[0][i].getUid(),hbLoc.distTo(validLocs[0][i]));
		}
		
		//Connect all places to adjacent places (ie. if preference q is restaurant, museums, parks, it will connect all the restaurants to all the museums
		// and all the museums to all the parks.
		for(int i = 0; i < validLocs.length - 1; i++){
			for(int j = 0; j < validLocs[i].length; j++){
				for(int k = 0; k < validLocs[i + 1].length; k++){
					if(j == 0)
						validLocs[i+1][k].setUid(++uidCounter);
					de[counter++] = new DirectedEdge(validLocs[i][j].getUid(),validLocs[i+1][k].getUid(),validLocs[i][j].distTo(validLocs[i+1][k]));	
				}
				
			}
		}
		
		//Connnect all the valid locations (last location in the queue) back to the homebase location
		for(int i = 0; i < validLocs[validLocs.length-1].length; i++){
			de[counter++] = new DirectedEdge(validLocs[validLocs.length-1][i].getUid(),hbLoc.getUid(),validLocs[validLocs.length-1][i].distTo(hbLoc));
		}
	
		
		//Create an edge weighted digraph
		EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(de,v,e);
		
		//Print out edges
		for(DirectedEdge edd : de){
			System.out.println(edd.toString());
		}
		

		
	}

}
