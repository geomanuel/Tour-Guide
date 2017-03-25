package tourguide;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.*;


public class Gen {

	static Location[] airports;
	static Location[] alcohol;
	static Location[] attractions;
	static Location[] casinos;
	static Location[] golf;
	static Location[] hotels;
	static Location[] lighthouses;
	static Location[] majorCities;
	static Location[] mountainPeaks;
	static Location[] museumsAndArt;
	static Location[] parksAndCampgrounds;
	static Location[] restAreas;
	static Location[] restaurants;
	static Location[] skiing;
	static Location[] touristInfo;
	
	private static void Airports() throws FileNotFoundException{
		airports = new Location[1119];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Airports.csv"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line, ",");
			double latitude = Double.parseDouble(stk.nextToken());
			double longitude = Double.parseDouble(stk.nextToken());
			String name = stk.nextToken();
			airports[n++] = new Location(name, latitude, longitude);
			
		}
		file.close();
	}
	
	private static void Alcohol() throws FileNotFoundException{
		alcohol = new Location[1054];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Alcohol.csv"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line, ",");
			double latitude = Double.parseDouble(stk.nextToken());
			double longitude = Double.parseDouble(stk.nextToken());
			String name = stk.nextToken();
			alcohol[n++] = new Location(name, latitude, longitude);
		}
		file.close();
		System.out.println(alcohol[1053].getName());
	}
	
	private static void Attractions() throws FileNotFoundException{
		attractions = new Location[1770];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Attractions.csv"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line, ",");
			double latitude = Double.parseDouble(stk.nextToken());
			double longitude = Double.parseDouble(stk.nextToken());
			String name = stk.nextToken();
			attractions[n++] = new Location(name, latitude, longitude);
		}
		file.close();
	}
	
	private static void Casinos() throws FileNotFoundException{
		casinos = new Location[1021];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Casinos.csv"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line, ",");
			double latitude = Double.parseDouble(stk.nextToken());
			double longitude = Double.parseDouble(stk.nextToken());
			String name = stk.nextToken();
			casinos[n++] = new Location(name, latitude, longitude);
		}
		file.close();
	}
	
	private static void Golf() throws FileNotFoundException{
		golf = new Location[16916];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Golf.csv"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line, ",");
			double latitude = Double.parseDouble(stk.nextToken());
			double longitude = Double.parseDouble(stk.nextToken());
			String name = stk.nextToken();
			golf[n++] = new Location(name, latitude, longitude);
		}
		file.close();
	}
	
	private static void Hotels() throws FileNotFoundException{
		hotels = new Location[12504];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Hotels.csv"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line, ",");
			double latitude = Double.parseDouble(stk.nextToken());
			double longitude = Double.parseDouble(stk.nextToken());
			String name = stk.nextToken();
			hotels[n++] = new Location(name, latitude, longitude);
		}
		file.close();
	}
	
	private static void Lighthouses() throws FileNotFoundException{
		lighthouses = new Location[76];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Lighthouses.csv"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line, ",");
			double latitude = Double.parseDouble(stk.nextToken());
			double longitude = Double.parseDouble(stk.nextToken());
			String name = stk.nextToken();
			lighthouses[n++] = new Location(name, latitude, longitude);
		}
		file.close();
	}
	
	private static void MajorCities() throws FileNotFoundException{
		majorCities = new Location[5448];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Major Cities.csv"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line, ",");
			double latitude = Double.parseDouble(stk.nextToken());
			double longitude = Double.parseDouble(stk.nextToken());
			String name = stk.nextToken();
			majorCities[n++] = new Location(name, latitude, longitude);
		}
		file.close();
	}
	
	private static void MountainPeaks() throws FileNotFoundException{
		mountainPeaks = new Location[1556];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Mountain Peaks.csv"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line, ",");
			double latitude = Double.parseDouble(stk.nextToken());
			double longitude = Double.parseDouble(stk.nextToken());
			String name = stk.nextToken();
			mountainPeaks[n++] = new Location(name, latitude, longitude);
		}
		file.close();
	}
	
	private static void MuseumsArt() throws FileNotFoundException{
		museumsAndArt = new Location[358];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Museums and Art.csv"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line, ",");
			double latitude = Double.parseDouble(stk.nextToken());
			double longitude = Double.parseDouble(stk.nextToken());
			String name = stk.nextToken();
			museumsAndArt[n++] = new Location(name, latitude, longitude);
		}
		file.close();
	}
	
	private static void ParksAndCampgrounds() throws FileNotFoundException{
		parksAndCampgrounds = new Location[8863];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Parks and Campgrounds.csv"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line, ",");
			double latitude = Double.parseDouble(stk.nextToken());
			double longitude = Double.parseDouble(stk.nextToken());
			String name = stk.nextToken();
			parksAndCampgrounds[n++] = new Location(name, latitude, longitude);
		}
		file.close();
	}
	
	private static void RestAreas() throws FileNotFoundException{
		restAreas = new Location[3798];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Rest Areas.txt"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line, "\t\"");
			double latitude = Double.parseDouble(stk.nextToken());
			double longitude = Double.parseDouble(stk.nextToken());
			String name = stk.nextToken();
			restAreas[n++] = new Location(name, latitude, longitude);
		}
		file.close();
	}
	
	private static void Restaurants() throws FileNotFoundException{
		restaurants = new Location[59616];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Restaurants.csv"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line);
			double latitude = Double.parseDouble(stk.nextToken(","));
			double longitude = Double.parseDouble(stk.nextToken(","));
			String name = stk.nextToken(",");
			restaurants[n++] = new Location(name, latitude, longitude);
		}
		file.close();
	}
	
	private static void Skiing() throws FileNotFoundException{
		skiing = new Location[79];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Skiing.csv"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line);
			double latitude = Double.parseDouble(stk.nextToken(","));
			double longitude = Double.parseDouble(stk.nextToken(","));
			String name = stk.nextToken(",");
			skiing[n++] = new Location(name, latitude, longitude);
		}
		file.close();
	}
	
	private static void TouristInfo() throws FileNotFoundException{
		touristInfo = new Location[91];
		int n = 0;
		Scanner file = new Scanner(new File("csv/Tourist Info.csv"));
		String line = file.nextLine();
		
		while(file.hasNextLine()){
			line = file.nextLine();
			StringTokenizer stk = new StringTokenizer(line);
			double latitude = Double.parseDouble(stk.nextToken(","));
			double longitude = Double.parseDouble(stk.nextToken(","));
			String name = stk.nextToken(",");
			touristInfo[n++] = new Location(name, latitude, longitude);
		}
		file.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Airports();
		Alcohol();
		Attractions();
		Casinos();
		Golf();
		Hotels();
		Lighthouses();
		MajorCities();
		MountainPeaks();
		MuseumsArt();
		ParksAndCampgrounds();
		RestAreas();
		Restaurants();
		Skiing();
		TouristInfo();
	}
	
}
