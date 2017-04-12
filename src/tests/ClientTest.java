package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tourguide.Client;
import tourguide.Location;
import tourguide.Queue;

/**
 * This class tests that the overall client code behaves as specified in the
 * requirements specification.
 * 
 * @author Alex Trudeau
 * @since April 11, 2017
 */
public class ClientTest {
	private Client test;

	@Before
	public void setUp() throws Exception {
		test = new Client("1280 Main St W Hamilton", "Park/Campground, Restaurant, Attraction, Hotels, Alcohol");
	}

	@After
	public void tearDown() throws Exception {
		test = new Client("1280 Main St W Hamilton", "Park/Campground, Restaurant, Attraction, Hotels, Alcohol");
	}

	/**
	 * Test that all components of the client code are working properly -check
	 * that categories are added to the queue -check that the homebase location
	 * is being geocoded and fetched from the Google Maps API -check that all
	 * locations within 10km are being found -check that all locations within
	 * 10km are being taken into account when computing the shortest path b/n
	 * points -check that all paths b/n points are the shortest of all points
	 * found
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testClient() throws FileNotFoundException {
		test.setUpLocations();

		String a = test.locationQ.dequeue();
		String b = test.locationQ.dequeue();
		String c = test.locationQ.dequeue();
		String d = test.locationQ.dequeue();
		String e = test.locationQ.dequeue();

		assertTrue(test.hbLoc.getName().equals("1280 Main St W, Hamilton, ON L8S 4L8, Canada"));
		assertEquals(43.262939, test.hbLoc.getLatitude(), 0.003);
		assertEquals(-79.917804, test.hbLoc.getLongitude(), 0.003);
		assertTrue(
				a.equals("Park/Campground") && b.equals("Restaurant") && c.equals("Attraction") && d.equals("Hotels") && e.equals("Alcohol"));
		
		Queue<String> locationQ = new Queue<String>();
		locationQ.enqueue("Park/Campground");
		locationQ.enqueue("Restaurant");
		locationQ.enqueue("Attraction");
		locationQ.enqueue("Hotels");
		locationQ.enqueue("Alcohol");
		
		test.locationQ = locationQ;
		// check that all locations within 10km are found and added to an array
		// list
		ArrayList<ArrayList<Location>> validLocs = test.findValidLocations(test.locationQ);
		assertTrue(validLocs.isEmpty() == false);
		for (int i = 0; i < validLocs.size(); i++) {
			for (int j = 0; j < validLocs.get(i).size(); j++) {
				assertTrue(validLocs.get(i).get(j).distTo(test.hbLoc) <= 10);
			}
		}
		// check that all items in the locations in the ith spot in the queue
		// are connected to
		// all the locations in the ith + 1 spot in the queue
		test.pathFinder(validLocs);
		for (int i = 0; i < validLocs.size() - 1; i++) {
			for (int j = 0; j < validLocs.get(i).size(); j++) {
				assertTrue(test.ewd.outdegree(validLocs.get(i).get(j).getUid()) == validLocs.get(i + 1).size());
			}
		}
	}
}
