import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Az2020 az = new Az2020();
		
		TreeNode root = new TreeNode(5);
		
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		
		
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		
		root.right.right.right = new TreeNode(1);
		
		az.hasPathSum(root,22);
	
	}

}
