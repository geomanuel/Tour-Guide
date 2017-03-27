package tourguide;

public class Location {
	
	private String name;
	private double latitude;
	private double longitude;
	private boolean marked;
	
	
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
	 * 
	 * @param L Location
	 * @return Distance between two locations
	 */
	public double distTo(Location L){
		double dx = this.getLatitude() - L.getLatitude();	//Difference in latitude
		double dy = this.getLongitude() - L.getLongitude();	//Difference in longitude
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));//Pythagorean
	}
	
	/**
	 * Implementation of the Haversine algorithm to calculate the distance (in km) between two locations longitude and latitude
	 * @param Location l
	 * @return the distance (in km) between the two locations
	 */
	public double distToKm(Location l){
		
		final int earthR = 6371; 
		
		Double longDiff = toRad(l.longitude - this.longitude);
		Double latDiff = toRad(l.latitude - this.latitude);
		
		Double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) + Math.cos(toRad(this.latitude)) * 
				Math.cos(toRad(l.latitude)) * Math.sin(longDiff / 2) * Math.sin(longDiff / 2);
		
		return (2* Math.atan2(Math.sqrt(a), Math.sqrt(1-a))) * earthR;
		
		
	}
	
	/**
	 * 
	 * @return Whether the location is marked or not
	 */
	public boolean isMarked(){
		return this.marked == true;
	}
	
	/**
	 * Marks a location
	 */
	public void mark(){
		this.marked = true;
	}
	
	//Convertes degrees to radians
	private Double toRad(Double deg){
		return deg * (Math.PI/180);
	}
	
}
