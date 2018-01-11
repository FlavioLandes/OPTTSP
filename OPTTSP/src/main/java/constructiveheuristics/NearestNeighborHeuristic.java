/* Heuristic of the nearest neighbor of Souza (2011, page 5).
 * 
 * Reference:
 * 
 * SOUZA, M. J. F. Inteligência computacional para otimização. Notas de aula,
   Departamento de Computação, Universidade Federal de Ouro Preto, 2011.
 */

package constructiveheuristics;

import java.util.ArrayList;

import bases.BaseDataSet;
import bases.BaseHeuristics;

public class NearestNeighborHeuristic extends BaseHeuristics {

	public NearestNeighborHeuristic(BaseDataSet dataSet) {
		super(dataSet);
	}

	
	@Override
	public void execute() {
		ArrayList<Integer> newSolution = new ArrayList<>();
		double auxValueFx;
	 	ArrayList<Integer> cities = new ArrayList<>();// List of candidate cities
	 	double distance;
	 	int idxSmallerDistance;
	 	double smallerDistance;
	 	
	 	for (int i = 1; i < this.dataSet.getTotalCity(); i++) {
	 		cities.add(i);
	 	}
	 	
	 	// Search the city nearest the origin
	 	smallerDistance = this.dataSet.getDistance(0, cities.get(0));
	 	idxSmallerDistance = 0;
	 	for (int i = 1; i < cities.size(); i++) {
	 		distance = this.dataSet.getDistance(0, cities.get(i));
	 		if (distance < smallerDistance) {
	 			smallerDistance = distance;
	 			idxSmallerDistance = i;
	 		}
	 	}
	 	
	 	newSolution.add(cities.get(idxSmallerDistance));// Add in solution the city nearest the origin
	 	cities.remove(idxSmallerDistance);// Remove the city the list of candidate cities
	 	
	 	// Loop to search the city for next to last visited. Repeat this step until all cities have been visited
	 	while (cities.size() > 0) {
	 		smallerDistance = this.dataSet.getDistance(newSolution.get(newSolution.size() - 1), cities.get(0));
		 	idxSmallerDistance = 0;
		 	for (int i = 1; i < cities.size(); i++) {
		 		distance = this.dataSet.getDistance(newSolution.get(newSolution.size() - 1), cities.get(i));
		 		if (distance < smallerDistance) {
		 			smallerDistance = distance;
		 			idxSmallerDistance = i;
		 		}
		 	}
		 	
		 	newSolution.add(cities.get(idxSmallerDistance));// Add in solution the city nearest the origin
		 	cities.remove(idxSmallerDistance);// Remove the city the list of candidate cities 	
	 	}
	 	
	 	this.setSolution(newSolution);
	 	auxValueFx = this.dataSet.calculateFx(newSolution);
	 	this.setValueFx(auxValueFx);
	}

}
