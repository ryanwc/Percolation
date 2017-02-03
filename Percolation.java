

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * A Percolation object. Holds state and method for exploring percolation 
 * problems, like "can the water from the top of this system reach the 
 * bottom?" and "will this material conduct electricity?"
 * 
 * By convention, the row and column indices of the system are integers 
 * between 1 and n, where (1, 1) is the upper-left site.
 * 
 * Within the grid (a 2D int array), '0' means 'blocked', '1' means 'open'.
 * 
 * @author ryanwilliamconnor
 *
 */
public class Percolation {

	private int[][] grid;
	private int numOpenSites, virtualTopID, virtualBottomID;
	private WeightedQuickUnionUF unionFind;
	private boolean percolates;
	
	/**
	 * Create n by n grid with all sites blocked and a corresponding union-find.
	 * Runs in O(n^2) time.
	 * 
	 * @param n: the width/height of the percolation system 
	 * 	in terms of sites
	 */
	public Percolation(int n) {
		
		if (n <= 0) throw new IllegalArgumentException("Size must be greater than 0");
		
		numOpenSites = 0;
		grid = new int[n][n];
		unionFind = new WeightedQuickUnionUF(n*n + 2); // to hold virtual top and bottom site
		virtualTopID = n*n;
		virtualBottomID = n*n + 1;
		percolates = false;
	}
	
	/**
	 * Make a site open if it is not open already.
	 * 
	 * @param row: the row num of the site (indexed at 1)
	 * @param col: the col num of the site (indexed at 1)
	 */
	public void open(int row, int col) {

		if (indexOutOfBounds(row) || indexOutOfBounds(col))
			throw new IndexOutOfBoundsException("Row and col must be in [1,size]");
		
		if (grid[row-1][col-1] != 0) {
			return;
		}
		
		// open it
		grid[row-1][col-1] = 1;
		numOpenSites++;
		
		// connect to top virtual site if necessary
		if (row == 1) 
			unionFind.union(getSiteID(row, col), virtualTopID);
		
		// connect it to any open neighbors
		if (row != 1 && isOpen(row-1, col)) 
			unionFind.union(getSiteID(row, col), getSiteID(row-1, col));
		
		if (row != grid.length && isOpen(row+1, col)) {
			unionFind.union(getSiteID(row, col), getSiteID(row+1, col));
		}
		
		if (col != 1 && isOpen(row, col-1))
			unionFind.union(getSiteID(row, col), getSiteID(row, col-1));
		
		if (col != grid.length && isOpen(row, col+1))
			unionFind.union(getSiteID(row, col), getSiteID(row, col+1));
		
		// if this one is full and connected to a bottom site, enable percolation
		if (!percolates && isFull(row, col)) {
			for (int i = 1; i <= grid.length; i++) {
				if (unionFind.connected(getSiteID(grid.length, i), virtualTopID)) {
					unionFind.union(getSiteID(grid.length, i), virtualBottomID);	
					break;
				}		
			}
		}
		
		if (!percolates && percolates())
			percolates = true;
	}
	
	private int getSiteID(int row, int col) {
		return ((row-1)*grid.length + col)-1;
	}

	/**
	 * Check if a site is open.
	 * 
	 * @param row: the row num of the site (indexed at 1)
	 * @param col: the col num of the site (indexed at 1)
	 */
	public boolean isOpen(int row, int col) {
		
		if (indexOutOfBounds(row) || indexOutOfBounds(col))
			throw new IndexOutOfBoundsException("Row and col must be in [1,size]");
		
		return grid[row-1][col-1] == 1;	
	} 
	
	/**
	 * Check if a site is full (i.e. is filled with water, electricity, etc).
	 * 
	 * @param row: the row num of the site (indexed at 1)
	 * @param col: the col num of the site (indexed at 1)
	 */
	public boolean isFull(int row, int col) {
		
		if (indexOutOfBounds(row) || indexOutOfBounds(col))
				throw new IndexOutOfBoundsException("Row and col must be in [1,size]");

		return unionFind.connected(virtualTopID, getSiteID(row, col));
	} 
	
	public int numberOfOpenSites() {
		return numOpenSites;
	} 
	
	/**
	 * Check whether any open site in the top row is connected 
	 * via open sites to any open site in the bottom row.
	 * Note a connection is only left-right-top-bottom, not diagonal.
	 * @return true if the above condition holds, false otherwise
	 */
	public boolean percolates() {
		return unionFind.connected(virtualTopID, virtualBottomID);
	}
	
	private boolean indexOutOfBounds(int index) {
		return index < 1 || index > grid.length;
	}
}
