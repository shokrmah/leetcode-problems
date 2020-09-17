import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class BloombergProblems {

	public int dieSimulator(int n, int[] r) {
		long mod = (long) (Math.pow(10, 9) + 7);
		if (n == 1)
			return 6;
		long[][] dp = new long[n][7];
		for (int i = 0; i < 6; i++) {
			dp[0][i] = 1;
		}
		dp[0][6] = 6;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 6; j++) {
				long sum = dp[i - 1][6];
				if (i - r[j] == 0)
					sum--; // exclude 111, say i = 2, r[j] = 2;
				if (i - r[j] - 1 >= 0)
					sum = (sum - dp[i - r[j] - 1][6] + dp[i - r[j] - 1][j] + mod) % mod; // exclude 2111, 3111, but not
																							// 1111
				dp[i][j] = sum;
				dp[i][6] = (dp[i][6] + sum) % mod;
			}
		}
		return (int) dp[n - 1][6];
	}

	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;

		Stack<Integer> firstList = new Stack<Integer>();
		Stack<Integer> secondList = new Stack<Integer>();

		while (l1 != null) {
			firstList.add(l1.val);
			l1 = l1.next;
		}

		while (l2 != null) {
			secondList.add(l2.val);
			l2 = l2.next;
		}

		ListNode head = null;

		int sum = 0;
		int remainder = 0;

		while (!firstList.isEmpty() || !secondList.isEmpty()) {
			sum = remainder;
			if (!firstList.isEmpty())
				sum = sum + firstList.pop();
			if (!secondList.isEmpty())
				sum = sum + secondList.pop();

			remainder = sum / 10;
			sum = sum % 10;

			ListNode node = new ListNode(sum);
			node.next = head;
			head = node;
		}

		if (remainder > 0) {
			ListNode node = new ListNode(remainder);
			node.next = head;
			head = node;
		}

		return head;
	}

	public int firstUniqChar(String s) {
		if (s.length() == 0)
			return -1;

		int[] counts = new int[28];

		for (int i = 0; i < s.length(); i++) {
			counts[s.charAt(i) - 'a']++;
		}

		for (int i = 0; i < s.length(); i++) {
			if (counts[s.charAt(i) - 'a'] == 1)
				return i;
		}

		return -1;
	}

	public int triangleNumber(int[] nums) {
		int count = 0;

		if (nums.length < 3)
			return 0;

		Arrays.sort(nums);

		int k = 0;
		for (int i = 0; i < nums.length - 2; i++) {
			k = i + 2;
			for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
				while (k < nums.length && nums[i] + nums[j] > nums[k])
					k++;
				count += k - j - 1;

			}

		}

		return count;
	}

	public boolean isValidBST(TreeNode root) {
		return helper(root, null, null);
	}

	public boolean helper(TreeNode node, Integer lower, Integer upper) {
		if (node == null)
			return true;

		int val = node.val;
		if (lower != null && val <= lower)
			return false;
		if (upper != null && val >= upper)
			return false;

		if (!helper(node.right, val, upper))
			return false;
		if (!helper(node.left, lower, val))
			return false;
		return true;
	}

	public Node copyRandomList(Node head) {
		if (head == null)
			return null;

		Map<Node, Node> myMap = new HashMap<Node, Node>();

		Node mover = head;
		Node newHead = copyNode(mover, myMap);

		Node moverNewHead = newHead;
		mover = head;
		while (mover != null) {
			if (mover.random != null) {
				moverNewHead.random = myMap.get(mover.random);
			}

			mover = mover.next;
			moverNewHead = moverNewHead.next;
		}

		return newHead;
	}

	public Node copyNode(Node original, Map<Node, Node> myMap) {
		if (original == null)
			return null;

		Node copy = new Node(original.val);
		copy.next = copyNode(original.next, myMap);
		myMap.put(original, copy);
		return copy;
	}

	public boolean wordBreak(String s, List<String> wordDict) {

		Set<String> dictionary = new HashSet<String>();
		for (int i = 0; i < wordDict.size(); i++) {
			dictionary.add(wordDict.get(i));

		}

		int n = s.length();
		boolean[] dp = new boolean[n + 1];
		dp[0] = true;
		for (int i = 1; i <= n; i++) {
			if (dictionary.contains(s.substring(0, i))) {
				dp[i] = true;
				continue;
			}
			for (int j = 0; j < i; j++) {
				if (dp[j] && dictionary.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[n];

//		List<StringBuilder> stringList = new ArrayList<StringBuilder>();
//		StringBuilder sb = new StringBuilder();
//		stringList.add(sb);
//
//		
//		int size = 0;
//		for (int i = 0; i < s.length(); i++) {
//			size = stringList.size();
//			for (int j = 0; j < size; j++) {
//				stringList.get(j).append(s.charAt(i));
//				if (dictionary.contains(stringList.get(j).toString())) {
//					if(i == s.length() - 1)
//						return true;
//					StringBuilder sb2 = new StringBuilder();
//					stringList.add(sb2);
//				}
//			}
//
//		}
//
//
//		return false;
	}

	public int numIslands(char[][] grid) {

		int islandsCount = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '1') {
					dfs(grid, i, j);
					islandsCount++;
				}
			}
		}

		return islandsCount;
	}

	public void dfs(char[][] grid, int i, int j) {
		if (i >= grid.length || i < 0 || j >= grid[i].length || j < 0)
			return;

		if (grid[i][j] == '1') {
			grid[i][j] = '2';
			dfs(grid, i + 1, j);
			dfs(grid, i - 1, j);
			dfs(grid, i, j + 1);
			dfs(grid, i, j - 1);

		}
	}

	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {


		List<List<Integer>> result = new ArrayList<List<Integer>>();
		addPaths(result, new ArrayList<Integer>(), graph, 0);
		return result;
	}

//	Input: [[1,2], [3], [3], []] 
	// Output: [[0,1,3],[0,2,3]]
	public void addPaths(List<List<Integer>> result, List<Integer> tmp, int[][] graph, int index) {
		tmp.add(index);
		if (index == graph.length - 1)
			result.add(new ArrayList<Integer>(tmp));

		for (int i = 0; i < graph[index].length; i++) {
			addPaths(result, tmp, graph, graph[index][i]);
		}
		tmp.remove(tmp.size() - 1);
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;

		int extra = 0;

		ListNode result;
		int sum = l1.val + l2.val;
		if (sum >= 10) {
			result = new ListNode(sum - 10);
			extra = 1;
		} else
			result = new ListNode(sum);

		l1 = l1.next;
		l2 = l2.next;
		ListNode it = result;
		while (l1 != null && l2 != null) {
			sum = l1.val + l2.val + extra;
			if (sum >= 10) {
				it.next = new ListNode(sum - 10);
				extra = 1;
			} else {
				it.next = new ListNode(sum);
				extra = 0;
			}

			it = it.next;
			l1 = l1.next;
			l2 = l2.next;
		}

		while (l1 != null) {
			sum = l1.val + extra;
			if (sum >= 10) {
				it.next = new ListNode(sum - 10);
				extra = 1;
			} else {
				it.next = new ListNode(sum);
				extra = 0;
			}
			it = it.next;
			l1 = l1.next;
		}
		while (l2 != null) {
			sum = l2.val + extra;
			if (sum >= 10) {
				it.next = new ListNode(sum - 10);
				extra = 1;
			} else {
				it.next = new ListNode(sum);
				extra = 0;
			}
			it = it.next;
			l2 = l2.next;
		}

		if (extra == 1)
			it.next = new ListNode(1);

		return result;
	}

	public int compress(char[] chars) {

		if (chars.length <= 1)
			return chars.length;

		int currentIndex = 0;
		char currentChar = chars[0];
		int countOfRepeat = 1;
		String intVal = "";

		for (int i = 1; i < chars.length; i++) {
			if (chars[i] == currentChar) {
				countOfRepeat++;
			} else if (countOfRepeat > 1) {
				intVal = countOfRepeat + "";
				chars[currentIndex] = currentChar;
				for (int j = 0; j < intVal.length(); j++) {
					currentIndex++;
					chars[currentIndex] = intVal.charAt(j);
				}

				countOfRepeat = 1;
				currentChar = chars[i];
				currentIndex++;
			} else {
				// currentIndex++;
				chars[currentIndex] = currentChar;
				currentChar = chars[i];
				currentIndex++;
			}

		}

		if (countOfRepeat > 1) {
			chars[currentIndex] = currentChar;
			intVal = countOfRepeat + "";
			for (int j = 0; j < intVal.length(); j++) {
				currentIndex++;
				chars[currentIndex] = intVal.charAt(j);
			}
		} else {
			// currentIndex++;
			chars[currentIndex] = currentChar;
		}

		return currentIndex + 1;
	}

	public boolean search(int[] nums, int target) {

		if (nums.length == 0)
			return false;

		int pivot = findPivot(nums, 0, nums.length - 1);

		if (pivot == -1)
			return binarySearch(nums, 0, nums.length - 1, target);

		if (nums[pivot] == target)
			return true;

		if (nums[0] <= target)
			return binarySearch(nums, 0, pivot - 1, target);

		return binarySearch(nums, pivot + 1, nums.length - 1, target);

	}

	public int findPivot(int[] nums, int low, int high) {
		if (high < low)
			return -1;
		if (high == low)
			return -1;

		int mid = (low + high) / 2;

		if (mid < high && nums[mid] > nums[mid + 1])
			return mid;

		if (mid > low && nums[mid] < nums[mid - 1])
			return mid - 1;

		if (nums[low] >= nums[mid])
			return findPivot(nums, low, mid - 1);
		return findPivot(nums, mid + 1, high);
	}

	public boolean binarySearch(int[] nums, int low, int high, int target) {
		if (high < low)
			return false;

		int mid = (high + low) / 2;

		if (nums[mid] == target)
			return true;
		else if (target > nums[mid])
			return binarySearch(nums, mid + 1, high, target);
		else
			return binarySearch(nums, low, mid - 1, target);
	}

	public boolean isCousins(TreeNode root, int x, int y) {

		int xDepth = -1;
		int yDepth = -1;

		if (root.val == x || root.val == y)
			return false;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);

		TreeNode tmpNode;
		int currentLevelSize = 0;
		int currentLevel = -1;
		int xParent = -1;
		int yParent = -1;
		int parent = -1;

		while (!queue.isEmpty()) {
			currentLevelSize = queue.size();
			currentLevel++;
			for (int i = 0; i < currentLevelSize; i++) {
				parent++;
				tmpNode = queue.poll();
				if (tmpNode.left != null) {
					queue.add(tmpNode.left);
					if (tmpNode.left.val == x) {
						xDepth = currentLevel;
						xParent = parent;
					}
					if (tmpNode.left.val == y) {
						yDepth = currentLevel;
						yParent = parent;
					}
				} else if (tmpNode.right != null) {
					queue.add(tmpNode.right);
					if (tmpNode.right.val == x) {
						xDepth = currentLevel;
						xParent = parent;
					}
					if (tmpNode.right.val == y) {
						yDepth = currentLevel;
						yParent = parent;
					}
				}

				if (xDepth != -1 && yDepth != -1) {
					queue.clear();
					break;
				}
			}

		}
		if (xDepth != -1 && xDepth == yDepth && xParent != yParent)
			return true;

		return false;
	}

	public int widthOfBinaryTree(TreeNode root) {
		if (root == null)
			return 0;

		if (root.left == null && root.right == null)
			return 1;

		int maxWidth = 1;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();

		queue.add(root);

		TreeNode tmpNode;
		int currentSize = 0;

		// int tmpWidth = 0;
		int nodeLocation = -1;
		int firstNotNull = -1;
		int lastNotNull = -1;
		int nullCounts = -1;
		while (!queue.isEmpty()) {

			currentSize = queue.size();
			// tmpWidth = 0;
			firstNotNull = -1;
			lastNotNull = -1;
			for (int i = 0; i < currentSize; i++) {
				tmpNode = queue.poll();
				nodeLocation++;
				if (tmpNode.left != null) {
					queue.add(tmpNode.left);
					if (firstNotNull == -1)
						firstNotNull = nodeLocation;
					lastNotNull = nodeLocation;
				} else if (firstNotNull != -1)
					nullCounts++;

				nodeLocation++;
				if (tmpNode.right != null) {
					queue.add(tmpNode.right);
					if (firstNotNull == -1)
						firstNotNull = nodeLocation;
					lastNotNull = nodeLocation;
				} else if (firstNotNull != -1)
					nullCounts++;

			}

			if ((lastNotNull - firstNotNull + 1) > maxWidth)
				maxWidth = lastNotNull - firstNotNull + 1;

		}

		return maxWidth;
	}

	public List<List<Integer>> generate(int numRows) {
		if (numRows <= 0)
			return new ArrayList<List<Integer>>();

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> tmp = new ArrayList<Integer>();
		tmp.add(1);
		result.add(tmp);

		if (numRows == 1)
			return result;

		tmp = new ArrayList<Integer>();
		tmp.add(1);
		tmp.add(1);

		result.add(tmp);

		List<Integer> myLastList;
		int currentValue = 0;
		for (int i = 2; i < numRows; i++) {
			tmp = new ArrayList<Integer>();
			myLastList = result.get(result.size() - 1);
			tmp.add(myLastList.get(0));
			for (int j = 1; j < myLastList.size(); j++) {
				currentValue = myLastList.get(j - 1) + myLastList.get(j);
				tmp.add(currentValue);
			}
			tmp.add(myLastList.get(myLastList.size() - 1));
			result.add(tmp);
		}

		return result;
	}

	public String frequencySort(String s) {
		Map<Character, Integer> counts = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			counts.putIfAbsent(s.charAt(i), 0);
			counts.put(s.charAt(i), counts.get(s.charAt(i)) + 1);
		}

		List<Map.Entry<Character, Integer>> sorted = new LinkedList<Map.Entry<Character, Integer>>(counts.entrySet());

		Collections.sort(sorted, new Comparator<Map.Entry<Character, Integer>>() {
			@Override
			public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < sorted.size(); i++) {
			for (int j = 0; j < sorted.get(i).getValue(); j++) {
				sb.append(sorted.get(i).getKey());
			}
		}

		return sb.toString();
	}

	public String minRemoveToMakeValid(String s) {
		int noOfOpen = 0;
		int noOfClosed = 0;
		char[] word = s.toCharArray();
		for (int i = 0; i < word.length; i++) {
			if (word[i] == '(') {
				noOfOpen++;
			} else if (word[i] == ')') {
				noOfClosed++;
				if (noOfClosed > noOfOpen) {
					word[i] = '0';
					noOfClosed--;
				}
			}
		}

		int extraOpen = noOfOpen - noOfClosed;
		if (extraOpen > 0) {
			for (int i = word.length - 1; i >= 0; i--) {
				if (word[i] == '(') {
					word[i] = '0';
					extraOpen--;
					if (extraOpen == 0)
						break;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < word.length; i++) {
			if (word[i] != '0')
				sb.append(word[i]);
		}

		return sb.toString();
	}

	public String removeDuplicates(String s, int k) {
		if (s.length() == 1)
			return s;

		StringBuilder sb = new StringBuilder(s);

		int countOfDuplicats = 1;
		for (int i = 1; i < sb.length(); i++) {
			if (sb.charAt(i) == sb.charAt(i - 1)) {
				countOfDuplicats++;
				if (countOfDuplicats == k) {
					for (int j = i; j > i - k; j--) {
						sb.deleteCharAt(j);
					}
					i = i - k;
					if (k > 2)
						i = i - (k - 2);
					if (i < 0) {
						i = 0;
					}
					countOfDuplicats = 1;
				}
			} else
				countOfDuplicats = 1;
		}

		return sb.toString();
	}

//3[a2[c]]
	public String decodeString(String s) {

		Stack<String> myStack = new Stack<String>();

		StringBuilder tmpSb = new StringBuilder();

		StringBuilder result = new StringBuilder();
		Stack<String> resultStack = new Stack<String>();

		// fill stack
		for (int i = 0; i < s.length(); i++) {
			if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'))
				tmpSb.append(s.charAt(i));
			else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				if (tmpSb.length() > 0 && ((tmpSb.charAt(0) >= 'a' && tmpSb.charAt(0) <= 'z')
						|| (tmpSb.charAt(0) >= 'A' && tmpSb.charAt(0) <= 'Z'))) {
					myStack.add(tmpSb.toString());
					tmpSb.setLength(0);
				}
				tmpSb.append(s.charAt(i));
			} else {
				if (tmpSb.length() > 0) {
					myStack.add(tmpSb.toString());
					tmpSb.setLength(0);
				}
				myStack.add("" + s.charAt(i));
			}
		}

		if (tmpSb.length() > 0)
			myStack.add(tmpSb.toString());

		tmpSb.setLength(0);

		String top = "";
		String tmpRepeat = "";

		List<String> notDone = new ArrayList<String>();
		while (!myStack.isEmpty()) {
			top = myStack.pop();
			if ((top.charAt(0) >= 'a' && top.charAt(0) <= 'z') || (top.charAt(0) >= 'A' && top.charAt(0) <= 'Z')) {
				if (notDone.size() == 0) {
					resultStack.add(top);
				} else {
					tmpRepeat = top + tmpRepeat;
				}
			} else if (top.charAt(0) == ']') {
				if (tmpRepeat.length() > 0) {
					notDone.add(tmpRepeat);
					tmpRepeat = "";
				}
				notDone.add("]");

			} else if (top.charAt(0) == '[') {
				while (notDone.size() >= 0) {
					String notDoneTmp = notDone.get(notDone.size() - 1);
					notDone.remove(notDone.size() - 1);
					if (notDoneTmp.charAt(0) == ']')
						break;
					else
						tmpRepeat = tmpRepeat + notDoneTmp;
				}

			} else {
				int repeat = Integer.parseInt(top);
				for (int i = 0; i < repeat; i++) {
					tmpSb.append(tmpRepeat);
				}
				tmpRepeat = tmpSb.toString();
				tmpSb.setLength(0);
				if (notDone.size() == 0) {
					resultStack.add(tmpRepeat);
					tmpRepeat = "";
				}
			}
		}

		while (!resultStack.isEmpty()) {
			result.append(resultStack.pop());
		}

		return result.toString();
	}

	public int[][] candyCrush(int[][] board) {

		List<Integer> locationsToCrush = new ArrayList<Integer>();
		// calculate locations to erase
		CalculateLocations(board, locationsToCrush);

		while (locationsToCrush.size() > 0) {
			// erase
			while (locationsToCrush.size() > 0) {
				board[locationsToCrush.get(0)][locationsToCrush.get(1)] = 0;
				locationsToCrush.remove(0);
				locationsToCrush.remove(0);
			}

			// drop in board
			DropBoard(board);

			// re calculate locations
			CalculateLocations(board, locationsToCrush);
		}

		return board;
	}

	private void DropBoard(int[][] board) {
		for (int row = board.length - 1; row >= 0; row--) {
			for (int col = board[row].length - 1; col >= 0; col--) {
				if (board[row][col] == 0) {
					// search for first above not 0 value and replace
					int tmpRow = row - 1;
					while (tmpRow >= 0) {
						if (board[tmpRow][col] != 0) {
							board[row][col] = board[tmpRow][col];
							board[tmpRow][col] = 0;
							break;
						}
						tmpRow--;
					}
				}
			}
		}
	}

	private void CalculateLocations(int[][] board, List<Integer> locationsToCrush) {
		int length = 1;
		for (int row = 0; row < board.length; row++) {
			length = 1;
			for (int col = 1; col < board[row].length; col++) {
				if (board[row][col - 1] != 0 && board[row][col - 1] == board[row][col]) {
					length++;
					if (length == 3) {
						locationsToCrush.add(row);
						locationsToCrush.add(col - 2);
						locationsToCrush.add(row);
						locationsToCrush.add(col - 1);
						locationsToCrush.add(row);
						locationsToCrush.add(col);
					} else if (length > 3) {
						locationsToCrush.add(row);
						locationsToCrush.add(col);
					}
				} else {
					length = 1;
				}
			}
		}

		// int itRow = 1;
		int row = 1;
		int col = 0;
		while (board.length > 0 && col < board[0].length) {
			length = 1;
			row = 1;
			while (row < board.length) {
				if (board[row - 1][col] != 0 && board[row - 1][col] == board[row][col]) {
					length++;
					if (length == 3) {
						locationsToCrush.add(row - 2);
						locationsToCrush.add(col);
						locationsToCrush.add(row - 1);
						locationsToCrush.add(col);
						locationsToCrush.add(row);
						locationsToCrush.add(col);
					} else if (length > 3) {
						locationsToCrush.add(row);
						locationsToCrush.add(col);
					}
				} else {
					length = 1;
				}
				row++;
			}
			// itRow++;
			col++;
		}
	}

	public List<String> invalidTransactions(String[] transactions) {
		if (transactions == null || transactions.length == 0)
			return new ArrayList<String>();

		List<String> result = new ArrayList<String>();

		Map<Integer, String> transMap = new HashMap<Integer, String>();
		List<Transaction> transList = new ArrayList<Transaction>();

		for (int i = 0; i < transactions.length; i++) {
			String[] values = transactions[i].split(",");

			Transaction tmp = new Transaction(i, values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]),
					values[3]);
			transList.add(tmp);
			transMap.put(i, transactions[i]);
		}

		Collections.sort(transList);

		for (int i = 0; i < transList.size(); i++) {
			for (int j = i + 1; j < transactions.length; j++) {
				if (Math.abs(transList.get(i).time - transList.get(j).time) <= 60) {
					if (transList.get(i).name.equals(transList.get(j).name)
							&& !transList.get(i).city.equals(transList.get(j).city)) {

						if (!transList.get(i).isAdded) {
							result.add(transMap.get(transList.get(i).id));
							transList.get(i).isAdded = true;
						}

						if (!transList.get(j).isAdded) {
							result.add(transMap.get(transList.get(j).id));
							transList.get(j).isAdded = true;
						}

					}
				} else
					break;

			}

			if (!transList.get(i).isAdded) {
				if (transList.get(i).amount > 1000)
					result.add(transMap.get(transList.get(i).id));
			}
		}

		return result;
	}

	public Node flatten(Node head) {

		if (head == null)
			return null;

		List<Node> toComplete = new ArrayList<Node>();

		Node mover = head;

		while (mover != null || toComplete.size() > 0) {

			if (mover.child != null) {
				Node tmp = mover.next;
				if (tmp != null)
					toComplete.add(0, tmp);
				mover.next = mover.child;
				mover = mover.next;
			} else {
				if (mover.next == null) {
					if (toComplete.size() > 0) {
						mover.next = toComplete.get(0);
						toComplete.remove(0);
					}

				}

				mover = mover.next;
			}
		}

		mover = head;
		while (mover.next != null) {
			mover.next.prev = mover;
			mover = mover.next;
			mover.child = null;
		}

		head.child = null;

		return head;
	}

	public int twoCitySchedCost(int[][] costs) {

		Arrays.sort(costs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Math.abs(o1[0] - o1[1]) - (Math.abs(o2[0] - o2[1]));
			}
		});

		int n = costs.length / 2;
		int countA = 0;
		int countB = 0;
		int total = 0;

		for (int i = 0; i < costs.length; i++) {
			if (countA < n && countB < n) {
				if (costs[i][0] < costs[i][1]) {
					countA++;
					total = total + costs[i][0];
				} else {
					countB++;
					total = total + costs[i][1];
				}
			} else if (countA < n)
				total = total + costs[i][0];
			else
				total = total + costs[i][1];
		}

		return total;
	}

	public class Transaction implements Comparable<Transaction> {
		int id;
		String name;
		int time;
		int amount;
		String city;
		boolean isAdded = false;

		public Transaction(int id, String name, int time, int amount, String city) {
			this.id = id;
			this.name = name;
			this.time = time;
			this.amount = amount;
			this.city = city;
		}

		@Override
		public int compareTo(Transaction t) {
			// TODO Auto-generated method stub
			return this.time - t.time;
		}

	}

}
