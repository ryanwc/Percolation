# Percolation

This repository provides classes and methods for exploring percolation problems. Percolation problems include determining whether water can flow from one side of a system to another and whether a system conducts electricity.

In this program, a percolation problem is represented by a square grid of "sites" that can be closed or open. An open site is "full" if there is a path of open sites from the top of the grid to that site. A system "percolates" if it has a "full" site on the bottom row.

The goal of this program is to help visualize percolation problems and understand how many open sites are needed, on average, for a system to percolate.

# Using this Repository

1. Download all of the files in this repo into the same directory.
2. Download Princeton's Java library [algs4.jar](http://algs4.cs.princeton.edu/code/algs4.jar) (warning: clicking the link initiates a download) and add it to the exuction path of the files from this repo. This algs4.jar contains about 200 Java programs that implement common, useful algorithms and data types that are not part of the standard Java library, such as the union-find data type and the Dijkstra shortest path algorithm).
3. Download test data from this link: [test data](http://coursera.cs.princeton.edu/algs4/testing/percolation-testing.zip) (warning: clicking the link initiates a download).
4. Explore percolation scenarios one of three ways:
	1. PercolationStats.java:
		- Run PercolationStats.java with two command line arguments: size and numTrials. Size specifies the dimensions of the square grid, giving size*size sites, while trials provides the number of trials to run.
		- The program will do the following, numTrials trials of times on a grid with size*size dimensions: open random sites on the grid until the grid percolates.
		- The output is the sample mean and standard deviation of the number of sites opened before the grid percolates, along with the 95% confidence interval for the population mean.
	2. PercolationVisualizer.java:
		- Run PercolationVisualizer.java with one comand line argument: the path to a text file. The text file specifies grid size on the first line and a sequence of (1-index-based) sites to open.
		- The visualization runs through the sequence of opening sites, with black sites representing closed sites, white sites representing open but not "full" sites, and blue sites representing open and "full" sites. The visualization also shows how many sites are open and whether the grid percolates.
	3. InteractivePercolationVisualizer.java:
		- Run InteractivePercolationVisualizer.java with zero or one command lind arguments: size. If size is provided, the visualizer will create a grid with the specified size. If no size is provided, the visualizer will create a grid with size 10 (100 sites).
		- Once the program is active, the user can click on black sites, which represent closed sites, to open them. White sites (open sites) will turn blue to indicate they are full. The visualization shows how many sites are open and whether the system percolates. 

# License

Copyright (C) 2017 Ryan William Connor.

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.