package tourguide;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Alex Trudeau
 * @since April 11, 2017
 *
 */
public class GeoCodingTest {
	private GeoCoding gc1;
	private GeoCoding gc2;
	private GeoCoding gc3;
	@Before
	public void setUp() throws Exception {
		gc1 = new GeoCoding("1280 Main St W Hamilton");
		gc2 = new GeoCoding("301 Front St W Toronto");
		gc3 = new GeoCoding("King St West");
	}

	@After
	public void tearDown() throws Exception {
		gc1 = new GeoCoding("1280 Main St W Hamilton");
		gc2 = new GeoCoding("301 Front St W Toronto");
	}

	@Test
	public void testHomebase() {
		assertTrue(gc1.homebase().equals("1280 Main St W Hamilton"));
		assertTrue(gc2.homebase().equals("301 Front St W Toronto"));
	}

	@Test
	public void testFormattedAddress() {
		assertTrue(gc1.formattedAddress()[0].equals("1280 Main St W, Hamilton, ON L8S 4L8, Canada"));
		assertTrue(gc2.formattedAddress()[0].equals("301 Front St W, Toronto, ON M5V 2T6, Canada"));
	}

	@Test
	public void testLongitude() {
		assertEquals(-79.919318, gc1.longitude()[0], 0.003);
		assertEquals(-79.387383, gc2.longitude()[0], 0.003);
	}

	@Test
	public void testLatitude() {
		//delta value doubled bc latitude goes -90 deg to 90 deg, longitude goes -180 deg to 180 deg
		assertEquals(43.258770, gc1.latitude()[0], 0.006);
		assertEquals(43.642707, gc2.latitude()[0], 0.006);
	}
	
	@Test
	public void testResults() {
		assertTrue(gc1.results() == 1);
		assertTrue(gc2.results() == 1);
		assertTrue(gc3.results() == 2);
	}
}
