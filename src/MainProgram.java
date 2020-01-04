
public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Problems p = new Problems();

		TreeNode tn = new TreeNode(3);
		tn.left = new TreeNode(1);
		tn.right = new TreeNode(4);
		// tn.left.left = new TreeNode(0);
		tn.left.right = new TreeNode(2);
		// tn.left.right = new TreeNode(3);
		// tn.right.left = new TreeNode(5);
		// tn.right.right = new TreeNode(5);
		// tn.right.right = new TreeNode(8);

		// System.out.println(p.maxDistToClosest(new int[] { 1,0,0,0,0,0,1 }));

		ListNode n = new ListNode(1);
		n.next = new ListNode(2);
		n.next.next = new ListNode(3);
		n.next.next.next = new ListNode(4);
		// n.next.next.next.next = new ListNode(5);
		n.next.next.next.next = n.next;

		// System.out.println(y);
		// [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]
		int[][] matrix = new int[5][5];
		matrix[0] = new int[] { 1, 4, 7, 11, 15 };
		matrix[1] = new int[] { 2, 5, 8, 12, 19 };
		matrix[2] = new int[] { 3, 6, 9, 16, 22 };
		matrix[3] = new int[] { 10, 13, 14, 17, 24 };
		matrix[4] = new int[] { 18, 21, 23, 26, 30 };

		System.out.println(p.maxNumberOfBalloons("mbetypbpefxvviadqaodrbjeoacfomepmzymiudltgnvnpbowwmjgpzzhtiismearuwocsgbiimiqqzaozgeizikrlxmupfzjzmlfttqqbpfblqfkecsdfbsceqjhubfxksivrfwvukapxmuciybfhzlmpeamdxziptxregymqtmgcsujmugissgnlbhxbcxxeoumcqyulvahuianbaaxgzrtmshjguqdaxvxndzoqvwmcjfhpevavnrciqbymnlylbrfkkiceienoarfrzzxtuaqapaeqeqolozadmtgjyhfqzpuaskjuawxqkdqyjqcmbxtvshzrquvegcuyuckznspmrxvqdassidcmrajedsnuuumfwqzvasljlyvfefktiqgvzvdzojtjegsyhbepdkuwvgrfscezvswywmdavpxlekbrlkfnbyvlobazmvgulxrfdranuhomkrlpbfeagfxxxuhjuqhbkhznixquxrxngwimdxdhqbdaouitsvcdmbwxbbaomkgxsqwnexbjjyhtxvkjfqkrrxjghvzqsattubphryqxxdyjkihfnzvjhohnhdlfwoqiwtmwzfgcyhyqtcketvgnbchcxvnhcsoosirfqgdgcsitegzlxdfijzmxnvhrulmgvoqfpzesootscnxenokmmozmoxpaverydbsnimwacjqhrtxkqtvghjyushoctxphxzztukgmnoeycqaeukymvwxcsyvvctflqjhtcvjtxncuvhkptkjnzaetwbzkwnseovewuhpkaxiphdicgacszzdturzgjkzwgkmzzavykancvvzaafgzjhcyicorrblmhsnnkhfkujttbkuuedhwguuaapojmnjdfytdhrepjwcddzsoeutlbbljlikghxefgbqenwamanikmynjcupqpdjnhldaixwygcvsgdkzszmsptqqnroflgozblygtiyaxudwmooiviqcosjfksnevultrf"));
	}

}
