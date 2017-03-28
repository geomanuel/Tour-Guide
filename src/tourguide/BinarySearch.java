package tourguide;

import java.util.ArrayList;


/**
 * BinarySearch
 * 
 * Binary Search algorithm modified from algs4 Princeton website/TB
 * 
 * @author Kathryn Kodama
 *
 */
public class BinarySearch {

    /**
     * This class should not be instantiated.
     */
    private BinarySearch() { }

    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param  a the array of integers, must be sorted in ascending order
     * @param  key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    /**
     * Returns the index of the specified key in the specified array.
     * This function is poorly named because it does not give the <em>rank</em>
     * if the array has duplicate keys or if the key is not in the array.
     *
     * @param  key the search key
     * @param  a the array of integers, must be sorted in ascending order
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     * @deprecated Replaced by {@link #indexOf(int[], int)}.
     */
    @Deprecated
    public static int rank(int key, int[] a) {
        return indexOf(a, key);
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