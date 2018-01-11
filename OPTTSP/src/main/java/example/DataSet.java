/* Example of implementation of the DataSet class.
 * In this example a distance matrix based on Euclidean distances is being created.
 */
package example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.math3.ml.distance.EuclideanDistance;

import bases.BaseDataSet;

public class DataSet extends BaseDataSet {
	
	public DataSet() {	
	}
	

	@Override
	public void createMatrixDistance(String fileName) {
		int aux;
		double x, y;// They store the x and y coordinates of each city
		Map<Integer, double[]> cities = new HashMap<>();
		EuclideanDistance euclideanDistance = new EuclideanDistance();
		
		
		
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(new File(fileName));
		
			this.totalCity = fileScanner.nextInt();
		
			// Reading file containing city coordinates
			for (int i = 0; i < this.totalCity; i++) {
				aux = fileScanner.nextInt();// First element and discarded, because only the city number
				x = fileScanner.nextDouble(); 
				y = fileScanner.nextDouble();
				cities.put(i, new double[] {x, y});
			}	
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);// Exit the program
		}
		
		
		// Creating the Distance Matrix
		this.matrixDistance = new double[this.totalCity][this.totalCity];
		for (int i = 0; i < this.totalCity; i++) {
			for (int j = 0; j < this.totalCity; j++) {
				matrixDistance[i][j] = euclideanDistance.compute(cities.get(i), cities.get(j));
			}
		}
	}

}
