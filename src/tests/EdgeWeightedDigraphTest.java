package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tourguide.*;

/**
 * This class tests all the methods in the EdgeWeightedDigraph class
 * @author Andrew Deschenes
 * @since April 12th, 2017
 *
 */
public class EdgeWeightedDigraphTest {
	
	private ArrayList<Location> l;
	private ArrayList<Location> l1;
	private DirectedEdge[] de;
	private EdgeWeightedDigraph e1;

	@Before
	public void setUp() throws Exception {
		
		l = new ArrayList<Location>();
		l1 = new ArrayList<Location>();
		de = new DirectedEdge[8];
		
		Location hb = new Location("Home", 0, 0);
		hb.setUid(0);
		
		int uidCounter = 0;
		int uidCounter1 = 2;
		
		for(int i = 0 ; i < 2; i++){
			l.add(new Location("Test" + i, i+1, i+1));
			l1.add(new Location("Test" + i, (i+1)*2, (i+1)*2));
			l.get(i).setUid(++uidCounter);
			l1.get(i).setUid(++uidCounter1);
			de[i] = new DirectedEdge(0,l.get(i).getUid(), hb.distTo(l.get(i)));
			de[i+6] = new DirectedEdge(l1.get(i).getUid(), 0, hb.distTo(l1.get(i)));
		}
		
		de[2] = new DirectedEdge(l.get(0).getUid(), l1.get(0).getUid(), l.get(0).distTo(l1.get(0)));
		de[3] = new DirectedEdge(l.get(0).getUid(), l1.get(1).getUid(), l.get(0).distTo(l1.get(1)));
		de[4] = new DirectedEdge(l.get(1).getUid(), l1.get(0).getUid(), l.get(1).distTo(l1.get(0)));
		de[5] = new DirectedEdge(l.get(1).getUid(), l1.get(1).getUid(), l.get(1).distTo(l1.get(1)));
		
		e1 = new EdgeWeightedDigraph(de,5,de.length);		

	}
	


	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests the EdgeWeightedDigraph constructor when only an integer (representing the amount of vertices is passed)
	 */
	@Test
	public void testEdgeWeightedDigraph() {
		
		EdgeWeightedDigraph e = new EdgeWeightedDigraph(10);
		EdgeWeightedDigraph e1 = new EdgeWeightedDigraph(0);		
		
		assertTrue(e.V() == 10);
		assertTrue(e.E() == 0);
		
		assertTrue(e1.V() == 0);
		assertTrue(e1.E() == 0);
		
		try{
			
			EdgeWeightedDigraph e2 = new EdgeWeightedDigraph(-10); 
			
		}catch(IllegalArgumentException err){
			assertTrue(err.getMessage().equals("Number of vertices in a Digraph must be nonnegative"));
		}
		
	}
	
	/**
	 * Tests the EdgeWeightedDigraph constructor when a list of DirectedEdges, vertex count and edge count are passed
	 */
	@Test
	public void testEdgeWeightedDigraphParameters() {
		
		DirectedEdge[] d1 = new DirectedEdge[0];
				
		EdgeWeightedDigraph e2 = new EdgeWeightedDigraph(d1,0,0);		
		
		assertTrue(e1.V() == 5);
		assertTrue(e1.E() == 8);
		
		assertTrue(e1.toString().split("\n")[0].trim().equals("5 8"));	
		assertTrue(e1.toString().split("\n")[1].trim().equals("0: 0->2 314.4748051008686  0->1 157.24938127194397"));		
		assertTrue(e1.toString().split("\n")[2].trim().equals("1: 1->4 471.5085530271331  1->3 157.22543203807288"));		
		assertTrue(e1.toString().split("\n")[3].trim().equals("2: 2->4 314.2832550736839  2->3 0.0"));		
		assertTrue(e1.toString().split("\n")[4].trim().equals("3: 3->0 314.4748051008686"));		
		assertTrue(e1.toString().split("\n")[5].trim().equals("4: 4->0 628.7577973618929"));
		
		assertTrue(e2.V() == 0);
		assertTrue(e2.E() == 0);
		assertTrue(e2.toString().split("\n")[0].trim().equals("0 0"));	
		
		//Shouldnt be a next line since theres no vertices and no edges
		try{
			e2.toString().split("\n")[1].trim();
		}catch(ArrayIndexOutOfBoundsException err){
			assertTrue(err.getMessage().equals("1"));
		}
	
	}
	
	/**
	 * Tests the V method
	 */
	@Test
	public void testV() {
		
		EdgeWeightedDigraph e2 = new EdgeWeightedDigraph(0);
		
		assertTrue(e1.V() == 5);
		assertTrue(e2.V() == 0);

	}
	
	/**
	 * Tests the E method
	 */
	@Test
	public void testE() {
		
		EdgeWeightedDigraph e2 = new EdgeWeightedDigraph(0);
		
		assertTrue(e1.E() == 8);
		assertTrue(e2.E() == 0);

	}
	
	/**
	 * Tests the addEdge method
	 */
	@Test
	public void testAddEdge() {
		
		DirectedEdge d = new DirectedEdge(1,0,100.0);
		DirectedEdge d1 = new DirectedEdge(-1,-1,-10.0);
		
		assertTrue(e1.E() == 8);
		e1.addEdge(d);		
		assertTrue(e1.E() == 9);
		
		//shouldnt be able to add illegal edge
		try{
			e1.addEdge(d1);
		}catch(IllegalArgumentException err){
			assertTrue(err.getMessage().equals("vertex -1 is not between 0 and 4"));
		}
		
		assertTrue(e1.E() == 9); //make sure no edge got added

	}
	
	/**
	 * Tests the adj() method
	 */
	@Test
	public void TestAdj() {
		
		EdgeWeightedDigraph e2 = new EdgeWeightedDigraph(0);
		
		for(DirectedEdge i : e1.adj(0))
			assertTrue(i.from() == 0 && (i.to() == 1 || i.to() == 2));
		
		
		//Nothing is adjacent to 0 (since there is no vertex 0)
		try{
			e2.adj(0);
		}catch(IllegalArgumentException err){
			assertTrue(err.getMessage().equals("vertex 0 is not between 0 and -1"));
		}
	}
	
	/**
	 * Tests the indegree() method
	 */
	@Test
	public void TestIndegree() {
		
		assertTrue(e1.indegree(0) == 2);
		assertTrue(e1.indegree(1) == 1);
		assertTrue(e1.indegree(2) == 1);
		assertTrue(e1.indegree(3) == 2);
		assertTrue(e1.indegree(4) == 2);
	}
	
	/**
	 * Tests the outdegree() method
	 */
	@Test
	public void TestOutdegree() {
		
		assertTrue(e1.outdegree(0) == 2);
		assertTrue(e1.outdegree(1) == 2);
		assertTrue(e1.outdegree(2) == 2);
		assertTrue(e1.outdegree(3) == 1);
		assertTrue(e1.outdegree(4) == 1);
	}
	
	/**
	 * Tests the edges() method
	 */
	@Test
	public void TestEdges() {
		
		EdgeWeightedDigraph e2 = new EdgeWeightedDigraph(2);
		DirectedEdge d1 = new DirectedEdge(0,1,10.9);
		e2.addEdge(d1);
		
		for(DirectedEdge d : e1.edges())
			assertTrue((d.from() == 0 && (d.to() == 1 || d.to() == 2)) || (d.from() == 1 && (d.to() == 3 || d.to() == 4)) 
					|| (d.from() == 2 && (d.to() == 3 || d.to() == 4)) || (d.from() == 3 && d.to() == 0) || (d.from() == 4 && d.to() == 0));
		
		
		for(DirectedEdge d : e2.edges())
			assertTrue(d.to() == 1 && d.from() == 0);

		
	}
	
	/**
	 * Tests the toString() method
	 */
	@Test
	public void TestToString() {
		
		EdgeWeightedDigraph e2 = new EdgeWeightedDigraph(0);

		assertTrue(e1.toString().split("\n")[0].trim().equals("5 8"));	
		assertTrue(e1.toString().split("\n")[1].trim().equals("0: 0->2 314.4748051008686  0->1 157.24938127194397"));		
		assertTrue(e1.toString().split("\n")[2].trim().equals("1: 1->4 471.5085530271331  1->3 157.22543203807288"));		
		assertTrue(e1.toString().split("\n")[3].trim().equals("2: 2->4 314.2832550736839  2->3 0.0"));		
		assertTrue(e1.toString().split("\n")[4].trim().equals("3: 3->0 314.4748051008686"));		
		assertTrue(e1.toString().split("\n")[5].trim().equals("4: 4->0 628.7577973618929"));
		
		assertTrue(e2.toString().split("\n")[0].trim().equals("0 0"));
		
		//Shouldnt be a next line since theres no vertices and no edges
		try{
			e2.toString().split("\n")[1].trim();
		}catch(ArrayIndexOutOfBoundsException err){
			assertTrue(err.getMessage().equals("1"));
		}

		
	}

}
