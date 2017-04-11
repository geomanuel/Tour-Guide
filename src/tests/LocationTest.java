package tests;

import static org.junit.Assert.*;
import tourguide.Location;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Test the methods inside the class Location.java
 * @author Alex Trudeau
 * @since April 11, 2017
 */
public class LocationTest {
	private static Location loc1;
	private static Location loc2;
	@Before
	public void setUp() throws Exception {
		loc1 = new Location("foo", 5, -5);
		loc2 = new Location("bar", 29, 40);
		loc1.setUid(8);
		loc2.setUid(21);
	}

	@After
	public void tearDown() throws Exception {
		loc1 = new Location("foo", 5, -5);
		loc2 = new Location("bar", 29, 40);
	}

	@Test
	public void testGetName() {
		assertTrue(loc1.getName().equals("foo"));
		assertTrue(loc2.getName().equals("bar"));
	}

	@Test
	public void testSetName() {
		loc1.setName("bar");
		loc2.setName("foo");
		assertTrue(loc1.getName().equals("bar"));
		assertTrue(loc2.getName().equals("foo"));
	}

	@Test
	public void testGetLatitude() {
		assertTrue(loc1.getLatitude() == 5);
		assertTrue(loc2.getLatitude() == 29);
	}

	@Test
	public void testSetLatitude() {
		loc1.setLatitude(2.5);
		loc2.setLatitude(-74);
		assertTrue(loc1.getLatitude() == 2.5);
		assertTrue(loc2.getLatitude() == -74);
	}

	@Test
	public void testGetLongitude() {
		assertTrue(loc1.getLongitude() == -5);
		assertTrue(loc2.getLongitude() == 40);
	}

	@Test
	public void testSetLongitude() {
		loc1.setLongitude(90.121);
		loc2.setLongitude(-101.555787);
		assertTrue(loc1.getLongitude() == 90.121);
		assertTrue(loc2.getLongitude() == -101.555787);
	}

	@Test
	public void testDistTo() {
		assertTrue(loc1.distTo(loc2) > 0);
		assertTrue(loc1.distTo(loc2) <= 6371*Math.PI);
		assertTrue(loc1.distTo(loc2) == 5429.1855106660705);
	}

	@Test
	public void testSetUid() {
		loc1.setUid(45);
		loc2.setUid(1);
		assertTrue(loc1.getUid() == 45);
		assertTrue(loc2.getUid() == 1);
	}

	@Test
	public void testGetUid() {
		assertTrue(loc1.getUid() == 8);
		assertTrue(loc2.getUid() == 21);
	}

	@Test
	public void testIsMarked() {
		loc1.mark();
		loc2.mark();
		
		assertTrue(loc1.isMarked());
		assertTrue(loc2.isMarked());
	}
}
