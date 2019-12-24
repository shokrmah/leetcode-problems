import java.text.ParseException;

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

			System.out.print(p.checkPossibility(new int[] {4,2,3}));

	}

}
