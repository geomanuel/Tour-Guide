package tourguide;

import java.util.ArrayList;

/**
 * <h1>Heap Sort Implementation</h1> Heap.java implements heap sort..
 * 
 * @author Alexander Pattison
 * @version 1.0
 * @since 2017-03-28
 */

public class Heap {
	private static Location center;

	/**
	 * Heap sort.
	 * 
	 * @param x
	 *            input ArrayList, being sorted by distTo center
	 * @param cen
	 *            center location to relate distances to, from a location in an
	 *            ArrayList
	 */
	public static void sortHeap(ArrayList<Location> x, Location cen) {
		center = cen;
		int n = x.size();

		for (int k = n / 2; k >= 1; k--)
			sink(x, k, n);
		while (n > 1) {
			exch(x, 1, n--);
			sink(x, 1, n);
		}
	}

	/**
	 * Sink a node in heap.
	 * 
	 * @param x
	 *            input ArrayList, being sorted by distTo center
	 * @param k
	 *            the node to sink
	 */
	private static void sink(ArrayList<Location> x, int k, int n) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && less(x, j, j + 1))
				j++;
			if (!less(x, k, j))
				break;
			exch(x, k, j);
			k = j;
		}
	}

	/**
	 * Uses distTo Location class method to compare location distances to a
	 * center location.
	 * 
	 * @param x
	 *            input ArrayList, being sorted by distTo center
	 * @param i
	 *            index of first Location in an ArrayList x
	 * @param j
	 *            index of second Location in an ArrayList x
	 * @return boolean If Location at index i is closer to center than Location
	 *         at index j
	 * @see Location distTo method
	 */
	private static boolean less(ArrayList<Location> x, int i, int j) {
		double dist_i = x.get(i - 1).distTo(center);
		double dist_j = x.get(j - 1).distTo(center);
		return dist_i < dist_j;
		// return x[i-1].compareTo(x[j-1]) == -1;
	}

	/**
	 * Exchange two values in an ArrayList.
	 * 
	 * @param x
	 *            input ArrayList, being sorted by distTo center
	 * @param i
	 *            index of first Location in an ArrayList x
	 * @param j
	 *            index of second Location in an ArrayList x
	 */
	private static void exch(ArrayList<Location> x, int i, int j) {
		Location t = x.get(i - 1);
		x.set(i - 1, x.get(j - 1));
		x.set(j - 1, t);
	}

}
