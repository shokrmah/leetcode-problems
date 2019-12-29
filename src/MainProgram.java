import java.util.Calendar;
import java.util.Date;

public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Problems p = new Problems();

		TreeNode tn = new TreeNode(4);
		tn.left = new TreeNode(1);
		tn.right = new TreeNode(6);
		tn.left.left = new TreeNode(0);
		tn.left.right = new TreeNode(2);
		tn.left.right = new TreeNode(3);
		tn.right.left = new TreeNode(5);
		tn.right.right = new TreeNode(7);
		tn.right.right = new TreeNode(8);

		//System.out.println(p.maxDistToClosest(new int[] { 1,0,0,0,0,0,1 }));
		System.out.println(p.bstToGst(tn).val);
	}

}
