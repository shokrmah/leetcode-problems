import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class OtherProblems {

	public String mostCommonWord2(String paragraph, String[] banned) {

		String result = "";
		int max = -1;
		
		Map<String, Integer> counts = new HashMap<String, Integer>();
		Set<String> bannedSet = new HashSet<String>();
		for (String bannedWord : banned) {
			bannedSet.add(bannedWord);
		}

		int count = 0;
		StringBuilder sb = new StringBuilder();
		String word = "";
		for (int i = 0; i < paragraph.length(); i++) {
			if ((paragraph.charAt(i) >= 'a' && paragraph.charAt(i) <= 'z')
					|| (paragraph.charAt(i) >= 'A' && paragraph.charAt(i) <= 'Z')) {
				sb.append(paragraph.charAt(i));
			} else {
				word = sb.toString().toLowerCase();
				sb.setLength(0);
				if (!bannedSet.contains(word)) {
					counts.putIfAbsent(word, 0);
					count = counts.get(word) + 1;
					counts.put(word, count);
					if(count > max) {
						result = word;
						max = count;
					}
					
				}
			}
		}

		if (sb.length() > 0) {
			word = sb.toString().toLowerCase();
			sb.setLength(0);
			if (word.length() > 0 && !bannedSet.contains(word)) {
				counts.putIfAbsent(word, 0);
				count = counts.get(word) + 1;
				counts.put(word, count);
				if(count > max) {
					result = word;
					max = count;
				}
			}
		}


		return result;
	}

	public String mostCommonWord(String paragraph, String[] banned) {
		paragraph = paragraph.replaceAll("[\\.,!\\?;:\\+\\-\\*\\{\\}\\[\\]\\(\\)]\\'", " ");

		Map<String, Integer> counts = new HashMap<String, Integer>();
		Set<String> bannedSet = new HashSet<String>();
		for (String bannedWord : banned) {
			bannedSet.add(bannedWord);
		}

		String words[] = paragraph.split(" ");
		for (String word : words) {
			word = word.trim().toLowerCase();
			if (word.length() > 0 && !bannedSet.contains(word)) {
				counts.putIfAbsent(word, 0);
				counts.put(word, counts.get(word) + 1);
			}

		}

		String result = "";
		int max = -1;
		for (Map.Entry<String, Integer> entry : counts.entrySet()) {
			if (entry.getValue() > max) {
				result = entry.getKey();
				max = entry.getValue();
			}
		}

		return result;
	}

	public int orangesRotting(int[][] grid) {
		int mintues = 0;
		int newRottenValue = 2;
		boolean isChanged = false;
		do {

			isChanged = iterateOranges(grid, newRottenValue);
			if (isChanged)
				mintues++;
			newRottenValue++;

		} while (isChanged);

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					return -1;
				}
			}
		}

		return mintues;
	}

	public boolean iterateOranges(int[][] grid, int newRottenValue) {
		boolean change = false;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == newRottenValue) {
					boolean isChanged = updateNeighbours(grid, i, j, newRottenValue);
					if (isChanged)
						change = true;
				}
			}
		}
		return change;
	}

	public boolean updateNeighbours(int[][] grid, int i, int j, int newRottenValue) {
		boolean isChanged = false;
		if (i + 1 < grid.length && grid[i + 1][j] == 1) {
			grid[i + 1][j] = newRottenValue + 1;
			isChanged = true;
		}
		if (i - 1 >= 0 && grid[i - 1][j] == 1) {
			grid[i - 1][j] = newRottenValue + 1;
			isChanged = true;
		}

		if (j + 1 < grid[i].length && grid[i][j + 1] == 1) {
			grid[i][j + 1] = newRottenValue + 1;
			isChanged = true;
		}

		if (j - 1 >= 0 && grid[i][j - 1] == 1) {
			grid[i][j - 1] = newRottenValue + 1;
			isChanged = true;
		}

		return isChanged;
	}

	static String isBalanced(String s) {

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

		if (myStack.size() > 0)
			return "NO";
		return "YES";

	}

	public class ValueCount implements Comparator<ValueCount> {

		int val;
		int count;

		public ValueCount(int val, int count) {
			this.val = val;
			this.count = count;
		}

		@Override
		public int compare(ValueCount o1, ValueCount o2) {
			// TODO Auto-generated method stub
			if (o1.count == o2.count)
				return o1.count - o2.count;
			else
				return o1.val - o2.val;
		}

	}

	public int getKth(int lo, int hi, int k) {

		Map<Integer, Integer> countOfStepsMap = new HashMap<Integer, Integer>();

		PriorityQueue<ValueCount> queue = new PriorityQueue<ValueCount>();

		int count = 0;
		for (int i = lo; i <= hi; i++) {
			count = stepsCount(i, countOfStepsMap);
			countOfStepsMap.put(i, count);
			queue.add(new ValueCount(i, count));
		}

		return 0;
	}

	public int stepsCount(int val, Map<Integer, Integer> countOfStepsMap) {
		if (val == 1)
			return 1;

		if (countOfStepsMap.containsKey(val))
			return countOfStepsMap.get(val);

		if (val % 2 == 0)
			val = val / 2;
		else
			val = (val * 3) + 1;

		return countOfStepsMap.put(val, stepsCount(val, countOfStepsMap) + 1);

	}

	public int countServers(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;

		// int[] countConnectedServers = new int[1];

		int countConnectedServers = 0;
		// int tmpCount = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					// tmpCount = countConnectedServers[0];
					countConnectedServers = countConnectedServers + SearchConnected(grid, i, j);
//					if (countConnectedServers[0] - tmpCount == 1)
//						countConnectedServers[0]--;
				}
			}
		}

		return countConnectedServers;
	}

	public int SearchConnected(int[][] grid, int i, int j) {
		int count = 0;
		int tmpI = i;
		while (i < grid.length) {
			if (grid[i][j] == 1) {
				grid[i][j] = 2;
				count++;
			}
			i++;
		}

		i = tmpI;
		if (count == 1) {
			count = 0;
		} else {
			j = j + 1;
		}
		while (j < grid[i].length) {
			if (grid[i][j] == 1) {
				grid[i][j] = 2;
				count++;
			}
			j++;
		}

		if (count == 1) {
			count = 0;
		}

		return count;
	}

	public Node cloneGraph(Node node) {

		if (node == null)
			return null;

		Map<Integer, Node> myMap = new HashMap<Integer, Node>();

		Queue<Node> myQueue = new LinkedList<Node>();

		myQueue.add(node);

		Node head = new Node(node.val);
		myMap.put(node.val, head);
		Node tmp = null;

		Node current = null;

		while (!myQueue.isEmpty()) {
			tmp = myQueue.poll();
			if (myMap.containsKey(tmp.val)) {
				current = myMap.get(tmp.val);
			} else {
				Node n = new Node(tmp.val);
				current = n;
				myMap.put(tmp.val, n);
			}

			for (int i = 0; i < tmp.neighbors.size(); i++) {
				if (myMap.containsKey(tmp.neighbors.get(i).val)) {
					current.neighbors.add(myMap.get(tmp.neighbors.get(i).val));
				} else {
					Node newNode = new Node(tmp.neighbors.get(i).val);
					myQueue.add(tmp.neighbors.get(i));
					myMap.put(tmp.neighbors.get(i).val, newNode);
					current.neighbors.add(newNode);
				}
			}

		}

		return head;

	}

	public int eraseOverlapIntervals(int[][] intervals) {
		if (intervals == null || intervals.length <= 1)
			return 0;

		Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

		int minRemovals = 0;

		int compareStart = intervals[0][0];
		int compareEnd = intervals[0][1];

		for (int i = 1; i < intervals.length; i++) {
			if (isIntersected(compareStart, compareEnd, intervals[i][0], intervals[i][1])) {
				minRemovals++;
				if (intervals[i][1] < compareEnd) {
					compareStart = intervals[i][0];
					compareEnd = intervals[i][1];
				}
			} else {
				compareStart = intervals[i][0];
				compareEnd = intervals[i][1];
			}
		}

		return minRemovals;
	}

	public boolean isIntersected(int start1, int end1, int start2, int end2) {
		if ((start2 >= start1 && end2 <= end1) || (start2 <= start1 && end2 >= end1) || (start2 < end1)) {
			return true;
		}

		return false;
	}

	public int minCostClimbingStairs(int[] cost) {
		if (cost == null || cost.length <= 1)
			return 0;

		int f1 = 0, f2 = 0, f0 = 0;
		for (int i = cost.length - 1; i >= 0; --i) {
			f0 = cost[i] + Math.min(f1, f2);
			f2 = f1;
			f1 = f0;
		}
		return Math.min(f1, f2);

	}

}
