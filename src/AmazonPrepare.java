import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class AmazonPrepare {
	public int isWinner(List<List<String>> codeList, List<String> order) {
		if (codeList == null || codeList.size() == 0)
			return 1;

		int listOneIndex = 0;
		int listTwoIndex = 0;

		String startKey = codeList.get(0).get(0);
		Queue<Integer> locationsOfStartKey = new LinkedList<Integer>();
		for (int i = 0; i < order.size(); i++) {
			if (order.get(i).equals(startKey)) {
				locationsOfStartKey.add(i);
			}
			if (!locationsOfStartKey.isEmpty()) {
				int k = locationsOfStartKey.poll();

				listOneIndex = 0;
				listTwoIndex = 0;
				while (k < order.size()) {
					if (listTwoIndex == codeList.get(listOneIndex).size()) {
						listOneIndex++;
						listTwoIndex = 0;
					}
					if (listOneIndex < codeList.size()) {
						//inject here that I can add to queue
						
						if (codeList.get(listOneIndex).get(listTwoIndex) == order.get(k)
								|| codeList.get(listOneIndex).get(listTwoIndex).equals("anything")) {
							k++;
							listTwoIndex++;
							if(k == order.size() && listOneIndex == codeList.size() - 1 && listTwoIndex == codeList.get(listOneIndex).size())
								return 1;
						}
						else 
							break;

					}
					else 
						return 1;
				}
			}

		}

		return 0;

	}

	public List<String> largestItemAssociation(List<PairString> itemAssociation) {

		if (itemAssociation == null || itemAssociation.size() == 0)
			return new ArrayList<String>();

		// create map of items and their mappings
		Map<String, Set<String>> items = new HashMap<String, Set<String>>();
		for (int i = 0; i < itemAssociation.size(); i++) {
			items.putIfAbsent(itemAssociation.get(i).first, new HashSet<String>());
			items.putIfAbsent(itemAssociation.get(i).second, new HashSet<String>());
			items.get(itemAssociation.get(i).first).add(itemAssociation.get(i).second);
		}

		PriorityQueue<List<String>> myHeap = new PriorityQueue<List<String>>(
				(s1, s2) -> (s1.size() != s2.size() ? s1.size() - s2.size() : s2.get(0).compareTo(s1.get(0))));

		for (Map.Entry<String, Set<String>> entry : items.entrySet()) {
			Queue<String> myQueue = new LinkedList<String>();
			myQueue.add(entry.getKey());
			Set<String> tmpSetResult = new HashSet<String>();
			tmpSetResult.add(entry.getKey());

			while (!myQueue.isEmpty()) {
				String stringItem = myQueue.poll();
				for (String item : items.get(stringItem)) {
					if (!tmpSetResult.contains(item))
						myQueue.add(item);
					tmpSetResult.add(item);
				}
			}

			myHeap.add(new ArrayList<String>(tmpSetResult));
			if (myHeap.size() > 1) {
				myHeap.poll();
			}
		}

		List<String> r = myHeap.poll();
		Collections.sort(r);

		return r;

	}

	public int orangesRotting(int[][] grid) {

		if (grid == null || grid.length == 0)
			return 0;

		Queue<int[]> myQueue = new LinkedList<int[]>();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 2) {
					myQueue.add(new int[] { i, j });
				}
			}
		}

		int timeNeeded = 0;
		int queueLength = 0;
		boolean isChanged = false;
		while (!myQueue.isEmpty()) {
			queueLength = myQueue.size();
			isChanged = false;
			for (int i = 0; i < queueLength; i++) {
				int[] currentIndeces = myQueue.poll();
				boolean tmpChanged = updateNeighbours(grid, currentIndeces, myQueue);

				if (tmpChanged)
					isChanged = true;
			}
			if (isChanged)
				timeNeeded++;
			else
				break;
		}

		// loop to check if there is no fresh oranges
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					return -1;
				}
			}
		}

		return timeNeeded;
	}

	public boolean updateNeighbours(int[][] grid, int[] indeces, Queue<int[]> myQueue) {
		boolean isChanged = false;
		// check 4 directions and add to queue new rotted oranges

		if (indeces[0] + 1 < grid.length && grid[indeces[0] + 1][indeces[1]] == 1) {
			grid[indeces[0] + 1][indeces[1]] = 2;
			myQueue.add(new int[] { indeces[0] + 1, indeces[1] });
			isChanged = true;
		}

		if (indeces[0] - 1 >= 0 && grid[indeces[0] - 1][indeces[1]] == 1) {
			grid[indeces[0] - 1][indeces[1]] = 2;
			myQueue.add(new int[] { indeces[0] - 1, indeces[1] });
			isChanged = true;
		}

		if (indeces[1] + 1 < grid[indeces[0]].length && grid[indeces[0]][indeces[1] + 1] == 1) {
			grid[indeces[0]][indeces[1] + 1] = 2;
			myQueue.add(new int[] { indeces[0], indeces[1] + 1 });
			isChanged = true;
		}

		if (indeces[1] - 1 >= 0 && grid[indeces[0]][indeces[1] - 1] == 1) {
			grid[indeces[0]][indeces[1] - 1] = 2;
			myQueue.add(new int[] { indeces[0], indeces[1] - 1 });
			isChanged = true;
		}

		return isChanged;

	}

	static double[] runningMedian(int[] a) {
		/*
		 * Write your code here.
		 */

		if (a == null || a.length == 0)
			return new double[0];

		double[] result = new double[a.length];
		List<Integer> allValues = new ArrayList<Integer>();

		allValues.add(a[0]);
		result[0] = a[0];
		for (int i = 1; i < a.length; i++) {
			binaryInsert(allValues, a[i], 0, allValues.size());
			result[i] = getMedian(allValues);
		}

		return result;
	}

	public static void binaryInsert(List<Integer> allValues, int value, int start, int end) {
		int middle = (start + end) / 2;
		if (value == allValues.get(middle)) {
			allValues.add(middle, value);
			return;
		} else if (value > allValues.get(middle)) {
			if (middle == allValues.size() - 1) {
				allValues.add(value);
				return;
			} else if (value <= allValues.get(middle + 1)) {
				allValues.add(middle + 1, value);
				return;
			} else {
				binaryInsert(allValues, value, middle + 1, end);
			}

		} else {
			if (middle == 0) {
				allValues.add(0, value);
				return;
			} else if (value >= allValues.get(middle - 1)) {
				allValues.add(middle, value);
				return;
			} else {
				binaryInsert(allValues, value, start, middle - 1);
			}
		}
	}

	public static double getMedian(List<Integer> allValues) {

		if (allValues.size() % 2 == 0) {
			double median = allValues.get(allValues.size() / 2);
			median += allValues.get(allValues.size() / 2 - 1);
			return median / 2.0;
		} else {
			return allValues.get(allValues.size() / 2);
		}
	}

	public static class WordsTrie {
		// true if word completed
		public boolean isWord;
		public Map<Character, WordsTrie> children;
		public int count;

		public WordsTrie() {
			this.isWord = false;
			this.children = new HashMap<Character, WordsTrie>();
			// this.children = new WordsTrie();
			count = 0;
		}

	}

	public void addToTrie(WordsTrie myTrie, String word) {
		WordsTrie currentLevel = myTrie;

		char c;
		for (int i = 0; i < word.length(); i++) {
			c = word.charAt(i);
			if (currentLevel.children.containsKey(c)) {
				currentLevel = currentLevel.children.get(c);
			} else {
				currentLevel.children.put(c, new WordsTrie());
				currentLevel = currentLevel.children.get(c);
			}
			currentLevel.count++;
		}

		currentLevel.isWord = true;
	}

	public int SearchTrieAndReturnCount(WordsTrie myTrie, String word) {
		WordsTrie currentLevel = myTrie;

		char c;
		for (int i = 0; i < word.length(); i++) {
			c = word.charAt(i);
			if (currentLevel.children.containsKey(c)) {
				currentLevel = currentLevel.children.get(c);
			} else {
				currentLevel.children.put(c, new WordsTrie());
				currentLevel = currentLevel.children.get(c);
			}

		}

		return currentLevel.count;
	}

	public int[] contacts(String[][] queries) {

		if (queries == null)
			return new int[0];

		WordsTrie myTrie = new WordsTrie();

		List<Integer> result = new ArrayList<Integer>();
		int count = 0;
		for (int i = 0; i < queries.length; i++) {
			if (queries[i][0].equals("add"))
				addToTrie(myTrie, queries[i][1]); // insert to trie
			else {
				// search trie
				count = SearchTrieAndReturnCount(myTrie, queries[i][1]);
				// print count
				// System.out.println(count);
				result.add(count);
			}

		}

		int[] resultArray = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			resultArray[i] = result.get(i);
		}

		return resultArray;

	}

	public String isBalanced(String s) {
		if (s == null || s.length() % 2 != 0)
			return "NO";

		Stack<Character> myStack = new Stack<Character>();

		char c;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (c == '(' || c == '{' || c == '[') {
				myStack.add(s.charAt(i));
			} else {
				if (myStack.size() > 0) {
					char tmp = myStack.pop();
					if ((c == ')' && tmp == '(') || (c == ']' && tmp == '[') || (c == '}' && tmp == '{'))
						continue;
				}
				return "NO";
			}

		}

		return "YES";
	}

	public void levelOrder(Node root) {

		if (root == null)
			return;

		Queue<Node> myQueue = new LinkedList<Node>();
		myQueue.add(root);

		int length = 0;
		Node tmp = null;
		while (!myQueue.isEmpty()) {

			length = myQueue.size();

			for (int i = 0; i < length; i++) {
				tmp = myQueue.poll();
				System.out.print(tmp.data + " ");
				if (tmp.left != null)
					myQueue.add(tmp.left);
				if (tmp.right != null)
					myQueue.add(tmp.right);
			}
		}

	}

	public int height(Node root) {
		// Write your code here.
		if (root == null)
			return -1;

		int treeHeight = -1;

		Queue<Node> myQueue = new LinkedList<Node>();
		myQueue.add(root);

		int length = 0;
		Node tmp = null;
		while (!myQueue.isEmpty()) {
			treeHeight++;
			length = myQueue.size();

			for (int i = 0; i < length; i++) {
				tmp = myQueue.poll();
				if (tmp.left != null)
					myQueue.add(tmp.left);
				if (tmp.right != null)
					myQueue.add(tmp.right);
			}
		}

		return treeHeight;

	}

}
