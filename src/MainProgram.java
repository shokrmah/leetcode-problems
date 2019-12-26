import java.util.Calendar;
import java.util.Date;

public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Problems p = new Problems();



		TreeNode tn = new TreeNode(1);
		//tn.left = new TreeNode(1);
		tn.right = new TreeNode(2);
//		tn.left.left = new TreeNode(9);
		//tn.left.right = new TreeNode(2);
		tn.right.left = new TreeNode(3);
//		System.out.println(p.rangeSumBST(tn,7,15));
//[0,0,0],[0,1,0],[0,0,0]
		
		
		System.out.println(p.postorderTraversal(tn));
		
		
	}

}
