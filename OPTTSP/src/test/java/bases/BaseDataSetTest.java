package bases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import example.DataSet;

public class BaseDataSetTest {

	@Test
	public void testCalculateFx() {
		ArrayList<Integer> solution;
		DataSet dataSet = new DataSet();
		dataSet.createMatrixDistance("instances//toUnitTest.tsp");
		
		/* 				Matrix Distance to toUnitTest.tsp 
		 * [0.0, 666.1080993352356, 281.1138559374119, 395.60080889704966]
		   [666.1080993352356, 0.0, 649.3265742290239, 1047.091209016674]
		   [281.1138559374119, 649.3265742290239, 0.0, 603.5105632878351]
		   [395.60080889704966, 1047.091209016674, 603.5105632878351, 0.0]
		*/   
		
		solution = new ArrayList<>();
		solution.add(2);
		solution.add(3);
		solution.add(1);
		
		assertEquals(2597.8237275771567, dataSet.calculateFx(solution), 0);
	}

}
