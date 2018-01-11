package constructiveheuristics;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;



import example.DataSet;

public class NearestNeighborHeuristicTest {

	@Test
	public void testExecute() {
		ArrayList<Integer> solutionExpected;
		DataSet dataSet = new DataSet();
		dataSet.createMatrixDistance("instances//toUnitTest.tsp");
		
		/* 				Matrix Distance to toUnitTest.tsp 
		 * [0.0, 666.1080993352356, 281.1138559374119, 395.60080889704966]
		   [666.1080993352356, 0.0, 649.3265742290239, 1047.091209016674]
		   [281.1138559374119, 649.3265742290239, 0.0, 603.5105632878351]
		   [395.60080889704966, 1047.091209016674, 603.5105632878351, 0.0]
		*/
		
		NearestNeighborHeuristic nearestNeighborHeuristic = new NearestNeighborHeuristic(dataSet);
		nearestNeighborHeuristic.execute();
		
		solutionExpected = new ArrayList<>();
		solutionExpected.add(2);
		solutionExpected.add(3);
		solutionExpected.add(1);
		
		assertEquals(solutionExpected, nearestNeighborHeuristic.getSolution());
	}

}
