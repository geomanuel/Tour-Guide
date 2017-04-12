package tourguide;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

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
 * This class implements the code found in Client.java. See Client.java
 * for details on code implementation and structure for the "inner
 * workings" of this class.
 * 
 * @author Alex Trudeau, Andrew Deschenes
 * @since March 28, 2017
 */
public class TourGuideUI {
	private JButton chooseLocationBtn;
	private JButton enterLocationBtn;
	private JButton genericBtn;
	private JButton tourBtn;
	
	private JFrame mf; // main frame
	
	private JPanel homepage;
	private JPanel selectionpage;
	private JPanel locationSelect;
	private JPanel pages;
	private JPanel mappage;
	private JPanel optionPanel;
	private JPanel listPanel;
	
	private JLabel categoriesLabel;
	private JLabel locationBoxLabel;
	private JLabel locationSelectLabel;
	private JLabel title;
	
	private JTextField locationBox;

	private GridBagConstraints gbc;
	private CardLayout cardLayout;

	private Color titleColor = new Color(57, 156, 255);
	
	private Font largeText = new Font("Impact", Font.PLAIN, 74);
	private Font mediumText = new Font("Impact", Font.PLAIN, 34);
	private int counter = 3;

	private static String homeBase;
	private static String preferences = "";
	private static Client tour = new Client(homeBase, preferences);

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
		// generate a button for each of the categories
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
						// get the information associated with the button that
						// was clicked and add it to the queue
						tour.locationQ.enqueue(((AbstractButton) e.getSource()).getText());
						preferences += ((AbstractButton) e.getSource()).getText() + ", ";
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
				tour.gc = new GeoCoding(homeBase);
				// dynamically generate buttons for multiple returned addresses
				for (int i = 0; i < tour.gc.formattedAddress().length; i++) {
					chooseLocationBtn = new JButton(tour.gc.formattedAddress()[i] + "; " + tour.gc.latitude()[i] + "; "
							+ tour.gc.longitude()[i]);
					chooseLocationBtn.setFont(mediumText);
					chooseLocationBtn.setBackground(titleColor);
					chooseLocationBtn.setForeground(Color.WHITE);
					chooseLocationBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
					chooseLocationBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// get the button information and add it to the
							// homebase variables in Client.java
							String[] fullAddress = ((AbstractButton) e.getSource()).getText().split("; ");
							tour.hbAddress = fullAddress[0];
							tour.hbLong = Double.parseDouble(fullAddress[2]);
							tour.hbLat = Double.parseDouble(fullAddress[1]);

							String tempLong = String.format("%.6f", tour.hbLong);
							String tempLat = String.format("%.6f", tour.hbLat);

							tour.hbLong = Double.parseDouble(tempLong);
							tour.hbLat = Double.parseDouble(tempLat);

							tour.hbLoc = new Location(tour.hbAddress, tour.hbLat, tour.hbLong);
							tour.hbCopy = new Location(tour.hbAddress, tour.hbLat, tour.hbLong);
							System.out.println(tour.hbAddress + "; " + tour.hbLong + "; " + tour.hbLat);
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
				// generate valid locations within allowed MAX_RADIUS radius
				ArrayList<ArrayList<Location>> validLocs = new ArrayList<ArrayList<Location>>();
				try {
					validLocs = tour.findValidLocations(tour.locationQ);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// find the shortest path between points
				Iterable<DirectedEdge> path = tour.pathFinder(validLocs);
				// display the google map with polylines connecting each of the
				// locations
				MapViewOptions options = new MapViewOptions();
				options.importPlaces();
				@SuppressWarnings("static-access")
				final Mapper mapView = new Mapper(path, tour.uids);
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
