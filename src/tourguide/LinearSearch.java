package tourguide;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Kathryn Kodama
 * @since March 29, 2017
 *
 */
public class LinearSearch {
	
	/**
	 * Floor
	 * @param l an array list of locations of a specific category
	 * @param radius the maximum radius for the tour
	 * @return the index of the Location in the ArrayList that is less than the radius
	 */
	public int floor(ArrayList<Location> l, double radius, Location hb){
		int count = 0;
		for (Location location: l){
			if (location.distTo(hb) > radius)
				break;
			count++;
		}
		return count;
		
	}
	
	
	public static void main(String [] args) throws FileNotFoundException{
		double MAX_RADIUS = 1;
    	
    	//initializes test array of locations and home base
   
    	Location hb = new Location("Home", 40.7589, -73.9851);
   
    	//heap sorts all items in reference to the home base
    	HeapSortCategory.Restaurants(hb);
    	//Initializes Linear Search
    	LinearSearch ls = new LinearSearch();
    	ArrayList<Location> listNew = new ArrayList<Location>();
    	
    	System.out.println("ArrayList size= "+ Gen.restaurants.size());
    	
		for (int i = 0; i < ls.floor(Gen.restaurants, MAX_RADIUS, hb); i++) {
			listNew.add(Gen.restaurants.get(i));
		}
		
		System.out.println(ls.floor(Gen.restaurants, MAX_RADIUS, hb));
		System.out.println(listNew.size());
		//print out locations in order of increasing distance away from the homebase
		for (Location L : listNew){
			System.out.println(L.getName() + ": " + L.getLatitude() + ", " + L.getLongitude() + "d = " + hb.distTo(L));
			
		}
    	
    	
	}
}
