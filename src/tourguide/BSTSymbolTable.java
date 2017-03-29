package tourguide;
import edu.princeton.cs.algs4.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * BinarySearchTree  Symbol Table
 * Takes a heap sorted ArrayList<Location> of locations and creates a binary search tree
 * Purpose is to find floor of an element (user inputted radius) in sorted ArrayList<Location> and creates a new ArrayList<Location> 
 * that stops at floor of element 
 * 
 * Binary Search Tree algorithm modified from algs4 Princeton website/TB
 * 
 * Key = index in ArrayList of location
 * Value = Location object distance from home base in km (distTo)
 * 
 * main method demonstrates implementation
 * 
 * 
 * @author Kathryn Kodama
 *
 */
public class BSTSymbolTable{
	
	private Node root;
	
	private class Node{
		private int key;
		private double val;
		private Node left, right; //linkstosub-trees 
		private int N; // # nodes in sub-tree rooted here
	
		public Node(int key, double val, int N){ 
			this.key = key; 
			this.val = val; 
			this.N = N; 
			} 
	}

	
	public int size() { 
		return size(root); 
	}
	
	private int size(Node x){
		if (x == null) return 0; 
		else return x.N;
	}
	
	public double get(int key) { 
		return get(root, key);
	}
	private double get(Node x, int key){
		// Return value associated with key in the subtree rooted at x; // return null if key not present in subtree rooted at x.
		if (x == null) 
			return (Double) null;
		//manually replacing compareTo function from comparable interface int cmp = key.compareTo(x.key);
		int cmp;
		if (key < x.key){
			cmp =  -1;
		}
		else if (key == x.key){
			cmp = 0;
		}
		else{
			cmp = 1;
		}
		
		if (cmp < 0) 
			return get(x.left, key);
		else if (cmp > 0) 
			return get(x.right, key);
		else 
			return x.val;
		}
	
	public void put(int key, double val){
		// Search for key. Update value if found; grow table if new.
	     root = put(root, key, val);
	}
	private Node put(Node x, int key, double val){
		// Change key’s value to val if key in subtree rooted at x.
		// Otherwise, add new node to subtree associating key with val. 
		if (x == null) 
			return new Node(key, val, 1);
		
		//manually replacing compareTo function from comparable interface int cmp = key.compareTo(x.key);
		int cmp;
		if (key < x.key){
			cmp =  -1;
		}
		else if (key == x.key){
			cmp = 0;
		}
		else{
			cmp = 1;
		}
			
		if (cmp < 0) 
			x.left = put(x.left, key, val);
		else if (cmp > 0) 
			x.right = put(x.right, key, val); 
		else 
			x.val = val;
		
		x.N = size(x.left) + size(x.right) + 1;
		return x; 
	}
	
	public int min(){
		return min(root).key;
	}
	
	private Node min(Node x){
		if (x.left == null)
			return x;
		return min(x.left);
	}
	
	/**
	 * 
	 * @param key
	 * @return - 1 if Node x doesn't exist
	 */
	public int floor(int key){
		Node x = floor(root, key);
		if (x == null)
			return -1;
		return x.key;
	}
	
	private Node floor(Node x, int key){
		if (x == null)
			return null;
	//manually replacing compareTo function from comparable interface int cmp = key.compareTo(x.key);
		int cmp;
		if (key < x.key){
			cmp =  -1;
		}
		else if (key == x.key){
			cmp = 0;
		}
		else{
			cmp = 1;
		}
		
		if (cmp == 0)
			return x;
		if (cmp < 0)
			return floor(x.left, key);
		Node t = floor(x.right, key);
		if (t != null)
			return t;
		else 
			return x;
	}
	
	public int select(int k){
		return select(root, k).key;
	}
	
	private Node select(Node x, int k){
		
		if (x == null)
			return null;
		int t = size(x.left);
		if(t > k)
			return select(x.left, k);
		else if (t < k)
			return select(x.right, k - t - 1);
		else 
			return x;
	}
	
	public int rank(int key){
		return rank(key, root);
	}
	
	private int rank(int key, Node x){
		if (x == null)
			return 0;
	//manually replacing compareTo function from comparable interface int cmp = key.compareTo(x.key);
		int cmp;
		if (key < x.key){
			cmp =  -1;
		}
		else if (key == x.key){
			cmp = 0;
		}
		else{
			cmp = 1;
		}
				
		if (cmp < 0)
			return rank(key, x.left);
		else if (cmp > 0)
			return 1 + size(x.left) + rank(key, x.right);
		else
			return size(x.left);
	}
	
	
    /**
     * 
     *
     * @param args the command-line arguments
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
    	
    	double MAX_RADIUS = 5;
    	
    	//initializes test array of locations and home base
   
    	Location hb = new Location("Home", 48, -58);
   
    	
    	//heap sorts all items in reference to the home base
    	//HeapSortCategory.All(hb);
    	//Initializes binary search tree
    	BSTSymbolTable bst = new BSTSymbolTable();
    

    	//inserts/puts all items in array airports to bst 
    	
    	System.out.println("ArrayList size= "+Gen.airports.size());
		for (int i = 0; i < Gen.airports.size(); i++) {
			double dist = Gen.airports.get(i).distTo(hb);
			bst.put(i, dist);
			System.out.println("("+Gen.airports.get(i).getLatitude() + ", " + Gen.airports.get(i).getLongitude() + "), d= "+dist);
		}
		
		//finds floor of max_radius
		System.out.println(bst.floor((int) MAX_RADIUS));
		ArrayList<Location> listNew = new ArrayList<Location>();
		//create new ArrayList and stops at floor index
		for (int j = 0; j < bst.floor((int) MAX_RADIUS); j++){
			listNew.add(Gen.airports.get(j));
		}
		
        
    }
}