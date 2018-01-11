/* Implementation of metaheuristics Iterated Local Search of Lourenco, Martin and Stutzle (2010).
 * 
 * Reference: 
 * 
 * LOURENÇO, H. R.; MARTIN, O. C.; STUTZLE, T. Iterated local search:
   Framework and applications. Gendreau, M.; Potvin, J.-Y. (Eds.), Handbook of
   Metaheuristics, volume 146 of International Series in Operations Research
   & Management Science, p. 363–397. Springer US, 2010.
 */

package metaheuristics;

import java.util.ArrayList;
import java.util.Random;

import bases.BaseDataSet;
import bases.BaseHeuristics;

public class ILS extends BaseHeuristics {
	private BaseHeuristics constructiveHeuristic;
	private BaseHeuristics localSearch;
	private int maxIte;
	

	public ILS(BaseDataSet dataSet, BaseHeuristics constructiveHeuristic, BaseHeuristics localSearch, int maxIte) {
		super(dataSet);
		this.constructiveHeuristic = constructiveHeuristic;
		this.localSearch = localSearch;
		this.maxIte = maxIte;
	}

	@Override
	public void execute() {
		ArrayList<Integer> solutionConstructive;
		double fxConstructive;
		ArrayList<Integer> solutionLocalSearch;
		ArrayList<Integer> solutionPertubation;
		double fxPertubation;
		
		this.constructiveHeuristic.execute();
		
		solutionConstructive = constructiveHeuristic.getSolution();
		fxConstructive = constructiveHeuristic.getValueFx();
		
		this.updateSolution(solutionConstructive);
		
		this.localSearch.setSolution(solutionConstructive);
		this.localSearch.setValueFx(fxConstructive);
		this.localSearch.execute();
		
		solutionLocalSearch = this.localSearch.getSolution();
		
		this.updateSolution(solutionLocalSearch);
		
		
		for (int i = 0; i < this.maxIte; i++) {
			solutionPertubation = pertubation(this.getSolution()); // Disturb the current solution
			fxPertubation = this.dataSet.calculateFx(solutionPertubation);
			this.updateSolution(solutionPertubation);
			
			this.localSearch.setSolution(solutionPertubation);
			this.localSearch.setValueFx(fxPertubation);
			this.localSearch.execute();
			
			solutionLocalSearch = this.localSearch.getSolution();
			
			this.updateSolution(solutionLocalSearch);
		}
	}
	
	// Returns a disturbed solution based on the current solution 
	private ArrayList<Integer> pertubation(ArrayList<Integer> solution) {
		ArrayList<Integer> auxSolution;
		int levelPertubation;
		Random random = new Random();
		int index1, index2;
		int aux;
		
		auxSolution = (ArrayList<Integer>) solution.clone();
		
		do {
			levelPertubation = random.nextInt(auxSolution.size() / 2);
		} while (levelPertubation == 0);
		
		for (int i = 0; i < levelPertubation; i++) {
			index1 = random.nextInt(auxSolution.size());
			index2 = random.nextInt(auxSolution.size());
			
			aux = auxSolution.get(index1);
			auxSolution.set(index1, auxSolution.get(index2));
			auxSolution.set(index2, aux);
		}
		
			return auxSolution;
	}
	
	// If the new solution is better then update the current solution
	private void updateSolution(ArrayList<Integer> solution) {
		double auxValueFx;
		
		auxValueFx = this.dataSet.calculateFx(solution);
		
		if (auxValueFx < this.getValueFx()) {
			this.setSolution((ArrayList<Integer>) solution.clone());
			this.setValueFx(auxValueFx);
		}
	}
	
}
