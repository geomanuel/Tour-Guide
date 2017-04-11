package tourguide;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;

import com.teamdev.jxmaps.MapViewOptions;
/**
 * 
 * This class implements the code found in Client.java/Client2_0.java.
 * See these classes for details on code implementation and structure
 * for the "inner workings" of this class.
 * 
 * @author Alex Trudeau, Andrew Deschenes
 * @since March 28, 2017
 */
public class TourGuideUI {
	private JFrame mf; // main frame
	private JLabel title;
	private JPanel homepage;
	private JPanel selectionpage;
	private JPanel locationSelect;
	private JPanel pages;
	private JPanel mappage;
	private JTextField locationBox;
	private JLabel locationBoxLabel;
	private JLabel locationSelectLabel;
	private JButton enterLocationBtn;
	private JButton chooseLocationBtn;

	private JLabel categoriesLabel;

	private GridBagConstraints gbc;

	private JButton tourBtn;

	private int counter = 3;

	private CardLayout cardLayout;

	private Color titleColor = new Color(57, 156, 255);
	private Font largeText = new Font("Impact", Font.PLAIN, 74);
	private Font mediumText = new Font("Impact", Font.PLAIN, 34);

	private static String homeBase;
	private static Queue<String> locationQ = new Queue<String>();
	private static GeoCoding gc;
	private JButton genericBtn;
	private JPanel optionPanel;
	private JPanel listPanel;

	private static String hbAddress;
	private static double hbLong;
	private static double hbLat;
	private static Location hbLoc;
	private static Location hbCopy;
	private static HashMap<Integer, Location> uids;
	private static Iterable<DirectedEdge> z;
	
	public TourGuideUI() {
		prepareGUI();
	}
	/**
	 * Prepares the content to be displayed on the GUI
	 */
	private void prepareGUI() {

		// Main frame properties
		mf = new JFrame("Tour Guide");
		Toolkit tool = Toolkit.getDefaultToolkit();
		int x = (int) tool.getScreenSize().getWidth();
		int y = (int) tool.getScreenSize().getHeight();
		mf.setSize(x, y);
		mf.getContentPane().setBackground(Color.WHITE);

		// title (JLabel) properties
		title = new JLabel("Tour Guide", JLabel.CENTER);
		title.setFont(largeText);
		title.setForeground(titleColor);
		title.setVerticalAlignment(JLabel.TOP);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);

		/////////////////////////////////////////////////////////////////////// HOMEPAGE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		// text above locationBox properties
		locationBoxLabel = new JLabel("Enter a location:", JLabel.CENTER);
		locationBoxLabel.setFont(mediumText);
		locationBoxLabel.setForeground(titleColor);
		locationBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		// locationBox text field Properties
		locationBox = new JTextField(10);
		locationBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, locationBox.getPreferredSize().height + 50));
		locationBox.setFont(mediumText);
		locationBox.setAlignmentX(Component.CENTER_ALIGNMENT);

		// locationBox button properties
		enterLocationBtn = new JButton("Submit");
		enterLocationBtn.setFont(mediumText);
		enterLocationBtn.setBackground(titleColor);
		enterLocationBtn.setForeground(Color.WHITE);
		enterLocationBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Homepage properties
		homepage = new JPanel();
		homepage.setLayout(new BoxLayout(homepage, BoxLayout.Y_AXIS));
		homepage.setAlignmentX(Component.CENTER_ALIGNMENT);
		homepage.setBackground(Color.WHITE);
		;

		////////////////////////////////////////////////////////////////// LOCATIONSELECTION\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		locationSelect = new JPanel();
		locationSelect.setBackground(Color.WHITE);
		locationSelect.setLayout(new BoxLayout(locationSelect, BoxLayout.Y_AXIS));
		locationSelectLabel = new JLabel("Choose Your Location:", JLabel.CENTER);
		locationSelectLabel.setFont(mediumText);
		locationSelectLabel.setForeground(titleColor);
		locationSelectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		locationSelect.add(locationSelectLabel);
		/////////////////////////////////////////////////////////////////////// SELECTIONPAGE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		// selection page properties
		selectionpage = new JPanel();
		selectionpage.setLayout(new BoxLayout(selectionpage, BoxLayout.Y_AXIS));
		optionPanel = new JPanel();
		listPanel = new JPanel();

		optionPanel.setLayout(new GridBagLayout());
		optionPanel.setBackground(Color.WHITE);
		listPanel.setLayout(new WrapLayout(WrapLayout.LEFT));
		listPanel.setBackground(Color.WHITE);

		selectionpage.add(listPanel);
		selectionpage.add(optionPanel);
		gbc = new GridBagConstraints();

		categoriesLabel = new JLabel("Select Categories To Add Them to Your Tour:", JLabel.CENTER);
		categoriesLabel.setFont(mediumText);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 6;

		optionPanel.add(categoriesLabel, gbc);
		gbc.gridwidth = 1;
		String[] POIs = { "Airport", "Alcohol", "Local Attraction", "Casino", "Golf", "Hotels", "Lighthouse",
				"Major City", "Mountain Peak", "Museum/Art", "Park/Campground", "Rest Area", "Restaurant", "Skiing",
				"Tourist Info" };
		int k = 0;
		//generate a button for each of the categories
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 6; j++) {
				genericBtn = new JButton(POIs[k]);
				k++;
				genericBtn.setFont(mediumText);
				genericBtn.setBackground(titleColor);
				genericBtn.setForeground(Color.WHITE);
				genericBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
				genericBtn.setAlignmentY(Component.TOP_ALIGNMENT);
				genericBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//get the information associated with the button that was clicked and add it to the queue
						System.out.println(((AbstractButton) e.getSource()).getText());
						locationQ.enqueue(((AbstractButton) e.getSource()).getText());
						if (counter - 2 > 23) {
							tourBtn.doClick();
						}
						JLabel l = new JLabel(counter - 2 + ") " + ((AbstractButton) e.getSource()).getText());
						l.setFont(mediumText);
						l.setForeground(titleColor);
						l.setVerticalAlignment(JLabel.TOP);
						;
						listPanel.add(l);

						counter++;

						mf.validate();
						mf.repaint();

					}
				});
				gbc.insets = new Insets(0, 10, 0, 10);
				gbc.gridx = i;
				gbc.gridy = j;

				optionPanel.add(genericBtn, gbc);
				optionPanel.revalidate();
				optionPanel.repaint();
			}
		}
		tourBtn = new JButton("Generate My Tour!");
		tourBtn.setFont(mediumText);
		tourBtn.setBackground(titleColor);
		tourBtn.setForeground(Color.WHITE);
		gbc.insets = new Insets(50, 50, 0, 50);
		gbc.gridy = 8;
		gbc.gridx = 0;
		gbc.gridwidth = 6;
		optionPanel.add(tourBtn, gbc);

		/////////////////////////////////////////////////////////////////////// MAPPAGE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		mappage = new JPanel();
		mappage.setLayout(new BorderLayout());
		///////////////////////////////////////////////////////////////////////////// \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		// Cardlayout containing all pages
		pages = new JPanel(new CardLayout());
		pages.add(homepage, "Homepage");
		pages.add(locationSelect, "LocationSelect");
		pages.add(selectionpage, "SelectionPage");
		pages.add(mappage, "MapPage");

		cardLayout = (CardLayout) pages.getLayout();

		// Adding things to main frame
		mf.add(title);
		mf.add(pages);

		// Close and stop the program when the x button is clicked
		mf.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}

		});

	}
	/**
	 * Displays the java swing GUI with the respective selection screens
	 */
	public void show() {

		homepage.add(title);
		homepage.add(Box.createVerticalStrut(20));
		homepage.add(locationBoxLabel);
		homepage.add(Box.createVerticalStrut(50));
		homepage.add(locationBox);
		homepage.add(Box.createVerticalStrut(75));
		homepage.add(enterLocationBtn);

		// On enterlocatiobBtn click
		enterLocationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeBase = locationBox.getText();
				cardLayout.next(pages);

				gc = new GeoCoding(homeBase);
				// dynamically generate buttons for multiple returned addresses
				for (int i = 0; i < gc.formattedAddress().length; i++) {
					chooseLocationBtn = new JButton(
							gc.formattedAddress()[i] + "; " + gc.latitude()[i] + "; " + gc.longitude()[i]);
					chooseLocationBtn.setFont(mediumText);
					chooseLocationBtn.setBackground(titleColor);
					chooseLocationBtn.setForeground(Color.WHITE);
					chooseLocationBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
					chooseLocationBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//get the button information and add it to the homebase variables
							String[] fullAddress = ((AbstractButton) e.getSource()).getText().split("; ");
							hbAddress = fullAddress[0];
							hbLong = Double.parseDouble(fullAddress[2]);
							hbLat = Double.parseDouble(fullAddress[1]);
							
							String tempLong = String.format("%.6f", hbLong);
							String tempLat = String.format("%.6f", hbLat);
							
							hbLong = Double.parseDouble(tempLong);
							hbLat = Double.parseDouble(tempLat);
							
							hbLoc = new Location(hbAddress, hbLat, hbLong);
							hbCopy = new Location(hbAddress, hbLat, hbLong);
							System.out.println(hbAddress + "; " + hbLong + "; " + hbLat);
							cardLayout.next(pages);

						}
					});
					locationSelect.add(Box.createVerticalStrut(30));
					locationSelect.add(chooseLocationBtn);
				}
			}
		});

		tourBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(pages);
				uids = new HashMap<Integer,Location>();
				final double MAX_RADIUS = 200;

				//sorts all categories
				//HeapSortCategory.All(hbLoc);

				//Initializes Linear Search
				LinearSearch ls = new LinearSearch();
				
				//creates 2 dimensional ArrayList called validLocs that contains sorted and searched ArrayList of categories in queue
				ArrayList<ArrayList<Location>> validLocs = new ArrayList<ArrayList<Location>>();
				String deq;
				int locationQSize = locationQ.size();
				int uidCounter = 0;
				
				hbLoc.setUid(uidCounter); //give homebase location a uid
				uids.put(uidCounter, hbLoc);
				
				hbCopy.setUid(++uidCounter); 
				uids.put(uidCounter, hbCopy);
				
				//For every element in the preference Q (everything the user wants to see)
				for (int j = 0; j < locationQSize; j++){
								
					deq = locationQ.dequeue();
					
					if (deq.equals("Airport")){ 
						
						try {
							HeapSortCategory.Airports(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} //Generate and sort list of Airports (by distance to hbLoc in km)
						
						ArrayList<Location> airports = new ArrayList<Location>(); //Initialize a new array to hold all airports within the radius
						
						for (int i = 0; i < ls.floor(Gen.airports, MAX_RADIUS, hbLoc); i++) {
							airports.add(Gen.airports.get(i));
							Gen.airports.get(i).setUid(++uidCounter); //Give Location object a unique integer id
							uids.put(uidCounter, Gen.airports.get(i)); //Store unique id and Location object in hashmap
						}
						
						if(!airports.isEmpty()) validLocs.add(airports); //If airports exist within the radius, add to validLocsS
						
					}
					else if (deq.equals("Alcohol")){
						
						try {
							HeapSortCategory.Alcohol(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						ArrayList<Location> alcohol = new ArrayList<Location>();
						
							for (int i = 0; i < ls.floor(Gen.alcohol, MAX_RADIUS, hbLoc); i++) {
								alcohol.add(Gen.alcohol.get(i));
								Gen.alcohol.get(i).setUid(++uidCounter);
								uids.put(uidCounter, Gen.alcohol.get(i));
							}
						
						if(!alcohol.isEmpty()) validLocs.add(alcohol);
						
					}
					else if (deq.equals("Attraction")){
						
						try {
							HeapSortCategory.Attractions(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						ArrayList<Location> attractions = new ArrayList<Location>();
						
						for (int i = 0; i < ls.floor(Gen.attractions, MAX_RADIUS, hbLoc); i++) {
							attractions.add(Gen.attractions.get(i));
							Gen.attractions.get(i).setUid(++uidCounter);
							uids.put(uidCounter, Gen.attractions.get(i));
						}

						if(!attractions.isEmpty()) validLocs.add(attractions);
					}
					else if (deq.equals("Casino")){
						
						try {
							HeapSortCategory.Casinos(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						ArrayList<Location> casinos = new ArrayList<Location>();
						
						for (int i = 0; i < ls.floor(Gen.casinos, MAX_RADIUS, hbLoc); i++) {
							casinos.add(Gen.casinos.get(i));
							Gen.casinos.get(i).setUid(++uidCounter);
							uids.put(uidCounter, Gen.casinos.get(i));
						}
						
						if(!casinos.isEmpty()) validLocs.add(casinos);
					}
					else if (deq.equals("Golf")){
						
						try {
							HeapSortCategory.Golf(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						ArrayList<Location> golf = new ArrayList<Location>();
						
						for (int i = 0; i < ls.floor(Gen.golf, MAX_RADIUS, hbLoc); i++) {
							golf.add(Gen.golf.get(i));
							Gen.golf.get(i).setUid(++uidCounter);
							uids.put(uidCounter, Gen.golf.get(i));
						}

						if(!golf.isEmpty()) validLocs.add(golf);
					}
					else if (deq.equals("Hotels")){
						
						try {
							HeapSortCategory.Hotels(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						ArrayList<Location> hotels = new ArrayList<Location>();
						
						for (int i = 0; i < ls.floor(Gen.hotels, MAX_RADIUS, hbLoc); i++) {
							hotels.add(Gen.hotels.get(i));
							Gen.hotels.get(i).setUid(++uidCounter);
							uids.put(uidCounter, Gen.hotels.get(i));
						}

						if(!hotels.isEmpty()) validLocs.add(hotels);
					}
					else if (deq.equals("Lighthouse")){
						
						try {
							HeapSortCategory.Lighthouses(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						ArrayList<Location> lighthouses = new ArrayList<Location>();
						
						for (int i = 0; i < ls.floor(Gen.lighthouses, MAX_RADIUS, hbLoc); i++) {
							lighthouses.add(Gen.lighthouses.get(i));
							Gen.lighthouses.get(i).setUid(++uidCounter);
							uids.put(uidCounter, Gen.lighthouses.get(i));
						}

						if(!lighthouses.isEmpty()) validLocs.add(lighthouses);
					}
					else if (deq.equals("Major City")){
						
						try {
							HeapSortCategory.MajorCities(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						ArrayList<Location> majorCities = new ArrayList<Location>();
						
						for (int i = 0; i < ls.floor(Gen.majorCities, MAX_RADIUS, hbLoc); i++) {
							majorCities.add(Gen.majorCities.get(i));
							Gen.majorCities.get(i).setUid(++uidCounter);
							uids.put(uidCounter, Gen.majorCities.get(i));
						}

						if(!majorCities.isEmpty()) validLocs.add(majorCities);
					}
					else if (deq.equals("Mountain Peak")){
						
						try {
							HeapSortCategory.MountainPeaks(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						ArrayList<Location> mountainPeaks = new ArrayList<Location>();
						
						for (int i = 0; i < ls.floor(Gen.mountainPeaks, MAX_RADIUS, hbLoc); i++) {
							mountainPeaks.add(Gen.mountainPeaks.get(i));
							Gen.mountainPeaks.get(i).setUid(++uidCounter);
							uids.put(uidCounter, Gen.mountainPeaks.get(i));
						}

						if(!mountainPeaks.isEmpty()) validLocs.add(mountainPeaks);
					}
					else if (deq.equals("Museum/Art")){
						
						try {
							HeapSortCategory.MuseumsAndArt(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						ArrayList<Location> museumsAndArt = new ArrayList<Location>();
						
						for (int i = 0; i < ls.floor(Gen.museumsAndArt, MAX_RADIUS, hbLoc); i++) {
							museumsAndArt.add(Gen.museumsAndArt.get(i));
							Gen.museumsAndArt.get(i).setUid(++uidCounter);
							uids.put(uidCounter, Gen.museumsAndArt.get(i));
						}

						if(!museumsAndArt.isEmpty()) validLocs.add(museumsAndArt);
					}
					else if (deq.equals("Park/Campground")){
						
						try {
							HeapSortCategory.ParksAndCampgrounds(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						ArrayList<Location> parksAndCampgrounds = new ArrayList<Location>();
						
							for (int i = 0; i < ls.floor(Gen.parksAndCampgrounds, MAX_RADIUS, hbLoc); i++) {
								parksAndCampgrounds.add(Gen.parksAndCampgrounds.get(i));
								Gen.parksAndCampgrounds.get(i).setUid(++uidCounter);
								uids.put(uidCounter, Gen.parksAndCampgrounds.get(i));	
							
						}
						
						if(!parksAndCampgrounds.isEmpty()) validLocs.add(parksAndCampgrounds);
					}
					else if (deq.equals("Rest Area")){
						
						try {
							HeapSortCategory.RestAreas(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						ArrayList<Location> restAreas = new ArrayList<Location>();
						
						for (int i = 0; i < ls.floor(Gen.restAreas, MAX_RADIUS, hbLoc); i++) {
							restAreas.add(Gen.restAreas.get(i));
							Gen.restAreas.get(i).setUid(++uidCounter);
							uids.put(uidCounter, Gen.restAreas.get(i));
						}

						if(!restAreas.isEmpty()) validLocs.add(restAreas);
					}
					else if (deq.equals("Restaurant")){
						
						try {
							HeapSortCategory.Restaurants(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						ArrayList<Location> restaurants = new ArrayList<Location>();
						
						for (int i = 0; i < ls.floor(Gen.restaurants, MAX_RADIUS, hbLoc); i++) {
							restaurants.add(Gen.restaurants.get(i));
							Gen.restaurants.get(i).setUid(++uidCounter);
							uids.put(uidCounter, Gen.restaurants.get(i));
						}

						if(!restaurants.isEmpty()) validLocs.add(restaurants);
					}
					else if (deq.equals("Skiing")){
						
						try {
							HeapSortCategory.Skiing(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						ArrayList<Location> skiing = new ArrayList<Location>();
						
						for (int i = 0; i < ls.floor(Gen.skiing, MAX_RADIUS, hbLoc); i++) {
							skiing.add(Gen.skiing.get(i));
							Gen.skiing.get(i).setUid(++uidCounter);
							uids.put(uidCounter, Gen.skiing.get(i));
						}

						if(!skiing.isEmpty()) validLocs.add(skiing);
					}
					else if (deq.equals("Tourist Info")){
						
						try {
							HeapSortCategory.TouristInfo(hbLoc);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						ArrayList<Location> touristInfo = new ArrayList<Location>();
						
						for (int i = 0; i < ls.floor(Gen.touristInfo, MAX_RADIUS, hbLoc); i++) {
							touristInfo.add(Gen.touristInfo.get(i));
							Gen.touristInfo.get(i).setUid(++uidCounter);
							uids.put(uidCounter, Gen.touristInfo.get(i));
						}

						if(!touristInfo.isEmpty()) validLocs.add(touristInfo);
					}
					
				}


				Collections.reverse(validLocs); //reverse arraylist so order stays in tact
				//Make sure validLocs contains something
				if(!validLocs.isEmpty()){
					
					int v = validLocs.get(validLocs.size()-1).size() + 2; //number of vertices
					int edges = 0; //number of edges
					
					//calculate the number of edges/vertices
					for (int i = 0; i < validLocs.size()-1; i++){			
						edges += validLocs.get(i).size() * validLocs.get(i+1).size();
						v += validLocs.get(i).size();
					}
					
					edges += validLocs.get(0).size() + validLocs.get(validLocs.size()-1).size();
					
					
					DirectedEdge[] de = new DirectedEdge[edges]; //Array of Directed Edges
					int counter = 0;
						
					
					//Connect the homebase to all the valid locations (type is the first element in the preference q)
					for(int i = 0; i < validLocs.get(0).size(); i++)
						de[counter++] = new DirectedEdge(hbLoc.getUid(),validLocs.get(0).get(i).getUid(),hbLoc.distTo(validLocs.get(0).get(i)));
							
					//Connect all places to adjacent places (ie. if preference q is restaurant, museums, parks, it will connect all the restaurants to all the museums
					// and all the museums to all the parks.
					for(int i = 0; i < validLocs.size() - 1; i++)
						for(int j = 0; j < validLocs.get(i).size(); j++)
							for(int k = 0; k < validLocs.get(i+1).size(); k++)
								de[counter++] = new DirectedEdge(validLocs.get(i).get(j).getUid(),validLocs.get(i+1).get(k).getUid(),validLocs.get(i).get(j).distTo(validLocs.get(i+1).get(k)));	
			
					//Connnect all the valid locations (last location in the queue) back to the homebase location
					for(int i = 0; i < validLocs.get(validLocs.size()-1).size(); i++)
						de[counter++] = new DirectedEdge(validLocs.get(validLocs.size()-1).get(i).getUid(),hbCopy.getUid(),validLocs.get(validLocs.size()-1).get(i).distTo(hbLoc));

					//Create an edge weighted digraph
					EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(de,v,edges);	
					
					//Implement Dijkstra
					Dijkstra d = new Dijkstra(ewd,hbCopy.getUid());		
					
					
					z = d.pathTo(hbLoc.getUid());
					
					//Print out path
					for(DirectedEdge dee : z)
						System.out.println(uids.get(dee.from()).getName() + " -> " + uids.get(dee.to()).getName());
				}
				//display the google map with polylines connecting each of the locations
				MapViewOptions options = new MapViewOptions();
				options.importPlaces();

				final Mapper mapView = new Mapper(z, uids);
				mappage.add(mapView, BorderLayout.CENTER);
				mappage.setVisible(true);
			}
		});
		
		mf.setVisible(true);

	}

	public static void main(String args[]) {
		TourGuideUI tg = new TourGuideUI();
		tg.show();
	}
}
