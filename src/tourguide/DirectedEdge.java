package tourguide;

public class DirectedEdge {
	
	private int v1;
	private int v2;
	private double weight;
	
	public DirectedEdge(int v1, int v2, double  weight){
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}
	
	public int from(){
		return this.v1;
	}
	
	public int to(){
		return this.v2;
	}
	
	public double weight(){
		return this.weight;
	}
	
	public String toString(){
		return v1 + "->" + v2 + " " + weight;
	}

}
