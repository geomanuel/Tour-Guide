package tests;

import static org.junit.Assert.*;
import tourguide.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Alex Trudeau
 * @since April 11, 2017
 */
public class GenTest extends Gen {
	private String[] fileNames = { "Airports.txt", "Casinos.txt", "Hotels.txt" };
	private int[] counts = { 0, 0, 0 };

	@Before
	public void setUp() throws Exception {
		Gen.Generate(airports);
		Gen.Generate(alcohol);
		Gen.Generate(attractions);
		Gen.Generate(casinos);
		Gen.Generate(golf);
		Gen.Generate(hotels);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGenerate() throws FileNotFoundException {
		assertTrue(airports.isEmpty() == false);
		assertTrue(alcohol.isEmpty() == false);
		assertTrue(attractions.isEmpty() == false);
		assertTrue(casinos.isEmpty() == false);
		assertTrue(golf.isEmpty() == false);
		assertTrue(hotels.isEmpty() == false);
		for (int i = 0; i < fileNames.length; i++) {
			Scanner file = new Scanner(new File("csv/" + fileNames[i])); // Loads
																			// file
			file.nextLine(); // Skips over the header
			while (file.hasNextLine()) {
				counts[i]++;
				file.nextLine();
			}
			file.close();
			for (int num : counts) {
				System.out.println(num);
			}
		}
		System.out.println(airports.size() + " " + casinos.size() + " " + hotels.size());
		assertTrue(counts[0] == airports.size() && counts[1] == casinos.size() && counts[2] == hotels.size());
	}
}
