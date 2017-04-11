package tourguide;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 
 * @author George Manuel
 * This class generates ArrayLists of locations seperated by categories
 */
public class Gen {

	public static ArrayList<Location> airports = new ArrayList<Location>();
	public static ArrayList<Location> alcohol = new ArrayList<Location>();
	public static ArrayList<Location> attractions = new ArrayList<Location>();
	public static ArrayList<Location> casinos = new ArrayList<Location>();
	public static ArrayList<Location> golf = new ArrayList<Location>();
	public static ArrayList<Location> hotels = new ArrayList<Location>();
	public static ArrayList<Location> lighthouses = new ArrayList<Location>();
	public static ArrayList<Location> majorCities = new ArrayList<Location>();
	public static ArrayList<Location> mountainPeaks = new ArrayList<Location>();
	public static ArrayList<Location> museumsAndArt = new ArrayList<Location>();
	public static ArrayList<Location> parksAndCampgrounds = new ArrayList<Location>();
	public static ArrayList<Location> restAreas = new ArrayList<Location>();
	public static ArrayList<Location> restaurants = new ArrayList<Location>();
	public static ArrayList<Location> skiing = new ArrayList<Location>();
	public static ArrayList<Location> touristInfo = new ArrayList<Location>();

	/**
	 * 
	 * @param category Category of location
	 * @return	ArrayList of locations within the category
	 * @throws FileNotFoundException File not found exception
	 */
	public static ArrayList<Location> Generate(ArrayList<Location> category) throws FileNotFoundException {
		String fileName = "";
		if (category == airports)
			fileName = "Airports.txt";
		else if (category == alcohol)
			fileName = "Alcohol.txt";
		else if (category == attractions)
			fileName = "Attractions.txt";
		else if (category == casinos)
			fileName = "Casinos.txt";
		else if (category == golf)
			fileName = "Golf.txt";
		else if (category == hotels)
			fileName = "Hotels.txt";
		else if (category == lighthouses)
			fileName = "Lighthouses.txt";
		else if (category == majorCities)
			fileName = "Major Cities.txt";
		else if (category == mountainPeaks)
			fileName = "Mountain Peaks.txt";
		else if (category == museumsAndArt)
			fileName = "Museums and Art.txt";
		else if (category == parksAndCampgrounds)
			fileName = "Parks and Campgrounds.txt";
		else if (category == restAreas)
			fileName = "Rest Areas.txt";
		else if (category == restaurants)
			fileName = "Restaurants.txt";
		else if (category == skiing)
			fileName = "Skiing.txt";
		else if (category == touristInfo)
			fileName = "Tourist Info.txt";

		Scanner file = new Scanner(new File("csv/" + fileName));	//Loads file
		String line = file.nextLine();	//Skips over the header

		while (file.hasNextLine()) {

			line = file.nextLine();	//Goes to next line
			StringTokenizer stk = new StringTokenizer(line, "\t\"");	//Seperates each line by the tabs
			double latitude = Double.parseDouble(stk.nextToken());		//Loads the latitude, longitude and name of location
			double longitude = Double.parseDouble(stk.nextToken());
			String name = stk.nextToken();
			Location t = new Location(name, latitude, longitude);	//Creates new location object and adds it into the new ArrayList
			category.add(t);	
		}

		file.close();
		return category;
	}

}
