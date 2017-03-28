package tourguide;

import java.util.ArrayList;


/**
 * BinarySearchTree 
 * 
 * Binary Search Tree algorithm modified from algs4 Princeton website/TB
 * 
 * Key = index in ArrayList of location
 * Value = Location object distance from home base in km (distTo)
 * 
 * @author Kathryn Kodama
 *
 */
public class BST<Key extends Comparable<Key>, Value>{
	
	private Node root;
	
	private class Node{
		private Key key;
		private Value val;
		private Node left, right; //linkstosub-trees 
		private int N; // # nodes in sub-tree rooted here
	
		public Node(Key key, Value val, int N){ 
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
	
	public Value get(Key key) { 
		return get(root, key);
	}
	private Value get(Node x, Key key){
		// Return value associated with key in the subtree rooted at x; // return null if key not present in subtree rooted at x.
		if (x == null) 
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) 
			return get(x.left, key);
		else if (cmp > 0) 
			return get(x.right, key);
		else 
			return x.val;
		}
	
	public void put(Key key, Value val){
		// Search for key. Update value if found; grow table if new.
	     root = put(root, key, val);
	}
	private Node put(Node x, Key key, Value val){
		// Change key’s value to val if key in subtree rooted at x.
		// Otherwise, add new node to subtree associating key with val. 
		if (x == null) 
			return new Node(key, val, 1);
		
		int cmp = key.compareTo(x.key);
		
		if (cmp < 0) 
			x.left = put(x.left, key, val);
		else if (cmp > 0) 
			x.right = put(x.right, key, val); 
		else 
			x.val = val;
		
		x.N = size(x.left) + size(x.right) + 1;
		return x; 
	}
	
	public Key min(){
		return min(root).key;
	}
	
	private Node min(Node x){
		if (x.left == null)
			return x;
		return min(x.left);
	}
	
	public Key floor(Key key){
		Node x = floor(root, key);
		if (x == null)
			return null;
		return x.key;
	}
	
	private Node floor(Node x, Key key){
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
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
	
	public Key select(int k){
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
	
	public int rank(Key key){
		return rank(key, root);
	}
	
	private int rank(Key key, Node x){
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return rank(key, a.left);
		else if (cmp > 0)
			return 1 + size(x.left) + rank(key, x.right);
		else
			return size(x.left);
	}
	
	
    /**
     * 
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
    	
    	ArrayList<Location> test = new ArrayList<Location>();
    	Location hb = new Location("Home", 1, 1);
    	Location l1 = new Location("McD", 1, 3);
    	Location l2 = new Location("Wendys", 1, 5);
    	Location l3 = new Location("Tims", 1, 6);
    	test.add(l1);
    	test.add(l2);
    	test.add(l3);
    	
    	System.out.println(hb.distTo(l1));
        
    }
}