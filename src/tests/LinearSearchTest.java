package tests;

import static org.junit.Assert.*;

import tourguide.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Alex Trudeau
 * @since April 11, 2017
 */
public class LinearSearchTest {
	private Location loc1;
	private Location loc2;
	private Location loc3;
	private double MAX_RADIUS = 1;
	@Before
	public void setUp() throws Exception {
		loc1 = new Location("Home", 40.7589, -73.9851);
		loc2 = new Location("McMasterU", 43.262970, -79.917804);
		loc3 = new Location("NASA Johnson Space Center", 29.553044, -95.093165);
    	
    	HeapSortCategory.Restaurants(loc1);
    	HeapSortCategory.Alcohol(loc2);
    	HeapSortCategory.MuseumsAndArt(loc3);
    	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFloor() {
		//Initializes Linear Search
    	LinearSearch ls = new LinearSearch();
    	//get number of restaurants near loc1 within a MAX_RADIUS radius
    	int count = 0;
    	for (int i = 0; i < Gen.restaurants.size(); i++){
    		if (loc1.distTo(Gen.restaurants.get(i)) < MAX_RADIUS){
    			count++;
    		}
    	}
    	assertTrue(ls.floor(Gen.restaurants, MAX_RADIUS, loc1) == count);
    	//repeat for other locations
    	count = 0;
    	for (int i = 0; i < Gen.alcohol.size(); i++){
    		if (loc2.distTo(Gen.alcohol.get(i)) < MAX_RADIUS){
    			count++;
    		}
    	}
    	assertTrue(ls.floor(Gen.alcohol, MAX_RADIUS, loc2) == count);
    	
    	count = 0;
    	for (int i = 0; i < Gen.museumsAndArt.size(); i++){
    		if (loc3.distTo(Gen.museumsAndArt.get(i)) < MAX_RADIUS){
    			count++;
    		}
    	}
    	assertTrue(ls.floor(Gen.museumsAndArt, MAX_RADIUS, loc3) == count);
		
	}

}
