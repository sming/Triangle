package org.psk.yodle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Responsibility: reading the triangle data into a "jagged array" (i.e. an array of arrays
 * of varying length).
 * @author Pete
 */
public class TriangleReader {
	private String fileName;
	private List<List<Node>> allNums = new ArrayList<List<Node>>();
	int row = 0;
	int col = 0;

	public TriangleReader(String fileName) {
		this.fileName = fileName; 
	}
	
	/**
	 * @return
	 */
	List<List<Node>> getNumbers() {
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		Scanner lineScanner = new Scanner(fr);

		while (lineScanner.hasNextLine())
			processLine(lineScanner.nextLine());

		
		return allNums;
	}

	/**
	 * Split the line by whitespace, parse each field to an int. If this is row #0, initialize
	 * its maxTopToBotTotal field since it's the base-case.
	 * @param line line of text from file to process
	 */
	private void processLine(String line) {
		
		List<String> fields = Arrays.asList(line.split(" "));
		List<Node> nums = new ArrayList<Node>();

		col = 0;
		try {
			for (String field : fields) {
				Node newNd = new Node(col++, row, Integer.parseInt(field));
				if (row == 0)
					newNd.maxTopToBotTotal = newNd.val;
				
				nums.add(newNd);
			}			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}

		allNums.add(nums);
		
		++row;
	}
}
