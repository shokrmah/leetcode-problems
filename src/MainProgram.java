
public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Problems p = new Problems();

		TreeNode tn = new TreeNode(1);
		tn.left = new TreeNode(2);
		tn.right = new TreeNode(3);
		// tn.left.left = new TreeNode(0);
		tn.left.right = new TreeNode(4);
		// tn.left.right = new TreeNode(3);
		// tn.right.left = new TreeNode(5);
		tn.right.right = new TreeNode(5);
		// tn.right.right = new TreeNode(8);

		// System.out.println(p.maxDistToClosest(new int[] { 1,0,0,0,0,0,1 }));

		ListNode n = new ListNode(1);
		n.next = new ListNode(1);
		//n.next.next = new ListNode(3);
		//n.next.next.next = new ListNode(4);
		//n.next.next.next.next = new ListNode(5);
		//n.next.next.next.next.next = n.next.next;
	
		System.out.println(p.isPalindrome(n));
	}

}
