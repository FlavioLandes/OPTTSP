/* Implementation of the metaheuristic Multi-Start as described by Souza (2011).
 * 
 * Reference:
 * 
 * SOUZA, M. J. F. Inteligência computacional para otimização. Notas de aula,
   Departamento de Computação, Universidade Federal de Ouro Preto, 2011.
 */

package metaheuristics;

import java.util.ArrayList;

import bases.BaseDataSet;
import bases.BaseHeuristics;
import constructiveheuristics.RandomHeuristic;

public class MultiStart extends BaseHeuristics {
	
	private BaseHeuristics localSearch;
	private int maxIterations;

	public MultiStart(BaseDataSet dataSet, BaseHeuristics localSearch, int maxIterations) {
		super(dataSet);
		this.localSearch = localSearch;
		this.maxIterations = maxIterations;
	}

	@Override
	public void execute() {
		
		RandomHeuristic randomHeuristic = new RandomHeuristic(this.dataSet);
		ArrayList<Integer> solutionConstructive;
		double fxSolutionConstructive;
		ArrayList<Integer> solutionLocalSearch;
		double fxSolutionLocalSearch;
		
		
		for (int i = 0; i < this.maxIterations; i++) {
			
			// Create a random solution
			randomHeuristic.execute();
			solutionConstructive = randomHeuristic.getSolution();
			fxSolutionConstructive = randomHeuristic.getValueFx();
			
			if (fxSolutionConstructive < this.getValueFx()) {
				this.setSolution(solutionConstructive);
				this.setValueFx(fxSolutionConstructive);
			}
			
			// Do a local search on the random solution created
			this.localSearch.setSolution(solutionConstructive);
			this.localSearch.setValueFx(fxSolutionConstructive);
			this.localSearch.execute();
			solutionLocalSearch = this.localSearch.getSolution();
			fxSolutionLocalSearch = this.localSearch.getValueFx();
			
			if (fxSolutionLocalSearch < this.getValueFx()) {
				this.setSolution(solutionLocalSearch);
				this.setValueFx(fxSolutionLocalSearch);
			}
		}
	}
	

}
