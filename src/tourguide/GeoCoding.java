package tourguide;
import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class represents the GeoCoding object type that allows you to get formatted addresses, longitudes and latitudes from the Google maps API
 * @author Andrew Deschenes
 * @since April 12th, 2017
 *
 */
public class GeoCoding {
	
	private final String apiKey = "AIzaSyBoU_ePES6JM7jBFRq5c4cbNJLhSm2EoB0"; 
	private URL url;
	
	private String jsonHomebase;
	private String homebase;
	
	private JSONObject jobj;
	private JSONArray resultsArray;
	
	
	//Request info from google maps API on homebase address
	private String geocode(){		
        
        String line;
        String json = null;
		
		try{
			
			url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+ this.homebase + "&key=" + apiKey);
  			String input ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><SingleServiceInteface><Header><VERSION>1.0</VERSION><CALLID>39de507e01e40051ad44000825a05f30</CALLID><APPLICATION>DTV_CVP</APPLICATION><CLIENT>DIRECTV</CLIENT></Header><State><STATE_NAME>GETDTVCALLDATA</STATE_NAME><DIALED_NUMBER>193995</DIALED_NUMBER></State><ActionLog/></SingleServiceInteface>";
  			
  			HttpURLConnection HTTPconnection = (HttpURLConnection) url.openConnection();
  			HTTPconnection.setDoOutput(true);
  			HTTPconnection.setRequestMethod("POST");
            
            OutputStream os = HTTPconnection.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader((HTTPconnection.getInputStream())));           
                        
            while ((line = br.readLine()) != null)   
            	if(json == null)
            		json = line + "\n";
            	else
            		json += line + "\n";
                       	
            HTTPconnection.disconnect();
                                    
            return json;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	//Break the json results into an array, where each element has the information for a different address
	private void JsonDecode(){	
		
		jobj = new JSONObject(this.jsonHomebase);
		resultsArray = jobj.getJSONArray("results");		
						
	}
	
	/**
	 * Constructor for Geocoding. Connects to the googlemaps api, gathers info for the entered address and parses the JSON into an array
	 * @param address
	 */
	public GeoCoding(String address){
		
		this.homebase = address;		
		this.homebase = this.homebase.replace(" ","+");
		this.jsonHomebase = geocode();
		JsonDecode();
		
		
	}
	
	/**
	 * The address of the homebase
	 * @return homebase address
	 */
	public String homebase(){
		return homebase.replace("+", " ");
	}
	
	/**
	 * The address of the homebase in JSON format
	 * @return homebase address (JSON format)
	 */
	public String jsonHomebase(){
		return jsonHomebase;
	}

	/**
	 * Formatted address (includes postal code, city, country, etc.)
	 * @return array containing all the formatted addresses found for the entered address
	 */
	public String[] formattedAddress(){
		String[] fa = new String[this.results()];
		
		for(int i = 0; i < resultsArray.length(); i++)
			fa[i] = resultsArray.getJSONObject(i).getString("formatted_address");	
		
		return fa;
	}

	/**
	 * Longitude of the address(es)
	 * @return array containing longitudes of different addresses
	 */
	public double[] longitude(){
		
		double[] lng = new double[this.results()];
		
		for(int i = 0; i < resultsArray.length(); i++)
			lng[i] = resultsArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lng");		
		
		return lng;
	}
	
	/**
	 * Latitude of the address(es)
	 * @return array containing latitudes of different addresses
	 */
	public double[] latitude(){		
		
		double[] lat = new double[this.results()];
		
		for(int i = 0; i < resultsArray.length(); i++)
			lat[i] = resultsArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lat");		
		
		return lat;
	}
	
	/**
	 * Whether or not the entered address exists
	 * @return boolean value whether or not address exists
	 */
	public boolean exists(){
		return(jobj.getString("status").equals("OK"));
	}
	
	/**
	 * Amount of addresses returned by the googlemaps api for the entered address
	 * @return integer containing the amount of results returned
	 */
	public int results(){
		return (resultsArray.length());
	}
	

}
