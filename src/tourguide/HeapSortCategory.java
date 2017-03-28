package tourguide;
import java.io.FileNotFoundException;

/**
* <h1>Heap Sort Implementation</h1>
* Heap.java implements heap sort..
* @author  Alexander Pattison
* @version 1.0
* @since   2017-03-28
*/
public class HeapSortCategory extends Gen {

	/**
	 * HeapSort all location categories by distance to a center location.
	 * @param radius
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void All(Location center) throws FileNotFoundException {
		Airports(center);
		Alcohol(center);
		Attractions(center);
		Casinos(center);
		Golf(center);
		Hotels(center);
		Lighthouses(center);
		MajorCities(center);
		MountainPeaks(center);
		MuseumsAndArt(center);
		ParksAndCampgrounds(center);
		RestAreas(center);
		Restaurants(center);
		Skiing(center);
		TouristInfo(center);
	}

	/**
	 * HeapSort airports category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void Airports(Location center) throws FileNotFoundException {
		Gen.Generate(airports);
		Heap.sortHeap(Gen.airports, center);
	}
	
	/**
	 * HeapSort alcohol category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void Alcohol(Location center) throws FileNotFoundException {
		Gen.Generate(alcohol);
		Heap.sortHeap(Gen.alcohol, center);
	}

	/**
	 * HeapSort attractions category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void Attractions(Location center) throws FileNotFoundException {
		Gen.Generate(attractions);
		Heap.sortHeap(Gen.attractions, center);
	}

	/**
	 * HeapSort casinos category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void Casinos(Location center) throws FileNotFoundException {
		Gen.Generate(casinos);
		Heap.sortHeap(Gen.casinos, center);
	}
	
	/**
	 * HeapSort golf category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void Golf(Location center) throws FileNotFoundException {
		Gen.Generate(golf);
		Heap.sortHeap(Gen.golf, center);
	}
	
	/**
	 * HeapSort hotels category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void Hotels(Location center) throws FileNotFoundException {
		Gen.Generate(hotels);
		Heap.sortHeap(Gen.hotels, center);
	}
	
	/**
	 * HeapSort lighthouses category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void Lighthouses(Location center) throws FileNotFoundException {
		Gen.Generate(lighthouses);
		Heap.sortHeap(Gen.lighthouses, center);
	}
	
	/**
	 * HeapSort majorCities category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void MajorCities(Location center) throws FileNotFoundException {
		Gen.Generate(majorCities);
		Heap.sortHeap(Gen.majorCities, center);
	}
	
	/**
	 * HeapSort mountainPeaks category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void MountainPeaks(Location center) throws FileNotFoundException {
		Gen.Generate(mountainPeaks);
		Heap.sortHeap(Gen.mountainPeaks, center);
	}
	
	/**
	 * HeapSort museumsAndArt category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void MuseumsAndArt(Location center) throws FileNotFoundException {
		Gen.Generate(museumsAndArt);
		Heap.sortHeap(Gen.museumsAndArt, center);
	}
	
	/**
	 * HeapSort parksAndCampgrounds category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void ParksAndCampgrounds(Location center) throws FileNotFoundException {
		Gen.Generate(parksAndCampgrounds);
		Heap.sortHeap(Gen.parksAndCampgrounds, center);
	}
	
	/**
	 * HeapSort restAreas category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void RestAreas(Location center) throws FileNotFoundException {
		Gen.Generate(restAreas);
		Heap.sortHeap(Gen.restAreas, center);
	}
	
	/**
	 * HeapSort restaurants category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void Restaurants(Location center) throws FileNotFoundException {
		Gen.Generate(restaurants);
		Heap.sortHeap(Gen.restaurants, center);
	}
	
	/**
	 * HeapSort skiing category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void Skiing(Location center) throws FileNotFoundException {
		Gen.Generate(skiing);
		Heap.sortHeap(Gen.skiing, center);
	}
	
	/**
	 * HeapSort touristInfo category by distance to a center location.
	 * @param center
	 * @throws FileNotFoundException
	 */
	public static void TouristInfo(Location center) throws FileNotFoundException {
		Gen.Generate(touristInfo);
		Heap.sortHeap(Gen.touristInfo, center);
	}

	/**
	 * Main method, used to test class methods.
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Location SP = new Location("StartingPoint", 48, -58);
		HeapSortCategory.All(SP);
		
		System.out.println("ArrayList size= "+Gen.airports.size());
		for (int i = 0; i < Gen.airports.size(); i++) {
			double dist = Gen.airports.get(i).distTo(SP);
			System.out.println("("+Gen.airports.get(i).getLatitude() + ", " + Gen.airports.get(i).getLongitude() + "), d= "+dist);
		}
		
//		System.out.println("ArrayList size= "+Gen.restaurants.size());
//		for (int i = 0; i < Gen.restaurants.size(); i++) {
//			double dist = Gen.restaurants.get(i).distTo(SP);
//			System.out.println("("+Gen.restaurants.get(i).getLatitude() + ", " + Gen.restaurants.get(i).getLongitude() + "), d= "+dist);
//		}
	
	}

}
