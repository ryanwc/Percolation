

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Performs experiments involving Percolation objects.
 * API for repeating trials of iteratively opening sites until the
 * system percolates.
 * 
 * @author ryanwilliamconnor
 *
 */
public class PercolationStats {

	private int size;
	private int trials;
	private double mean, stdDev, confidenceLo, confidenceHi;
	private double[] percPointPercents;
	
	public static void main(String[] args) {
		// perform trials independent experiments on an n-by-n grid
		int n = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		Percolation system;
		PercolationStats stats = new PercolationStats(n, T);;
		int currOpenSites;
		
		for (int i = 0; i < stats.trials; i++) {
			system = new Percolation(stats.size);
			currOpenSites = system.numberOfOpenSites();
			// while there are blocked sites
			System.out.println("system percolates is " + system.percolates());
			while (!system.percolates() && 
					system.numberOfOpenSites() < n*n) {
				// open a site
				while (currOpenSites == system.numberOfOpenSites()) {
					system.open(StdRandom.uniform(1, n+1), 
							StdRandom.uniform(1, n+1));
				}
				currOpenSites = system.numberOfOpenSites();
			}
			stats.setPercPointPercent(i, system.numberOfOpenSites() / 
					((double)n*n));
		}
		// print stats
		stats.setMean();
		stats.setStdDev();
		stats.setConfidenceLo();
		stats.setConfidenceHi();
		StdOut.printf("mean: ");
		StdOut.println(stats.mean());
		StdOut.printf("stddev: ");
		StdOut.println(stats.stddev());
		StdOut.printf("95%% confidence interval: [");
		StdOut.print(stats.confidenceLo());
		StdOut.printf(", ");
		StdOut.print(stats.confidenceHi());
		StdOut.printf("]");
	}
	
	public PercolationStats(int n, int trials) {
		this.size = n;
		this.trials = trials;
		this.percPointPercents = new double[trials];
	}
	
	private void setPercPointPercent(int trialIndex, double percPercent) {
		percPointPercents[trialIndex] = percPercent;
	}
	
	private void setMean() {
		mean = StdStats.mean(percPointPercents);
	}
	
	public double mean() {
		return mean;
	}
	
	private void setStdDev() {
		stdDev = StdStats.stddev(percPointPercents);
	}
	
	public double stddev() {
		// sample standard deviation of percolation threshold
		return stdDev;
	}
	
	private void setConfidenceLo() {
		// low  endpoint of 95% confidence interval
		confidenceLo = mean - (1.96*stdDev) / Math.sqrt(trials);
	}
	
	public double confidenceLo() {
		return confidenceLo;
	}
	
	private void setConfidenceHi() {
		// high endpoint of 95% confidence interval
		confidenceHi = mean + (1.96*stdDev) / Math.sqrt(trials);
	}
	
	public double confidenceHi() {
		return confidenceHi;
	}
}