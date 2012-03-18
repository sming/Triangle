package org.psk.yodle;

/**
 * Responsibility: represents a number in the triangle. Includes a static "minimum" Node
 * used to represent "this Node has the lowest max top-to-bottom". It basically allows us to
 * avoid null-handling logic when processing a Node's parents. Like the "null object" pattern. 
 * @author Pete
 */
public class Node {
	private static Node min;	// helps us avoid null-handling
	

	int val = 0;	// integer value that this Node encapsulates
	long maxTopToBotTotal = 0;	// largest "route" down the triangle so far 

	int row = 0;
	int col = 0;

	/**
	 * @param col column index 0-99
	 * @param row row index 0-99
	 * @param inVal number's value
	 */
	Node(int col, int row, int inVal) {
		this.col = col;
		this.row = row;
		val = inVal;
	}
	
	/**
	 * @return the single "smallest top-to-bottom max" object
	 */
	static Node getMin() {
		if (min == null) {
			min = new Node(0, 0, -1);
			min.maxTopToBotTotal = -1;
		}
		return min;
	}
	
	@Override
	public String toString() {
		return Integer.toString(val);
	}
}
