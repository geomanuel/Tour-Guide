package tourguide;

/**
 * This class represents a directed edges
 * @author Andrew Deschenes
 * @Since April 12th, 2017
 *
 */
public class DirectedEdge {
	
	private int v1;
	private int v2;
	private double weight;
	
	/**
	 * Constructor for DirectedEdge
	 * @param v1 the first vertex
	 * @param v2 the second vertex
	 * @param weight the weight of the edge
	 */
	public DirectedEdge(int v1, int v2, double  weight){
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}
	
	/**
	 * Returns the first vertex (Vertex the directed edge is starting at)
	 * @return int representing the vertex (uid of a location)
	 */
	public int from(){
		return this.v1;
	}
	
	/**
	 * Returns the second vertex (Vertex the directed edge is pointing to)
	 * @return int representing the vertex (uid of a location)
	 */
	public int to(){
		return this.v2;
	}
	
	/**
	 * Returns the weight of the directed edge
	 * @return double representing the weight
	 */
	public double weight(){
		return this.weight;
	}
	
	/**
	 * A string representation of the directed edge
	 * @return String representing the directed edge
	 */
	public String toString(){
		return v1 + "->" + v2 + " " + weight;
	}

}
