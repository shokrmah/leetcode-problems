import java.util.ArrayList;
import java.util.List;

public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Problems p = new Problems();
//
//		TreeNode tn = new TreeNode(3);
//		tn.left = new TreeNode(1);
//		tn.right = new TreeNode(4);
//		// tn.left.left = new TreeNode(0);
//		tn.left.right = new TreeNode(2);
//		// tn.left.right = new TreeNode(3);
//		// tn.right.left = new TreeNode(5);
//		// tn.right.right = new TreeNode(5);
//		// tn.right.right = new TreeNode(8);
//
//		// System.out.println(p.maxDistToClosest(new int[] { 1,0,0,0,0,0,1 }));
//
//		ListNode n = new ListNode(1);
//		n.next = new ListNode(2);
//		n.next.next = new ListNode(3);
//		n.next.next.next = new ListNode(4);
//		// n.next.next.next.next = new ListNode(5);
//		n.next.next.next.next = n.next;
//
//		// System.out.println(y);
//		// [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]
//		int[][] matrix = new int[5][5];
//		matrix[0] = new int[] { 1, 4, 7, 11, 15 };
//		matrix[1] = new int[] { 2, 5, 8, 12, 19 };
//		matrix[2] = new int[] { 3, 6, 9, 16, 22 };
//		matrix[3] = new int[] { 10, 13, 14, 17, 24 };
//		matrix[4] = new int[] { 18, 21, 23, 26, 30 };
//
//		System.out.println(p.findErrorNums(new int[] { 1, 2, 2, 4 }));

		BloombergProblems bp = new BloombergProblems();

//		Node head = new Node(1);
//		head.next = new Node(2);
//		head.next.next = new Node(3);
//		head.next.next.next = new Node(4);
//		head.next.next.next.next = new Node(5);
//		head.next.next.next.next.next = new Node(6);
//		head.next.next.child = new Node(7);
//		head.next.next.child.next = new Node(8);
//		head.next.next.child.next.child = new Node(11);
//
//		Node result = bp.flatten(head);
//		while (result != null) {
//			//System.out.println(result.val);
//			result = result.next;
//			if (result.val == 6) {
//				while (result != null) {
//					System.out.println(result.val);
//					result = result.prev;
//				}
//				break;
//			}
//		}

		// bp.invalidTransactions(new String[]
		// {"chalicefy,639,1283,beijing","maybe,324,192,frankfurt","bob,627,974,amsterdam","alex,962,125,chicago","iris,849,36,beijing","chalicefy,70,415,bangkok","chalicefy,112,467,frankfurt","xnova,358,82,barcelona","chalicefy,180,543,beijing","xnova,624,572,budapest","lee,651,1761,chicago","alex,991,1698,budapest","bob,531,700,amsterdam","chalicefy,926,478,budapest","iris,235,1993,frankfurt","alex,107,812,beijing","maybe,199,1313,barcelona"});

		// "zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef"

		// String s = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
		// System.out.println(bp.decodeString(s));

//		s = "2[2[2[r]c]b]";
//		System.out.println(bp.decodeString(s));
//		
//		s = "3[a]2[b4[F]c]";
//		System.out.println(bp.decodeString(s));
//		
//		s = "100[leetcode]";
//		System.out.println(bp.decodeString(s));
//
//		s = "3[a]2[bc]";
//		System.out.println(bp.decodeString(s));
//
//		s = "3[a2[c]]";
//		System.out.println(bp.decodeString(s));
//
		// String s = "2[abc]3[cd]ef";
		// System.out.println(bp.decodeString(s));

		// System.out.println(bp.frequencySort("tree"));

//		Input: [[1,2], [3], [3], []] 
		// Output: [[0,1,3],[0,2,3]]
		List<String> a = new ArrayList<String>();
		
		//"acaaaaabbbdbcccdcdaadcdccacbcccabbbbcdaaaaaadb"

		Node n = new Node(7);
		Node n2 = new Node(13);
		n.next = n2;
		n.random = null;
		n2.next = null;
		n2.random = n;
		
		Node m = bp.copyRandomList(n);
		System.out.println(m);
	}

}
