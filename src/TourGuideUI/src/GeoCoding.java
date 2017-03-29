package TourGuideUI.src;
import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeoCoding {

	private String apiKey = "AIzaSyBoU_ePES6JM7jBFRq5c4cbNJLhSm2EoB0";
	private URL url;
	private String input;

	private String jsonHomebase;
	private String homebase;

	private String geocode() {

		String line;
		String json = null;

		try {

			url = new URL(
					"https://maps.googleapis.com/maps/api/geocode/json?address=" + this.homebase + "&key=" + apiKey);
			input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SingleServiceInteface><Header><VERSION>1.0</VERSION><CALLID>39de507e01e40051ad44000825a05f30</CALLID><APPLICATION>DTV_CVP</APPLICATION><CLIENT>DIRECTV</CLIENT></Header><State><STATE_NAME>GETDTVCALLDATA</STATE_NAME><DIALED_NUMBER>193995</DIALED_NUMBER></State><ActionLog/></SingleServiceInteface>";

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "text/xml");

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			while ((line = br.readLine()) != null)
				if (json == null)
					json = line + "\n";
				else
					json += line + "\n";

			conn.disconnect();

			System.out.println(json);

			return json;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public GeoCoding(String address) {

		this.homebase = address;
		this.homebase = this.homebase.replace(" ", "+");
		this.jsonHomebase = geocode();

	}

	public String homebase() {
		return homebase;
	}

	public String jsonHomebase() {
		return jsonHomebase;
	}

}
