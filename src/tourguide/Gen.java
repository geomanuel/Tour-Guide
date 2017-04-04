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

	/*
	 * COMMENTING OUT IN CASE WE DECIDE TO GO BACK TO OLD METHOD
	 * 
	 * 
	 * private static void Airports() throws FileNotFoundException{ airports =
	 * new Location[1119]; int n = 0; Scanner file = new Scanner(new
	 * File("csv/Airports.csv")); String line = file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line, ","); double latitude =
	 * Double.parseDouble(stk.nextToken()); double longitude =
	 * Double.parseDouble(stk.nextToken()); String name = stk.nextToken();
	 * airports[n++] = new Location(name, latitude, longitude);
	 * 
	 * } file.close(); }
	 * 
	 * private static void Alcohol() throws FileNotFoundException{ alcohol = new
	 * Location[1054]; int n = 0; Scanner file = new Scanner(new
	 * File("csv/Alcohol.csv")); String line = file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line, ","); double latitude =
	 * Double.parseDouble(stk.nextToken()); double longitude =
	 * Double.parseDouble(stk.nextToken()); String name = stk.nextToken();
	 * alcohol[n++] = new Location(name, latitude, longitude); } file.close();
	 * System.out.println(alcohol[1053].getName()); }
	 * 
	 * private static void Attractions() throws FileNotFoundException{
	 * attractions = new Location[1770]; int n = 0; Scanner file = new
	 * Scanner(new File("csv/Attractions.csv")); String line = file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line, ","); double latitude =
	 * Double.parseDouble(stk.nextToken()); double longitude =
	 * Double.parseDouble(stk.nextToken()); String name = stk.nextToken();
	 * attractions[n++] = new Location(name, latitude, longitude); }
	 * file.close(); }
	 * 
	 * private static void Casinos() throws FileNotFoundException{ casinos = new
	 * Location[1021]; int n = 0; Scanner file = new Scanner(new
	 * File("csv/Casinos.csv")); String line = file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line, ","); double latitude =
	 * Double.parseDouble(stk.nextToken()); double longitude =
	 * Double.parseDouble(stk.nextToken()); String name = stk.nextToken();
	 * casinos[n++] = new Location(name, latitude, longitude); } file.close(); }
	 * 
	 * private static void Golf() throws FileNotFoundException{ golf = new
	 * Location[16916]; int n = 0; Scanner file = new Scanner(new
	 * File("csv/Golf.csv")); String line = file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line, ","); double latitude =
	 * Double.parseDouble(stk.nextToken()); double longitude =
	 * Double.parseDouble(stk.nextToken()); String name = stk.nextToken();
	 * golf[n++] = new Location(name, latitude, longitude); } file.close(); }
	 * 
	 * private static void Hotels() throws FileNotFoundException{ hotels = new
	 * Location[12504]; int n = 0; Scanner file = new Scanner(new
	 * File("csv/Hotels.csv")); String line = file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line, ","); double latitude =
	 * Double.parseDouble(stk.nextToken()); double longitude =
	 * Double.parseDouble(stk.nextToken()); String name = stk.nextToken();
	 * hotels[n++] = new Location(name, latitude, longitude); } file.close(); }
	 * 
	 * private static void Lighthouses() throws FileNotFoundException{
	 * lighthouses = new Location[76]; int n = 0; Scanner file = new Scanner(new
	 * File("csv/Lighthouses.csv")); String line = file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line, ","); double latitude =
	 * Double.parseDouble(stk.nextToken()); double longitude =
	 * Double.parseDouble(stk.nextToken()); String name = stk.nextToken();
	 * lighthouses[n++] = new Location(name, latitude, longitude); }
	 * file.close(); }
	 * 
	 * private static void MajorCities() throws FileNotFoundException{
	 * majorCities = new Location[5448]; int n = 0; Scanner file = new
	 * Scanner(new File("csv/Major Cities.csv")); String line = file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line, ","); double latitude =
	 * Double.parseDouble(stk.nextToken()); double longitude =
	 * Double.parseDouble(stk.nextToken()); String name = stk.nextToken();
	 * majorCities[n++] = new Location(name, latitude, longitude); }
	 * file.close(); }
	 * 
	 * private static void MountainPeaks() throws FileNotFoundException{
	 * mountainPeaks = new Location[1556]; int n = 0; Scanner file = new
	 * Scanner(new File("csv/Mountain Peaks.csv")); String line =
	 * file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line, ","); double latitude =
	 * Double.parseDouble(stk.nextToken()); double longitude =
	 * Double.parseDouble(stk.nextToken()); String name = stk.nextToken();
	 * mountainPeaks[n++] = new Location(name, latitude, longitude); }
	 * file.close(); }
	 * 
	 * private static void MuseumsArt() throws FileNotFoundException{
	 * museumsAndArt = new Location[358]; int n = 0; Scanner file = new
	 * Scanner(new File("csv/Museums and Art.csv")); String line =
	 * file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line, ","); double latitude =
	 * Double.parseDouble(stk.nextToken()); double longitude =
	 * Double.parseDouble(stk.nextToken()); String name = stk.nextToken();
	 * museumsAndArt[n++] = new Location(name, latitude, longitude); }
	 * file.close(); }
	 * 
	 * private static void ParksAndCampgrounds() throws FileNotFoundException{
	 * parksAndCampgrounds = new Location[8863]; int n = 0; Scanner file = new
	 * Scanner(new File("csv/Parks and Campgrounds.csv")); String line =
	 * file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line, ","); double latitude =
	 * Double.parseDouble(stk.nextToken()); double longitude =
	 * Double.parseDouble(stk.nextToken()); String name = stk.nextToken();
	 * parksAndCampgrounds[n++] = new Location(name, latitude, longitude); }
	 * file.close(); }
	 * 
	 * private static void RestAreas() throws FileNotFoundException{ restAreas =
	 * new Location[3798]; int n = 0; Scanner file = new Scanner(new
	 * File("csv/Rest Areas.txt")); String line = file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line, "\t\""); double latitude =
	 * Double.parseDouble(stk.nextToken()); double longitude =
	 * Double.parseDouble(stk.nextToken()); String name = stk.nextToken();
	 * restAreas[n++] = new Location(name, latitude, longitude); } file.close();
	 * }
	 * 
	 * private static void Restaurants() throws FileNotFoundException{
	 * restaurants = new Location[59616]; int n = 0; Scanner file = new
	 * Scanner(new File("csv/Restaurants.csv")); String line = file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line); double latitude =
	 * Double.parseDouble(stk.nextToken(",")); double longitude =
	 * Double.parseDouble(stk.nextToken(",")); String name = stk.nextToken(",");
	 * restaurants[n++] = new Location(name, latitude, longitude); }
	 * file.close(); }
	 * 
	 * private static void Skiing() throws FileNotFoundException{ skiing = new
	 * Location[79]; int n = 0; Scanner file = new Scanner(new
	 * File("csv/Skiing.csv")); String line = file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line); double latitude =
	 * Double.parseDouble(stk.nextToken(",")); double longitude =
	 * Double.parseDouble(stk.nextToken(",")); String name = stk.nextToken(",");
	 * skiing[n++] = new Location(name, latitude, longitude); } file.close(); }
	 * 
	 * private static void TouristInfo() throws FileNotFoundException{
	 * touristInfo = new Location[91]; int n = 0; Scanner file = new Scanner(new
	 * File("csv/Tourist Info.csv")); String line = file.nextLine();
	 * 
	 * while(file.hasNextLine()){ line = file.nextLine(); StringTokenizer stk =
	 * new StringTokenizer(line); double latitude =
	 * Double.parseDouble(stk.nextToken(",")); double longitude =
	 * Double.parseDouble(stk.nextToken(",")); String name = stk.nextToken(",");
	 * touristInfo[n++] = new Location(name, latitude, longitude); }
	 * file.close(); }
	 */

	public static void main(String[] args) throws FileNotFoundException {
		
		
	}
}
