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

	public int furthestBuilding(int[] heights, int bricks, int ladders) {
		 PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
	        for(int i = 0; i < heights.length - 1; i++){
	            int diff = heights[i + 1] - heights[i];
	            if(diff > 0) {                      // Current building's height is less than the next building's height.
	                queue.add(diff);                // Use ladder and add the difference.
	                if(queue.size() > ladders) {    // If you ran out of ladders!
	                    bricks -= queue.poll();     // Remove the smallest difference and use bricks instead
	                    if(bricks < 0) {            
	                        return i;               // Ops you ran out of the bricks and you stop here!
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
