/* Randomized construction heuristic
 * 
 */

package constructiveheuristics;

import java.util.ArrayList;
import java.util.Collections;

import bases.BaseDataSet;
import bases.BaseHeuristics;

public class RandomHeuristic extends BaseHeuristics {

	public RandomHeuristic(BaseDataSet dataSet) {
		super(dataSet);
	}

	@Override
	public void execute() {
		ArrayList<Integer> newSolution = new ArrayList<>();
		double auxValueFx;
		
		// Create a solution according to the order of the instance (city 1, 2, ..., n). The city of origin (0) is not included
		for (int index = 1; index < dataSet.getTotalCity(); index++) {
			newSolution.add(index);
		}
		
		Collections.shuffle(newSolution);// It makes several exchanges of the positions of the cities
		auxValueFx = this.dataSet.calculateFx(newSolution);
		
		this.setSolution(newSolution);
		this.setValueFx(auxValueFx);	
	}
	
}
