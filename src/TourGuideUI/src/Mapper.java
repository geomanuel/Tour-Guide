/*
 * Copyright (c) 2000-2017 TeamDev Ltd. All rights reserved.
 * Use is subject to Apache 2.0 license terms.
 */
package TourGuideUI.src;

import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Polyline;
import com.teamdev.jxmaps.PolylineOptions;
import com.teamdev.jxmaps.swing.MapView;

import tourguide.DirectedEdge;
import tourguide.Location;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This example demonstrates how to draw polylines on the map.
 *
 * @author Vitaly Eremenko
 */
@SuppressWarnings("serial")
public class Mapper extends MapView {
	public Mapper(Iterable<DirectedEdge> z, HashMap<Integer, Location> uids) {
		// Setting of a ready handler to MapView object. onMapReady will be
		// called when map initialization is done and
		// the map object is ready to use. Current implementation of onMapReady
		// customizes the map object.
		setOnMapReadyHandler(new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {
				// Check if the map is loaded correctly
				if (status == MapStatus.MAP_STATUS_OK) {
					// Getting the associated map object
					final Map map = getMap();
					// Creating a map options object
					MapOptions mapOptions = new MapOptions();
					// Creating a map type control options object
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					// Changing position of the map type control
					controlOptions.setPosition(ControlPosition.TOP_RIGHT);
					// Setting map type control options
					mapOptions.setMapTypeControlOptions(controlOptions);
					// Setting map options
					map.setOptions(mapOptions);
					// Setting the map center
					ArrayList<LatLng> pathInit = new ArrayList<LatLng>();
                    for (DirectedEdge dee : z){
						pathInit.add(new LatLng(uids.get(dee.from()).getLatitude(), uids.get(dee.from()).getLongitude()));
                    	System.out.println(uids.get(dee.from()).getLatitude() + ", " + uids.get(dee.from()).getLongitude());}
                    pathInit.add(pathInit.get(0));
                    map.setCenter(pathInit.get(0));
					// Setting initial zoom value
					map.setZoom(15.0);
					// Creating a path (array of coordinates) that represents a
					// polyline
					LatLng[] path = new LatLng[pathInit.size()];
					for (int i = 0; i < path.length; i++){
						path[i] = pathInit.get(i);
					}

					// Creating a new polyline object
					Polyline polyline = new Polyline(map);
					// Initializing the polyline with created path
					polyline.setPath(path);
					// Creating a polyline options object
					PolylineOptions options = new PolylineOptions();
					// Setting geodesic property value
					options.setGeodesic(true);
					// Setting stroke color value
					options.setStrokeColor("#FF0000");
					// Setting stroke opacity value
					options.setStrokeOpacity(1.0);
					// Setting stroke weight value
					options.setStrokeWeight(2.0);
					// Applying options to the polyline
					polyline.setOptions(options);
				}
			}
		});
	}
}
