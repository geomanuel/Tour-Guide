package tourguide;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Alex Trudeau
 * @since April 11, 2017
 */
public class DirectedEdgeTest {
	private DirectedEdge e1;
	private DirectedEdge e2;
	@Before
	public void setUp() throws Exception {
		e1 = new DirectedEdge(1, 2, 1.5);
		e2 = new DirectedEdge(4, 99, 50.111);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFrom() {
		assertTrue(e1.from() == 1);
		assertTrue(e2.from() == 4);
	}

	@Test
	public void testTo() {
		assertTrue(e1.to() == 2);
		assertTrue(e2.to() == 99);
	}

	@Test
	public void testWeight() {
		assertTrue(e1.weight() == 1.5);
		assertTrue(e2.weight() == 50.111);
	}

	@Test
	public void testToString() {
		assertTrue(e1.toString().equals("1->2 1.5"));
		assertTrue(e2.toString().equals("4->99 50.111"));
	}

}
