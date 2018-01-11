/* Implementation of the heuristic of refinement Random Descent Method of Souza (2011. p.13)
 * 
 * Reference:
 * 
 * SOUZA, M. J. F. Inteligência computacional para otimização. Notas de aula,
   Departamento de Computação, Universidade Federal de Ouro Preto, 2011.
 */

package localsearchheuristics;

import java.util.ArrayList;
import java.util.Random;

import bases.BaseDataSet;
import bases.BaseHeuristics;

public class MDR extends BaseHeuristics {
	private int maxIte;// Maximum number of iterations
	

	public MDR(BaseDataSet dataSet, int maxIte) {
		super(dataSet);
		this.maxIte = maxIte;
	}

	@Override
	public void execute() {
		int iteNoImproved = 0;// Number of iterations without improvement
		ArrayList<Integer> neighbor;
		int index1, index2;
		int aux;
		boolean improved;
		Random random = new Random();
		
		while (iteNoImproved < this.maxIte) {
			neighbor = (ArrayList<Integer>) this.getSolution().clone();
			
			index1 = random.nextInt(this.getSolution().size());
			index2 = random.nextInt(this.getSolution().size());
			
			aux = neighbor.get(index1);
			neighbor.set(index1, neighbor.get(index2));
			neighbor.set(index2, aux);
			
			improved = this.updateSolution(neighbor);
			
			if (improved) {
				iteNoImproved = 0;
			} else {
				iteNoImproved++;
			}
		}
	}
	
	// If the new solution is better, then update the solution
	private boolean updateSolution(ArrayList<Integer> solution) {
		boolean improved;
		double auxValueFx;
		
		auxValueFx = this.dataSet.calculateFx(solution);
		
		if (auxValueFx < this.getValueFx()) {
			this.setSolution((ArrayList<Integer>) solution.clone());
			this.setValueFx(auxValueFx);
			improved = true;
		} else { 
			improved = false;
		}
		
		return improved;
	}
	
}
