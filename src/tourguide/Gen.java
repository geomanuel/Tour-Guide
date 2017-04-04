package tourguide;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Gen {

	static ArrayList<Location> airports = new ArrayList<Location>();
	static ArrayList<Location> alcohol = new ArrayList<Location>();
	static ArrayList<Location> attractions = new ArrayList<Location>();
	static ArrayList<Location> casinos = new ArrayList<Location>();
	static ArrayList<Location> golf = new ArrayList<Location>();
	static ArrayList<Location> hotels = new ArrayList<Location>();
	static ArrayList<Location> lighthouses = new ArrayList<Location>();
	static ArrayList<Location> majorCities = new ArrayList<Location>();
	static ArrayList<Location> mountainPeaks = new ArrayList<Location>();
	static ArrayList<Location> museumsAndArt = new ArrayList<Location>();
	static ArrayList<Location> parksAndCampgrounds = new ArrayList<Location>();
	static ArrayList<Location> restAreas = new ArrayList<Location>();
	static ArrayList<Location> restaurants = new ArrayList<Location>();
	static ArrayList<Location> skiing = new ArrayList<Location>();
	static ArrayList<Location> touristInfo = new ArrayList<Location>();

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

		Scanner file = new Scanner(new File("csv/" + fileName));
		String line = file.nextLine();

		while (file.hasNextLine()) {

			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line, "\t\"");
			double latitude = Double.parseDouble(stk.nextToken());
			double longitude = Double.parseDouble(stk.nextToken());
			String name = stk.nextToken();
			Location t = new Location(name, latitude, longitude);
			category.add(t);
		}

		file.close();
		return category;
	}

}
