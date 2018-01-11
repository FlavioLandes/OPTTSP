/* Basic model for implementation of heuristics (constructive and local search) and meta-heuristics
 * The solution should not contain the city of origin. It is added and calculated in the fx of the dataSet
 */

package bases;

import java.util.ArrayList;

public abstract class BaseHeuristics {
	public BaseDataSet dataSet;
	private ArrayList<Integer> solution;
	private double valueFx;
	
	
	public BaseHeuristics(BaseDataSet dataSet) {
		this.dataSet = dataSet;
		this.valueFx = Double.MAX_VALUE;// For the TSP, because it is a minimization problem 
	}
	
	// Execute the heuristic
	public abstract void execute();
	


	public ArrayList<Integer> getSolution() {
		return solution;
	}

	// Configure the solution. The solution should not contain the city of origin because it is added and calculated in the fx of the dataSet
	public void setSolution(ArrayList<Integer> solution) {
		if (solution.size() == this.dataSet.getTotalCity() - 1) {
			this.solution = solution;
		} else {
			System.out.println("Solution != Number Total City - 1! Tip: the solution should not contain the city of origin.");
			System.exit(1);
		}
	}


	public double getValueFx() {
		return valueFx;
	}


	public void setValueFx(double valueFx) {
		if (valueFx >= 0) {
			this.valueFx = valueFx;
		} else {
			System.out.println("ValueFx is < 0");
			System.exit(1);
		}
	}
	
	
}
