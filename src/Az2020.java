import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;

public class Az2020 {

	public int minCostToMoveChips(int[] position) {
		if (position == null || position.length == 0)
			return 0;

		int evenCount = 0;
		int oddCount = 0;
		
		for (int i = 0; i < position.length; i++) {
			if(position[i] % 2 == 0)
				evenCount++;
			else
				oddCount++;
		}

		return Math.min(evenCount, oddCount);
	}

	public ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0)
			return null;

		ListNode top = new ListNode();

		int[] valueCount = new int[2];

		ListNode tmp = top;

		while (compareLists(lists, valueCount)) {
			for (int i = 0; i < valueCount[1]; i++) {
				tmp.next = new ListNode(valueCount[0]);
				tmp = tmp.next;
			}
		}

		return top.next;

	}

	private boolean compareLists(ListNode[] lists, int[] valueCount) {
		int smallestValue = Integer.MAX_VALUE;
		List<Integer> indexes = new ArrayList<Integer>();
		int finished = 0;
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null) {
				if (lists[i].val < smallestValue) {
					indexes.clear();
					smallestValue = lists[i].val;
					indexes.add(i);
				} else if (lists[i].val == smallestValue)
					indexes.add(i);
			} else {
				finished++;
			}
		}

		for (int i = 0; i < indexes.size(); i++) {
			lists[indexes.get(i)] = lists[indexes.get(i)].next;
		}

		if (finished == lists.length)
			return false;

		valueCount[0] = smallestValue;
		valueCount[1] = indexes.size();

		return true;
	}

	public boolean hasPathSum(TreeNode root, int sum) {

		return checkPathSum(root, 0, sum);
	}

	private boolean checkPathSum(TreeNode node, int sum, int targetSum) {
		if (node == null)
			return false;

		sum = sum + node.val;

		if (sum == targetSum && node.left == null && node.right == null)
			return true;
		return checkPathSum(node.left, sum, targetSum) || checkPathSum(node.right, sum, targetSum);

	}

	public List<String> binaryTreePaths(TreeNode root) {
		List<List<Integer>> myLists = new ArrayList<List<Integer>>();

		if (root == null)
			return new ArrayList<String>();
		myLists.add(new ArrayList<Integer>());
		calculatePaths(root, myLists, 0);

		StringBuilder sb = new StringBuilder();
		List<String> result = new ArrayList<String>();
		for (int i = myLists.size() - 1; i >= 1; i--) {
			for (int j = 0; j < myLists.get(i).size(); j++) {
				sb.append(myLists.get(i).get(j));
				if (j < myLists.get(i).size() - 1)
					sb.append("->");
			}

			result.add(sb.toString());
			sb.setLength(0);
		}

		return result;
	}

	private void calculatePaths(TreeNode node, List<List<Integer>> myLists, int listIndex) {
		myLists.get(listIndex).add(node.val);

		if (node.left != null)
			calculatePaths(node.left, myLists, listIndex);

		if (node.right != null)
			calculatePaths(node.right, myLists, listIndex);

		if (node.left == null && node.right == null) {
			copyList(myLists, listIndex);
			// listIndex++;
		}

		myLists.get(listIndex).remove(myLists.get(listIndex).size() - 1);

	}

	private void copyList(List<List<Integer>> myLists, int index) {
		myLists.add(new ArrayList<Integer>());
		int index2 = myLists.size() - 1;
		for (int i = 0; i < myLists.get(index).size(); i++) {
			myLists.get(index2).add(myLists.get(index).get(i));
		}
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {

		if (p == null && q == null)
			return true;

		if (p == null || q == null)
			return false;

		if (p.val != q.val)
			return false;

		else
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

	}

	public int getImportanceDFS(List<Employee> employees, int id) {
		if (employees == null || employees.size() == 0)
			return 0;

		int totalImportance = 0;

		Stack<Employee> dfsStack = new Stack<Employee>();

		Map<Integer, Employee> myMap = new HashMap<Integer, Employee>();
		for (int i = 0; i < employees.size(); i++) {
			myMap.put(employees.get(i).id, employees.get(i));
		}

		dfsStack.push(myMap.get(id));

		Employee tmpEmployee;

		while (!dfsStack.isEmpty()) {
			tmpEmployee = dfsStack.pop();
			totalImportance = totalImportance + tmpEmployee.importance;

			for (int i = 0; i < tmpEmployee.subordinates.size(); i++) {
				dfsStack.push(myMap.get(tmpEmployee.subordinates.get(i)));
			}

		}

		return totalImportance;
	}

	public boolean leafSimilar(TreeNode root1, TreeNode root2) {

		List<Integer> list1 = leaves(root1);
		List<Integer> list2 = leaves(root2);

		if (list1.size() != list2.size())
			return false;

		for (int i = 0; i < list1.size(); i++) {

			if (list1.get(i) != list2.get(i))
				return false;
		}
		return true;
	}

	private List<Integer> leaves(TreeNode root) {
		List<Integer> myList = new ArrayList<Integer>();

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.add(root);

		TreeNode tmpNode = null;

		while (!stack.isEmpty()) {
			tmpNode = stack.pop();

			if (tmpNode.left == null && tmpNode.right == null)
				myList.add(tmpNode.val);

			else {

				if (tmpNode.right != null) {
					stack.add(tmpNode.right);
				}

				if (tmpNode.left != null) {
					stack.add(tmpNode.left);
				}
			}
		}

		return myList;
	}

	public TreeNode increasingBST(TreeNode root) {
		if (root == null)
			return null;

		Queue<TreeNode> myQueue = new LinkedList<TreeNode>();

		addToQueue(root, myQueue);

		TreeNode newRoot = new TreeNode(myQueue.poll().val);

		TreeNode tmpNode = newRoot;
		while (!myQueue.isEmpty()) {
			tmpNode.right = new TreeNode(myQueue.poll().val);
			tmpNode = tmpNode.right;
		}

		return newRoot;

	}

	private void addToQueue(TreeNode root, Queue<TreeNode> myQueue) {
		if (root.left != null)
			addToQueue(root.left, myQueue);

		System.out.println(root.val);
		myQueue.add(root);

		if (root.right != null)
			addToQueue(root.right, myQueue);
	}

	public int rangeSumBST(TreeNode root, int low, int high) {
		if (root == null)
			return 0;

		if (root.val > low && root.val <= high)
			return root.val + rangeSumBST(root.right, low, high) + rangeSumBST(root.left, low, high);

		if (root.val <= low)
			return rangeSumBST(root.right, low, high);

		return rangeSumBST(root.left, low, high);
	}

//	private int sumRange(TreeNode root, int low, int high) {
//		if (root == null)
//			return 0;
//
//		if (root.val > low && root.val <= high)
//			return root.val + sumRange(root.right, low, high) + sumRange(root.left, low, high);
//		
//		if(root.val <= low)
//			return sumRange(root.right, low, high);
//		
//		return sumRange(root.left, low, high);
//
//	}

	public int networkDelayTime(int[][] times, int N, int K) {
		if (times == null || times.length == 0)
			return 0;

		int totalTime = 0;

		Map<Integer, Map<Integer, Integer>> timeMap = new HashMap<Integer, Map<Integer, Integer>>();
		for (int i = 0; i < times.length; i++) {
			timeMap.putIfAbsent(times[i][0], new HashMap<Integer, Integer>());
			timeMap.get(times[i][0]).put(times[i][1], times[i][2]);
		}

		Map<Integer, Integer> visitingTime = new HashMap<Integer, Integer>();

		Queue<Integer> bfsQueue = new LinkedList<Integer>();
		bfsQueue.add(K);
		visitingTime.put(K, 0);

		int tmpValue = -1;
		int size = 0;
		int currentTime = 0;
		int tmpTime = 0;

		int cycleTime = 0;
		while (!bfsQueue.isEmpty()) {
			size = bfsQueue.size();
			cycleTime = 0;
			for (int i = 0; i < size; i++) {
				tmpValue = bfsQueue.poll();
				currentTime = visitingTime.get(tmpValue);

				if (timeMap.containsKey(tmpValue))
					for (Map.Entry<Integer, Integer> entry : timeMap.get(tmpValue).entrySet()) {
						tmpTime = entry.getValue();
						if (visitingTime.containsKey(entry.getKey())) {
							// totalTime = totalTime - visitingTime.get(entry.getKey());
							tmpTime = Math.min(totalTime + tmpTime, visitingTime.get(entry.getKey()));
							visitingTime.put(entry.getKey(), tmpTime);

						} else {

							visitingTime.put(entry.getKey(), tmpTime);
							bfsQueue.add(entry.getKey());

						}
						cycleTime = Math.max(cycleTime, tmpTime);
					}

			}

			totalTime = totalTime + cycleTime;
		}

		if (visitingTime.size() < N)
			return -1;

		return totalTime;
	}

	public String reorganizeString(String S) {
		if (S == null || S.length() == 0)
			return "";

		StringBuilder sb = new StringBuilder();

		int[] counts = new int[26];
		for (int i = 0; i < S.length(); i++) {
			counts[S.charAt(i) - 'a']++;
		}

		Queue<PairCharInt> pq = new PriorityQueue<PairCharInt>();
		int maxCount = (S.length() + 1) / 2;
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] == 0)
				continue;
			if (counts[i] > maxCount)
				return "";

			int c = 'a' + i;
			pq.add(new PairCharInt((char) c, counts[i]));
		}

		PairCharInt c1, c2;
		while (pq.size() > 1) {
			c1 = pq.poll();
			c2 = pq.poll();

			sb.append(c1.c);
			sb.append(c2.c);
			c1.count--;
			c2.count--;
			if (c1.count > 0)
				pq.add(c1);

			if (c2.count > 0)
				pq.add(c2);
		}

		if (pq.size() > 0) {
			c1 = pq.poll();
			if (c1.count > 1)
				return "";

			sb.append(c1.c);
		}

		return sb.toString();

	}

	public class PairStringInt implements Comparable<PairStringInt> {
		String word;
		int count;

		public PairStringInt(String word, int count) {
			this.word = word;
			this.count = count;
		}

		@Override
		public int compareTo(PairStringInt psi) {
			int compare = this.count - psi.count;
			if (compare == 0)
				compare = this.word.compareTo(psi.word);

			return compare;
		}

	}

	public List<String> topKFrequent(String[] words, int k) {
		List<String> topWords = new ArrayList<String>();

		if (words == null || words.length == 0 || k <= 0)
			return topWords;

		Map<String, PairStringInt> wordsCounts = new HashMap<String, PairStringInt>();

		for (int i = 0; i < words.length; i++) {
			wordsCounts.putIfAbsent(words[i], new PairStringInt(words[i], 0));
			wordsCounts.get(words[i]).count++;
		}

		Queue<PairStringInt> pq = new PriorityQueue<PairStringInt>();

		for (Map.Entry<String, PairStringInt> entry : wordsCounts.entrySet()) {
			pq.add(entry.getValue());
			if (pq.size() > k)
				pq.poll();
		}

		while (!pq.isEmpty()) {
			topWords.add(pq.poll().word);
		}

		return topWords;
	}

	public int furthestBuilding(int[] heights, int bricks, int ladders) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (int i = 0; i < heights.length - 1; i++) {
			int diff = heights[i + 1] - heights[i];
			if (diff > 0) { // Current building's height is less than the next building's height.
				queue.add(diff); // Use ladder and add the difference.
				if (queue.size() > ladders) { // If you ran out of ladders!
					bricks -= queue.poll(); // Remove the smallest difference and use bricks instead
					if (bricks < 0) {
						return i; // Ops you ran out of the bricks and you stop here!
					}
				}
			}
		}
		return heights.length - 1;
	}

	public int kthSmallest(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0)
			return -1;

		Queue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				pq.add(matrix[i][j]);
				if (pq.size() > k)
					pq.poll();
			}
		}

		return pq.poll();
	}

	public int findKthLargest(int[] nums, int k) {
		if (nums == null || nums.length == 0 || nums.length - k < 0)
			return -1;

		Queue<Integer> pq = new PriorityQueue<Integer>();

		for (int i = 0; i < nums.length; i++) {
			pq.add(nums[i]);
			if (pq.size() > k)
				pq.poll();
		}

		return pq.poll();
	}

	public class PairInt implements Comparable<PairInt> {
		int value;
		int count;

		public PairInt(int value, int count) {
			this.value = value;
			this.count = count;
		}

		@Override
		public int compareTo(PairInt pi) {
			return this.count - pi.count;
		}

	}

	public int[] topKFrequent(int[] nums, int k) {

		if (nums == null)
			return new int[] {};

		Map<Integer, PairInt> counts = new HashMap<Integer, PairInt>();

		for (int i = 0; i < nums.length; i++) {
			counts.putIfAbsent(nums[i], new PairInt(nums[i], 0));
			counts.get(nums[i]).count++;
		}

		Queue<PairInt> pq = new PriorityQueue<PairInt>();
		for (Map.Entry<Integer, PairInt> entry : counts.entrySet()) {
			pq.add(entry.getValue());
			if (pq.size() > k)
				pq.poll();
		}

		int[] topKElements = new int[k];

		int i = 0;
		while (!pq.isEmpty()) {
			topKElements[i++] = pq.poll().value;
		}

		return topKElements;
	}

	public class PairCharInt implements Comparable<PairCharInt> {
		public char c;
		public int count;

		public PairCharInt(char c, int count) {
			this.c = c;
			this.count = count;
		}

		@Override
		public int compareTo(PairCharInt pci) {
			return pci.count - count;
		}

	}

	public String frequencySort(String s) {
		if (s == null || s.length() == 0)
			return "";

		PairCharInt[] counts = new PairCharInt[256];

		// Map<Character, PairCharInt> counts = new HashMap<Character, PairCharInt>();
		for (int i = 0; i < s.length(); i++) {
			// counts.putIfAbsent(s.charAt(i), new PairCharInt(s.charAt(i), 0));

			// counts.get(s.charAt(i)).count++;
			if (counts[s.charAt(i)] == null) {
				counts[s.charAt(i)] = new PairCharInt(s.charAt(i), 0);
			}
			counts[s.charAt(i)].count++;
		}

		PriorityQueue<PairCharInt> pq = new PriorityQueue<PairCharInt>();

//		for (Map.Entry<Character, PairCharInt> entry : counts.entrySet()) {
//			pq.add(entry.getValue());
//		}

		for (int i = 0; i < counts.length; i++) {
			if (counts[i] != null)
				pq.add(counts[i]);
		}

		StringBuilder sb = new StringBuilder();

		PairCharInt pc = null;
		while (!pq.isEmpty()) {
			pc = pq.poll();
			for (int i = 0; i < pc.count; i++) {
				sb.append(pc.c);
			}

		}

		return sb.toString();
	}

	public class PairValue implements Comparable<PairValue> {
		int[] pair;
		double distanceValue;

		public PairValue(int[] pair, double distance) {
			this.pair = pair;
			this.distanceValue = distance;
		}

		@Override
		public int compareTo(PairValue pv) {

			return Double.compare(pv.distanceValue, distanceValue);
		}

	}

	public int[][] kClosest(int[][] points, int K) {
		int[][] resultArray = new int[K][];
		if (points == null || points.length == 0)
			return resultArray;

		Queue<PairValue> pq = new PriorityQueue<PairValue>();
		for (int i = 0; i < points.length; i++) {
			PairValue pv = new PairValue(points[i], getDistance(points[i][0], points[i][1]));
			pq.add(pv);

			if (pq.size() > K)
				pq.poll();
		}

		K--;
		while (K >= 0) {
			resultArray[K] = pq.poll().pair;
			K--;
		}

		return resultArray;
	}

	private double getDistance(int v1, int v2) {
		return Math.sqrt(Math.pow(v1, 2) + Math.pow(v2, 2));
	}

	public int lastStoneWeight(int[] stones) {
		if (stones == null || stones.length == 0)
			return 0;

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());

		for (int i = 0; i < stones.length; i++) {
			pq.offer(stones[i]);
		}

		int w1 = -1;
		int w2 = -1;
		while (!pq.isEmpty()) {
			w1 = pq.poll();
			if (pq.size() > 0) {
				w2 = pq.poll();
				w1 = w1 - w2;
				if (w1 > 0)
					pq.add(w1);
			} else
				return w1;
		}

		return 0;
	}

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> rightSideList = new ArrayList<Integer>();
		if (root == null)
			return rightSideList;

		Queue<TreeNode> bfsQueue = new LinkedList<TreeNode>();
		bfsQueue.add(root);

		int size = 0;
		TreeNode tmpNode = null;

		while (!bfsQueue.isEmpty()) {
			size = bfsQueue.size();
			rightSideList.add(bfsQueue.peek().val);
			for (int i = 0; i < size; i++) {
				tmpNode = bfsQueue.poll();

				if (tmpNode.right != null)
					bfsQueue.add(tmpNode.right);

				if (tmpNode.left != null)
					bfsQueue.add(tmpNode.left);

			}
		}

		return rightSideList;
	}

	public boolean canReach(int[] arr, int start) {
		if (arr == null || arr.length == 0 || start >= arr.length)
			return false;

		Set<Integer> indexVisited = new HashSet<Integer>();

		Queue<Integer> bfsQueue = new LinkedList<Integer>();
		bfsQueue.add(start);

		int size = 0;

		int tmpIndex = -1;
		while (!bfsQueue.isEmpty()) {
			size = bfsQueue.size();

			for (int i = 0; i < size; i++) {
				tmpIndex = bfsQueue.poll();
				if (arr[tmpIndex] == 0)
					return true;

				indexVisited.add(tmpIndex);

				if (tmpIndex + arr[tmpIndex] < arr.length && !indexVisited.contains(tmpIndex + arr[tmpIndex])) {
					bfsQueue.add(tmpIndex + arr[tmpIndex]);
				}

				if (tmpIndex - arr[tmpIndex] >= 0 && !indexVisited.contains(tmpIndex - arr[tmpIndex])) {
					bfsQueue.add(tmpIndex - arr[tmpIndex]);
				}

			}
		}

		return false;
	}

	public List<Integer> largestValues(TreeNode root) {
		List<Integer> maxValues = new ArrayList<Integer>();

		if (root == null)
			return maxValues;

		Queue<TreeNode> bfsQueue = new LinkedList<TreeNode>();
		bfsQueue.add(root);

		int size = 0;
		int localMax = Integer.MIN_VALUE;
		TreeNode tmpNode = null;

		while (!bfsQueue.isEmpty()) {
			size = bfsQueue.size();

			for (int i = 0; i < size; i++) {
				tmpNode = bfsQueue.poll();
				if (tmpNode.val > localMax)
					localMax = tmpNode.val;
				if (tmpNode.left != null)
					bfsQueue.add(tmpNode.left);

				if (tmpNode.right != null)
					bfsQueue.add(tmpNode.right);
			}

			maxValues.add(localMax);
			localMax = Integer.MIN_VALUE;
		}

		return maxValues;
	}

	public int findBottomLeftValue(TreeNode root) {
		if (root == null)
			return -1;

		int leftMostValue = 0;

		Queue<TreeNode> bfsQueue = new LinkedList<TreeNode>();
		bfsQueue.add(root);

		int size = 0;
		TreeNode tmpNode = null;

		while (!bfsQueue.isEmpty()) {
			size = bfsQueue.size();

			leftMostValue = bfsQueue.peek().val;
			for (int i = 0; i < size; i++) {
				tmpNode = bfsQueue.poll();
				if (tmpNode.left != null)
					bfsQueue.add(tmpNode.left);

				if (tmpNode.right != null)
					bfsQueue.add(tmpNode.right);
			}
		}

		return leftMostValue;

	}

	public List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> resultLevel = new ArrayList<List<Integer>>();
		if (root == null)
			return resultLevel;

		Queue<Node> bfsQueue = new LinkedList<Node>();
		bfsQueue.add(root);

		Node tmpNode = null;
		int size = 0;

		List<Integer> tmpList;
		while (!bfsQueue.isEmpty()) {
			size = bfsQueue.size();

			tmpList = new ArrayList<Integer>();
			for (int i = 0; i < size; i++) {
				tmpNode = bfsQueue.poll();
				tmpList.add(tmpNode.val);
				if (tmpNode.children != null)
					for (int j = 0; j < tmpNode.children.size(); j++) {
						bfsQueue.add(tmpNode.children.get(j));
					}
			}

			resultLevel.add(tmpList);
		}

		return resultLevel;
	}

	public int maxLevelSum(TreeNode root) {
		if (root == null)
			return -1;

		Queue<TreeNode> bfsQueue = new LinkedList<TreeNode>();
		bfsQueue.add(root);
		int size = 0;
		int maxSum = Integer.MIN_VALUE;
		int maxLevel = -1;

		int currentSum = 0;
		int currentLevel = 0;
		TreeNode tmpNode = null;
		while (!bfsQueue.isEmpty()) {
			size = bfsQueue.size();
			currentLevel++;
			currentSum = 0;
			for (int i = 0; i < size; i++) {
				tmpNode = bfsQueue.poll();

				currentSum = currentSum + tmpNode.val;

				if (tmpNode.left != null)
					bfsQueue.add(tmpNode.left);

				if (tmpNode.right != null)
					bfsQueue.add(tmpNode.right);
			}

			if (currentSum > maxSum) {
				maxSum = currentSum;
				maxLevel = currentLevel;
			}

		}

		return maxLevel;

	}

	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;

		return isSymmetricValue(root.left, root.right);
	}

	private boolean isSymmetricValue(TreeNode left, TreeNode right) {

		if (left != null && right != null) {
			if (left.val != right.val)
				return false;

			return isSymmetricValue(left.left, right.right) && isSymmetricValue(left.right, right.left);
		}

		if (left == null && right == null)
			return true;

		return false;
	}

	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;

		int minDepth = 0;
		Queue<TreeNode> bfsQueue = new LinkedList<TreeNode>();
		bfsQueue.add(root);

		int size = 0;
		TreeNode tmpNode = null;

		while (!bfsQueue.isEmpty()) {
			size = bfsQueue.size();
			minDepth++;

			for (int i = 0; i < size; i++) {
				tmpNode = bfsQueue.poll();
				if (tmpNode.left == null && tmpNode.right == null)
					return minDepth;

				if (tmpNode.left != null)
					bfsQueue.add(tmpNode.left);

				if (tmpNode.right != null)
					bfsQueue.add(tmpNode.right);
			}
		}

		return minDepth;
	}

	public boolean isCousins(TreeNode root, int x, int y) {
		if (root == null)
			return false;

		int xParent = -1;
		int yParent = -1;
		int xDepth = -1;
		int yDepth = -1;

		int currentDepth = 0;
		Queue<TreeNode> bfsQueue = new LinkedList<TreeNode>();

		bfsQueue.add(root);
		int size = 0;
		TreeNode tmpTreeNode = null;

		int check = -1;
		while (!bfsQueue.isEmpty()) {
			size = bfsQueue.size();
			currentDepth++;
			for (int i = 0; i < size; i++) {
				tmpTreeNode = bfsQueue.poll();
				if (tmpTreeNode.left != null) {
					bfsQueue.add(tmpTreeNode.left);
					if (tmpTreeNode.left.val == x) {
						xParent = tmpTreeNode.val;
						xDepth = currentDepth;
						check = checkCousins(xDepth, yDepth, xParent, yParent);
					}

					if (tmpTreeNode.left.val == y) {
						yParent = tmpTreeNode.val;
						yDepth = currentDepth;
						check = checkCousins(xDepth, yDepth, xParent, yParent);
					}

					if (check == 0) {
						return false;
					} else if (check == 1)
						return true;
				}

				if (tmpTreeNode.right != null) {
					bfsQueue.add(tmpTreeNode.right);
					if (tmpTreeNode.right.val == x) {
						xParent = tmpTreeNode.val;
						xDepth = currentDepth;
						check = checkCousins(xDepth, yDepth, xParent, yParent);
					}

					if (tmpTreeNode.right.val == y) {
						yParent = tmpTreeNode.val;
						yDepth = currentDepth;
						check = checkCousins(xDepth, yDepth, xParent, yParent);
					}

					if (check == 0) {
						return false;
					} else if (check == 1)
						return true;
				}

			}
			// check if one negative and other not return false;
			if ((xDepth == -1 && yDepth != -1) || (xDepth != -1 && yDepth == -1))
				return false;
		}

		return false;
	}

	private int checkCousins(int xDepth, int yDepth, int xParent, int yParent) {
		if (xDepth == -1 || yDepth == -1)
			return -1;

		if (xDepth == yDepth && xParent != yParent)
			return 1;

		return 0;

	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		if (root == null)
			return resultList;

		Queue<TreeNode> bfsQueue = new LinkedList<TreeNode>();
		bfsQueue.add(root);
		int size = 0;
		TreeNode tmpTreeNode = null;
		List<Integer> tmpList = null;

		// Stack<List<Integer>> stack = new Stack<List<Integer>>();

		while (!bfsQueue.isEmpty()) {
			size = bfsQueue.size();
			tmpList = new ArrayList<Integer>();

			for (int i = 0; i < size; i++) {
				tmpTreeNode = bfsQueue.poll();
				tmpList.add(tmpTreeNode.val);
				if (tmpTreeNode.left != null) {
					bfsQueue.add(tmpTreeNode.left);
				}

				if (tmpTreeNode.right != null) {
					bfsQueue.add(tmpTreeNode.right);
				}
			}

			resultList.add(0, tmpList);
			// stack.add(tmpList);
		}

//		while(!stack.isEmpty()) {
//			resultList.add(stack.pop());
//		}

		return resultList;
	}

	public int getImportance(List<Employee> employees, int id) {
		if (employees == null || employees.size() == 0)
			return 0;

		int totalImportance = 0;

		Queue<Employee> bfsQueue = new LinkedList<Employee>();

		Map<Integer, Employee> myMap = new HashMap<Integer, Employee>();
		for (int i = 0; i < employees.size(); i++) {
			myMap.put(employees.get(i).id, employees.get(i));
		}

		bfsQueue.add(myMap.get(id));

		int size = 0;
		Employee tmpEmployee;

		while (!bfsQueue.isEmpty()) {
			size = bfsQueue.size();

			for (int i = 0; i < size; i++) {
				tmpEmployee = bfsQueue.poll();
				totalImportance = totalImportance + tmpEmployee.importance;
				for (int j = 0; j < tmpEmployee.subordinates.size(); j++) {
					bfsQueue.add(myMap.get(tmpEmployee.subordinates.get(j)));
				}
			}

		}

		return totalImportance;
	}

	public int maxDepth(Node root) {
		if (root == null)
			return 0;

		int maxDepth = 0;

		Queue<Node> bfsQueue = new LinkedList<Node>();

		bfsQueue.add(root);

		Node tmp;
		int size = 0;
		while (!bfsQueue.isEmpty()) {
			size = bfsQueue.size();

			for (int i = 0; i < size; i++) {
				tmp = bfsQueue.poll();
				if (tmp.children != null) {
					for (int j = 0; j < tmp.children.size(); j++) {
						bfsQueue.add(tmp.children.get(j));
					}
				}

			}
			maxDepth++;
		}

		return maxDepth;

	}
}
