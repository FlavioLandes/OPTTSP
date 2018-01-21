/* Implementation of the metaheuristic GRASP (Greedy Randomized Adaptive Search Procedures) of Resende and Ribeiro (2010).
 * Mauricio G.C. Resende and Celso C. Ribeiro
 * Reference: 
 * 
 * RESENDE, M. G. C.; RIBEIRO, C. C. Greedy Randomized Adaptive Search Procedures: Advances, Hybridizations, and Applications. Gendreau, M.; Potvin, J.-Y. (Eds.), Handbook of
   Metaheuristics, volume 146 of International Series in Operations Research
   & Management Science, p. 283-319. Springer US, 2010.
 */

package metaheuristics;

import java.util.ArrayList;
import java.util.Random;

import bases.BaseDataSet;
import bases.BaseHeuristics;

public class GRASP extends BaseHeuristics {
	private BaseHeuristics localSearch;
	private double alpha;
	private int maxIterations;

	public GRASP(BaseDataSet dataSet, BaseHeuristics localSearch, double alpha, int maxIteration) {
		super(dataSet);
		this.localSearch = localSearch;
		this.alpha = alpha;
		this.maxIterations = maxIteration;
	}

	@Override
	public void execute() {
		ArrayList<Integer> construtiveSolution;
		double valueConstrutiveSolution;
		
		for (int i = 0; i < maxIterations; i++) {
			
			// Creating a greedy random solution
			construtiveSolution = greedyRandomizedConstruction();
			valueConstrutiveSolution = this.dataSet.calculateFx(construtiveSolution);
			this.setSolution(construtiveSolution);
			this.setValueFx(valueConstrutiveSolution);
			
			// Configuring Local Search and Running It
			this.localSearch.setSolution(construtiveSolution);
			this.localSearch.setValueFx(valueConstrutiveSolution);
			this.localSearch.execute();
			
			
			// Checking whether to update the current solution with the local search solution
			if (this.localSearch.getValueFx() < this.getValueFx()) {
				this.setSolution(this.localSearch.getSolution());
				this.setValueFx(this.localSearch.getValueFx());
			}
		}
	}
	
	// Builds a greedy and random solution 
	private ArrayList<Integer> greedyRandomizedConstruction() {
		ArrayList<Integer> solution = new ArrayList<>();
		ArrayList<Integer> candidatesCities = new ArrayList<>();
		ArrayList<Integer> RCL = new ArrayList<>();
		double distance;
		double minDistance, maxDistance;
		Random random = new Random();
		
		// Creates the list of candidate cities (not visited)
		for (int i = 1; i < this.dataSet.getTotalCity(); i++) {
			candidatesCities.add(i);
		}
		
		// Search for the shortest visiting distance from the city of origin to all unvisited cities
		minDistance = Double.MAX_VALUE;
		for (Integer city : candidatesCities) {
			distance = this.dataSet.getDistance(0, city);
			if (distance < minDistance) {
				minDistance = distance;
			}
		}
		
		// Search for the longest visiting distance from the city of origin to all unvisited cities
		maxDistance = Double.MIN_VALUE;
		for (Integer city : candidatesCities) {
			distance = this.dataSet.getDistance(0, city);
			if (distance > maxDistance) {
				maxDistance = distance;
			}
		}
		
		// Creates a Restricted Candidate List
		for (Integer city : candidatesCities) {
			distance = this.dataSet.getDistance(0, city);
			if (distance <= (minDistance + this.alpha * (maxDistance - minDistance))) {
				RCL.add(city);
			}
		}
		
		// Randomly chooses a city of RCL and adds it in the solution
		int index = random.nextInt(RCL.size());
		solution.add(RCL.get(index));
		
		// Remove from the list of candidate cities the city that was added to the solution
		int cityAdded = RCL.get(index);
		int indexCityAdded = candidatesCities.indexOf(cityAdded);
		candidatesCities.remove(indexCityAdded);
		
		RCL.clear();
			
		
		while (candidatesCities.size() > 0) {
			
			// Search the shortest visiting distance from the last visited city to all unvisited cities
			minDistance = Double.MAX_VALUE;
			for (Integer city : candidatesCities) {
				distance = this.dataSet.getDistance(solution.get(solution.size() - 1), city);
				if (distance < minDistance) {
					minDistance = distance;
				}
			}
			
			// Search the longest visiting distance from the last visited city to all unvisited cities
			maxDistance = Double.MIN_VALUE;
			for (Integer city : candidatesCities) {
				distance = this.dataSet.getDistance(solution.get(solution.size() - 1), city);
				if (distance > maxDistance) {
					maxDistance = distance;
				}
			}
			
			// Creates a Restricted Candidate List
			for (Integer city : candidatesCities) {
				distance = this.dataSet.getDistance(solution.get(solution.size() - 1), city);
				if (distance <= (minDistance + this.alpha * (maxDistance - minDistance))) {
					RCL.add(city);
				}
			}
			
			// Randomly chooses a city of RCL and adds it in the solution
			int index2 = random.nextInt(RCL.size());
			solution.add(RCL.get(index2));
			
			// Remove from the list of candidate cities the city that was added to the solution
			int cityAdded2 = RCL.get(index2);
			int indexCityAdded2 = candidatesCities.indexOf(cityAdded2);
			candidatesCities.remove(indexCityAdded2);
			
			RCL.clear();
		}
		
		
		return solution;
	}

}
