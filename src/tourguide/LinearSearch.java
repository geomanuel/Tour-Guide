package tourguide;

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
	 * @param l ArrayList<Location>
	 * @param radius double
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
	
	
	public static void main(String [] args){
	
	}
}
