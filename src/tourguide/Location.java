package tourguide;
/**
 * 
 * @author George Manuel
 * @since March 2017
 *
 */
public class Location {
	
	//State variables
	private String name;
	private double latitude;
	private double longitude;
	private boolean marked;
	private int uid;
	
	/**
	 * Class constructor
	 * @param name Name of location
	 * @param latitude Latitude of location
	 * @param longitude Longitude of location
	 */
	public Location(String name, double latitude, double longitude){
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.marked = false;
	}
	
	/**
	 * 
	 * @return name of location
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Gives Location new name
	 * @param name New Name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * 
	 * @return latitude of location
	 */
	public double getLatitude(){
		return this.latitude;
	}
	
	/**
	 * Gives Location new latitude
	 * @param latitude New latitude
	 */
	public void setLatitude(double latitude){
		this.latitude = latitude;
	}
	
	/**
	 *
	 * @return longitude of location
	 */
	public double getLongitude(){
		return this.longitude;
	}
	
	/**
	 * Gives location new longitude
	 * @param longitude New longitude
	 */
	public void setLongitude(double longitude){
		this.longitude = longitude;
	}
	
	/**
	 * Implementation of the Haversine algorithm to calculate the distance (in km) between two locations longitude and latitude
	 * @param Location l
	 * @return the distance (in km) between the two locations
	 */
	public double distTo(Location l){
		
		final int earthR = 6371; 
		
		Double longDiff = toRad(l.longitude - this.longitude);
		Double latDiff = toRad(l.latitude - this.latitude);
		
		Double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) + Math.cos(toRad(this.latitude)) * 
				Math.cos(toRad(l.latitude)) * Math.sin(longDiff / 2) * Math.sin(longDiff / 2);
		
		return (2* Math.atan2(Math.sqrt(a), Math.sqrt(1-a))) * earthR;		
	}
	
	/**
	 * Sets the unique id of the location
	 * @param unique id of the location
	 */
	public void setUid(int uid){
		this.uid = uid;
	}
	
	/**
	 * Gets the unique if the location
	 * @return the unique id
	 */
	public int getUid(){
		return this.uid;
	}
	
	/**
	 * 
	 * @return Whether the location is marked or not
	 */
	public boolean isMarked(){
		return this.marked == true;
	}
	
	/**
	 * Marks a location true
	 */
	public void mark(){
		this.marked = true;
	}
	
	//Converts degrees to radians
	private Double toRad(Double deg){
		return deg * (Math.PI/180);
	}
	public static void main(String[] args){
		Location loc1 = new Location("foo", 5, -5);
		Location loc2 = new Location("bar", 29, 40);
		System.out.println(loc1.distTo(loc2));
	}
}
