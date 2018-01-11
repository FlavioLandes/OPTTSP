/* Base class for implementation of reading the dataset and creating the array distance.
 * 
 */


package bases;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class BaseDataSet {
	protected double[][] matrixDistance;// Array distance from dataset
	protected int totalCity;// Total number of cities


	public BaseDataSet() {
	}
	
	// Creates array distance according to a set of cities
	public abstract void createMatrixDistance(String fileName);
		// To implement...
		// Eg: Take the coordinates of the cities and calculate the Euclidean distance
	
	
	// Calculates the objective function of the traveling salesman problem. That is, it calculates the total distance traveled
	public double calculateFx(ArrayList<Integer> solution) {
		double distanceTotal = 0;
		
		distanceTotal = this.matrixDistance[0][solution.get(0)];// Take the distance from the city of origin to the second city
		
		for (int i = 0; i < solution.size() - 1; i++) {
			distanceTotal = distanceTotal + matrixDistance[solution.get(i)][solution.get(i + 1)];
		}
		
		distanceTotal = distanceTotal + matrixDistance[solution.get(solution.size() - 1)][0]; // Take the distance from the last city to the origin (first)
		
		return distanceTotal;
	}
	
	// Returns the distance from city x to city y
	public double getDistance(int x, int y) {
		return this.matrixDistance[x][y];
	}
	
	// Prints the Distance matrix
	public void printMatrixDistance() {
		for (int i = 0; i < this.totalCity; i++) {
			System.out.println(Arrays.toString(this.matrixDistance[i]));
		}
	}
	
	public int getTotalCity() {
		return totalCity;
	}
	

}
