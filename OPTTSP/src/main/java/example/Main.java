package example;

import constructiveheuristics.NearestNeighborHeuristic;
import localsearchheuristics.MDR;
import metaheuristics.ILS;

public class Main {

	public static void main(String[] args) {
		
		// Configuring the dataset and creating the array distance
		DataSet dataSet = new DataSet();
		dataSet.createMatrixDistance("instances//berlin52.tsp");
		dataSet.printMatrixDistance();
		
		
		// Configuring the build and local search heuristics. In this case, the Nearest Neighbor Construct and the Random Descent Method
		NearestNeighborHeuristic nearestNeighborHeuristic = new NearestNeighborHeuristic(dataSet);
		MDR mdr = new MDR(dataSet, 500);
		
		// Configuring Iterated Local Search metaheuristic
		ILS ils = new ILS(dataSet, nearestNeighborHeuristic, mdr, 5000);
		
		// Executing the metaheuristic
		ils.execute();
		
		// Printing the solution to the terminal and its value when applied to the objective function
		ils.printSolution();
	}

}
