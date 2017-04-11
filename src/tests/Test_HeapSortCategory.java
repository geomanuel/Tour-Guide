package tests;

import static org.junit.Assert.*;
import tourguide.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test_HeapSortCategory {
	private static Location center;

	@Before
	public void setUp() throws Exception {
		center = new Location("center", 10, 10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_Airports() throws FileNotFoundException {
		HeapSortCategory.Airports(center);
		assertTrue(isSorted(Gen.airports));
	}

	@Test
	public void test_Alcohol() throws FileNotFoundException {
		HeapSortCategory.Alcohol(center);
		assertTrue(isSorted(Gen.alcohol));

	}

	@Test
	public void test_Attractions() throws FileNotFoundException {
		HeapSortCategory.Attractions(center);
		assertTrue(isSorted(Gen.attractions));
	}

	@Test
	public void test_Casinos() throws FileNotFoundException {
		HeapSortCategory.Casinos(center);
		assertTrue(isSorted(Gen.casinos));
	}

	@Test
	public void test_Golf() throws FileNotFoundException {
		HeapSortCategory.Golf(center);
		assertTrue(isSorted(Gen.golf));
	}

	@Test
	public void test_Lighthouses() throws FileNotFoundException {
		HeapSortCategory.Lighthouses(center);
		assertTrue(isSorted(Gen.lighthouses));
	}

	@Test
	public void test_MajorCities() throws FileNotFoundException {
		HeapSortCategory.MajorCities(center);
		assertTrue(isSorted(Gen.majorCities));
	}

	@Test
	public void test_MountainPeaks() throws FileNotFoundException {
		HeapSortCategory.MountainPeaks(center);
		assertTrue(isSorted(Gen.mountainPeaks));
	}

	@Test
	public void test_MuseumsAndArt() throws FileNotFoundException {
		HeapSortCategory.MuseumsAndArt(center);
		assertTrue(isSorted(Gen.museumsAndArt));
	}

	@Test
	public void test_ParksAndCampgrounds() throws FileNotFoundException {
		HeapSortCategory.ParksAndCampgrounds(center);
		assertTrue(isSorted(Gen.parksAndCampgrounds));
		assertTrue(isSorted(Gen.restAreas));
		assertTrue(isSorted(Gen.restaurants));
		assertTrue(isSorted(Gen.skiing));
		assertTrue(isSorted(Gen.touristInfo));
	}

	@Test
	public void test_RestAreas() throws FileNotFoundException {
		HeapSortCategory.RestAreas(center);
		assertTrue(isSorted(Gen.restAreas));
	}

	@Test
	public void test_Restaurants() throws FileNotFoundException {
		HeapSortCategory.Restaurants(center);
		assertTrue(isSorted(Gen.restaurants));
		assertTrue(isSorted(Gen.skiing));
		assertTrue(isSorted(Gen.touristInfo));
	}

	@Test
	public void test_Skiing() throws FileNotFoundException {
		HeapSortCategory.Skiing(center);
		assertTrue(isSorted(Gen.skiing));
	}

	@Test
	public void test_TouristInfo() throws FileNotFoundException {
		HeapSortCategory.TouristInfo(center);
		assertTrue(isSorted(Gen.touristInfo));
	}

	/**
	 * Check for increasing order of distance to center as incrementing through
	 * ArrayList.
	 * 
	 * @param x
	 *            ArrayList of location types, to be checked for increasing
	 *            sorted order
	 * @return boolean If the ArrayList is sorted
	 */
	private static boolean isSorted(ArrayList<Location> x) { // Test whether
																// the array
		// entries are in order.
		for (int i = 1; i < x.size(); i++)
			if (less(x, i, i - 1))
				return false;
		return true;
	}

	/**
	 * Uses distTo Location class method to compare location distances to a
	 * center location.
	 * 
	 * @param x
	 *            input ArrayList, being sorted by distTo center
	 * @param i
	 *            index of first Location in an ArrayList x
	 * @param j
	 *            index of second Location in an ArrayList x
	 * @return boolean If Location at index i is closer to center than Location
	 *         at index j
	 * @see Location distTo method
	 */
	private static boolean less(ArrayList<Location> x, int i, int j) {
		double dist_i = x.get(i).distTo(center);
		double dist_j = x.get(j).distTo(center);
		return dist_i < dist_j;
		// return x[i-1].compareTo(x[j-1]) == -1;
	}
}
