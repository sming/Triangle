package org.psk.yodle;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsibility: find the max top-to-bottom total in the triangle data structure (a 2D array
 * of Node's).
 * Implements a dynamic programming approach. For each Node visited, the top to bottom total of
 * each of its parents (previous line, indexes col-1, col, col+1) is retrieved and the max
 * found. Then its maxTopToBotTotal is simply maxTotalParents + val. 
 * This means that previous "route" or "path" calculations down the triangle are not 
 * repeated.  
 * @author Pete
 */
public class TriangleSolver {
	private List<List<Node>> triangle;	// data structure representing the file
	private long triangleMax = 0L;		// overall max found so far

	TriangleSolver() {
		TriangleReader rd = new TriangleReader("triangle.txt");
		triangle = rd.getNumbers();
	}

	public static void main(String[] args) {
		TriangleSolver ts = new TriangleSolver();
		//dump(ts.triangle);	// handy for debugging TriangleReader
		long max = ts.solve();

		System.out.println("\nThe max top-bottom total is "+max);
	}
	
	/**
	 * for a given Node, get its parent Nodes (previous row, col-1, col, col+1)
	 * @param node node who's parents to get
	 * @return List of parents
	 */
	List<Node> getParents(Node node) {
		List<Node> ret = new ArrayList<Node>();
		tryAdd(ret, node, -1);
		tryAdd(ret, node, 0);
		
		return ret;
	}

	/**
	 * attempt to retrieve the parent at idx. This will fail if col+idx is -1 or past the
	 * end index of the previous row. 
	 * @param parents list of parents to populate
	 * @param node node who's parents to get
	 * @param idx -1, 0 or +1 depending on which parent we are getting
	 */
	private void tryAdd(List<Node> parents, Node node, int idx) {
		try {
			parents.add(triangle.get(node.row-1).get(node.col+idx));
		} catch (IndexOutOfBoundsException e) {
			// this is expected. Not good form since exceptions should be generated in 
			// exceptional circumstances (which this is not) but allows us to avoid a lot 
			// of tricky logic. It also wouldn't scale well since exception handling is slower
			// than regular function calls.
		}
	}

	/**
	 * basically need to determine max for every Node: this.max = max(p1,p2,p3)
	 * Base Case: max = val.
	 * 
	 * @return max top-to-bottom route
	 */
	private long solve() {
		for (List<Node> row : triangle) {
			for (Node node : row) {
				if (node.maxTopToBotTotal == 0) {
					List<Node> parents = getParents(node);
					for (Node parent : parents) {
						if (parent.maxTopToBotTotal > node.maxTopToBotTotal)
							node.maxTopToBotTotal = parent.maxTopToBotTotal;
					}
					
					node.maxTopToBotTotal += node.val;
					
					if (node.maxTopToBotTotal > triangleMax)
						triangleMax = node.maxTopToBotTotal;
				}
			}
		}

		return triangleMax;
	}

	/**
	 * print the triangle to std out (console, ususally)
	 * @param triangle
	 */
	public static void dump(List<List<Node>> nums) {
		int rowIdx = 0;
		for (List<Node> row : nums) {
			System.out.print("Row #"+(rowIdx++) + " : ");
			for (Node nd : row) {
				System.out.print(nd + " ");
			}
			System.out.println("");
		}
	}
}
