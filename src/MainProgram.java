import java.util.Calendar;
import java.util.Date;

public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Problems p = new Problems();

		// int[][] trust = new int[2][2];
		// trust[0][0] = 1;
		// trust[0][1] = 2;
		// trust[1][0] = 3;
		// trust[1][1] = 4;
		// trust[2][0] = 3;
		// trust[2][1] = 1;

		// int[] result = p.gardenNoAdj(7, trust);
//		for(int i = 0; i<result.length; i++)
//		{
//			System.out.println(result[i]);
//		}

		// System.out.println(p.findNumbers());
		// [10,5,15,3,7,null,18]
//		TreeNode tn = new TreeNode(9);
//		tn.left = new TreeNode(9);
//		tn.right = new TreeNode(6);
//		tn.left.left = new TreeNode(9);
//		tn.left.right = new TreeNode(9);
//		tn.right.right = new TreeNode(18);
//		System.out.println(p.rangeSumBST(tn,7,15));
//[0,0,0],[0,1,0],[0,0,0]
		int[][] obstacles = new int[22][33];
		
//		obstacles[0] = new int[] { 0, 0, 0, 0, 0, 0, 0 };
//		obstacles[1] = new int[] { 0, 0, 0, 1, 0, 0, 0 };
//		obstacles[2] = new int[] { 0, 0, 0, 0, 0, 0, 0 };

		// obstacles[0] = new int[] { 0, 0, 0, 0, 0 };
		// obstacles[1] = new int[] { 0, 0, 1, 0, 0 };
		// obstacles[2] = new int[] { 0, 0, 0, 0, 0 };
		obstacles[0] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0,
				0, 0, 0, 0, 0 };
		obstacles[1] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 1 };
		obstacles[2] = new int[] { 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0,
				0, 0, 0, 0, 0 };
		obstacles[3] = new int[] { 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 1 };
		obstacles[4] = new int[] { 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1,
				0, 0, 0, 0, 0 };
		obstacles[5] = new int[] { 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 1, 1, 0 };
		obstacles[6] = new int[] { 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0,
				0, 0, 0, 0, 0 };
		obstacles[7] = new int[] { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0,
				0, 0, 0, 0, 0 };
		obstacles[8] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 1, 0 };
		obstacles[9] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1,
				0, 0, 0, 0, 0 };
		obstacles[10] = new int[] { 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0 };
		obstacles[11] = new int[] { 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1,
				0, 0, 0, 0, 1 };
		obstacles[12] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1,
				0, 0, 0, 0, 0 };
		obstacles[13] = new int[] { 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1,
				0, 0, 0, 0, 0 };
		obstacles[14] = new int[] { 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
				0, 1, 1, 0, 1 };
		obstacles[15] = new int[] { 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0 };
		obstacles[16] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0,
				0, 0, 0, 0, 0 };
		obstacles[17] = new int[] { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0,
				0, 0, 0, 1, 1 };
		obstacles[18] = new int[] { 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1,
				1, 0, 1, 0, 1 };
		obstacles[19] = new int[] { 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0 };
		obstacles[20] = new int[] { 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 1, 1 };
		obstacles[21] = new int[] { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0,
				0, 1, 0, 0, 0 };

		
		System.out.println(p.uniquePathsWithObstacles(obstacles));
		
		
	}

}
