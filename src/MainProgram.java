import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AzMostRecent az = new AzMostRecent();
		
//		List<List<Integer>> connections = new ArrayList<List<Integer>>();
//		
//		connections.add(Arrays.asList(0,1));
//		connections.add(Arrays.asList(1,2));
//		connections.add(Arrays.asList(2,0));
//		connections.add(Arrays.asList(1,3));
//		
//		List<List<Integer>> result = az.criticalConnections(4, connections);
//		
//		for (int i = 0; i < result.size(); i++) {
//			System.out.println(result.get(i).get(0) + " " + result.get(i).get(1));
//		}

		//az.findCircleNum(new int[][] {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}});
		
		
		//rt.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[] {"hit"});
		//rt.kClosest(new int[][] {{3,3},{5,-1},{-2,4}}, 2);
		az.minDifficulty(new int[] {11,111,22,222,33,333,44,444}, 6);
	}

}
