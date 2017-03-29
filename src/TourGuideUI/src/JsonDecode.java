package TourGuideUI.src;
import org.json.*;

import TourGuideUI.*;

public class JsonDecode {

	private String json;
	private JSONObject jobj;
	private static JSONArray resultsArray;
	private static String[] addresses;

	JsonDecode(String json) {

		this.json = json;
		jobj = new JSONObject(this.json);
		resultsArray = jobj.getJSONArray("results");
	}

	public static String formattedAddress() {
		return (resultsArray.getJSONObject(0).getString("formatted_address"));
	}

	public double longitude() {
		return (resultsArray.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
	}

	public double latitude() {
		return (resultsArray.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat"));
	}

	public boolean exists() {
		return (jobj.getString("status").equals("OK"));
	}

	public static String[] getAddresses() {
		addresses = new String[resultsArray.length()];
		for (int i = 0; i < resultsArray.length(); i++) {
			addresses[i] = resultsArray.getJSONObject(i).getString("formatted_address");
		}
		return addresses;
	}

}
