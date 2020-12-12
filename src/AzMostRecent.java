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

public class AzMostRecent {

    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
    	List<List<Integer>> criticalConnectionsList = new ArrayList<List<Integer>>();
    	
    	if(connections == null || connections.size() == 0)
    		return criticalConnectionsList;
    	
    	// construct a graph based on the nodes in the edges (connections)
    	List<Integer>[] nodesConnectionsGraph = new ArrayList[n];
        
        for (int i = 0; i < connections.size(); i++) {
        	if(nodesConnectionsGraph[connections.get(i).get(0)] == null)
        		nodesConnectionsGraph[connections.get(i).get(0)] = new ArrayList<Integer>();
        	
        	if(nodesConnectionsGraph[connections.get(i).get(1)] == null)
        		nodesConnectionsGraph[connections.get(i).get(1)] = new ArrayList<Integer>();
        	
        	nodesConnectionsGraph[connections.get(i).get(0)].add(connections.get(i).get(1));
        	nodesConnectionsGraph[connections.get(i).get(1)].add(connections.get(i).get(0));
        	
		}
        
        
        // populate timestamps array using tarjan's algorithm
        int[] timestamps = new int[n];
        helper(nodesConnectionsGraph, 0, 0, 1, timestamps, criticalConnectionsList);
        
        return criticalConnectionsList;
    }
    
    private int helper(List<Integer>[] nodesConnectionsGraph, int curr, int parent, int ts, int[] timestamps, List<List<Integer>> criticalConnectionsList) {
        timestamps[curr] = ts;
        for (int nextNode: nodesConnectionsGraph[curr]) {
            // The next node ignores parent node
            if (nextNode == parent) continue;
            
            // If next nodes have already been traversed, set the timestamp of
            // current node to minimum of all next nodes
            if (timestamps[nextNode] > 0)
                timestamps[curr] = Math.min(timestamps[curr], timestamps[nextNode]);
            else
                // else, set the timestamp of current node to minimium of all it's children.
                timestamps[curr] = Math.min(timestamps[curr], helper(nodesConnectionsGraph, nextNode, curr, ts + 1, timestamps, criticalConnectionsList));
            
            // As defined by Tarjan's algorithm, if the timestamp of the current node is already
            // smaller than that of it's next node (child), then the edge connecting the
            // current and next nodes make up a critical connection.
            if (ts < timestamps[nextNode])
            	criticalConnectionsList.add(Arrays.asList(curr, nextNode));
        }
        
        return timestamps[curr];
    }
	
	
	public int minDifficulty(int[] jobDifficulty, int d) {

//		 Let's use a function to represent our target value, say, F(i, j) means the minimum difficulty on i-th day who takes j-th work as its end.
//
//		 However, as we fix the date and the last work, we cannot tell which work to start with in a specific day. To make it clear, the problem is we can start from any of the work on i-th day, but which one is the best?
//
//		 Let's make a further step. On i-th day, the minimum index of work we can take is i, because there should be at least one job done on each previous day, when we reaches i-th day, there should be at least i-1 works finished in total.
//		 So if we let k to be the start work, the range of k should be [i, j]. And each (k, j) pair means -- at i-th day, we start from k-th work and ends at j-th work. The difficulty will depend on the one with highest difficulty within the range. And we choose the k who creates minimum difficulty.
//
//		 At last, in order to start at k-th work on i-th day, we should finish (k-1)-th work at (i-1)-th day. Then we figure out the functional equation
//		 
		int n = jobDifficulty.length;
		if (d > n)
			return -1;
		int[][] F = new int[d + 1][n + 1];
		for (int i = 1; i <= n; i++)
			F[1][i] = Math.max(F[1][i - 1], jobDifficulty[i - 1]);
		for (int i = 2; i <= d; i++) {
			for (int j = i; j <= n; j++) {
				F[i][j] = Integer.MAX_VALUE;
				int currMax = 0;
				for (int k = j; k >= i; k--) {
					currMax = Math.max(currMax, jobDifficulty[k - 1]);
					F[i][j] = Math.min(F[i][j], F[i - 1][k - 1] + currMax);
				}
			}
		}
		return F[d][n];
	}

	public int minCostToMoveChips(int[] position) {
		if (position == null || position.length == 0)
			return 0;

		int evenCount = 0;
		int oddCount = 0;

		for (int i = 0; i < position.length; i++) {
			if (position[i] % 2 == 0)
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

	
	public int numPairsDivisibleBy60(int[] time) {
		
		if(time == null || time.length == 0)
			return 0;
		
		int[] mods = new int[61];
		
		int countOfPairs = 0;
		int mod = 0;
		for (int i = 0; i < time.length; i++) {
			mod = time[i] % 60;
			countOfPairs = countOfPairs + mods[60 - mod];
			if(mod == 0)
				mods[60]++;
			else
				mods[mod]++;
		}
		
		return countOfPairs;
	}

	
	public int maximalSquare(char[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return 0;

		int maxSquare = 0;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == '1') {
					
				}
			}
		}

		return maxSquare;
	}
	
	

	public int calPoints(String[] ops) {
		if (ops == null || ops.length == 0)
			return 0;

		Stack<Integer> values = new Stack<Integer>();

		for (int i = 0; i < ops.length; i++) {
			if (ops[i].equals("C")) {
				values.pop();
			} else if (ops[i].equals("D")) {
				values.push(values.peek() * 2);
			} else if (ops[i].equals("+")) {
				int tmp = values.pop();
				int newVal = tmp + values.peek();
				values.push(tmp);
				values.push(newVal);
			} else
				values.push(Integer.parseInt(ops[i]));
		}

		int result = 0;
		while (!values.isEmpty()) {
			result = result + values.pop();
		}

		return result;

	}

	public boolean isSubtree(TreeNode s, TreeNode t) {
		return traverse(s, t);
	}

	public boolean equals(TreeNode s, TreeNode t) {
		if (s == null && t == null)
			return true;
		if (s == null || t == null)
			return false;

		return s.val == t.val && equals(s.left, t.left) && equals(s.right, t.right);
	}

	public boolean traverse(TreeNode s, TreeNode t) {
		return s != null && (equals(s, t) || traverse(s.left, t) || traverse(s.right, t));
	}

	public String mostCommonWord(String paragraph, String[] banned) {
		if (paragraph == null || paragraph.length() == 0)
			return "";

		paragraph = paragraph + " ";
		Set<String> bannedWords = new HashSet<String>();
		for (int i = 0; i < banned.length; i++) {
			bannedWords.add(banned[i]);
		}

		Map<String, Integer> counts = new HashMap<String, Integer>();
		int maxCount = 0;
		int count = 0;

		StringBuilder sb = new StringBuilder();
		char c;
		String word;
		String mostFreqWord = "";

		for (int i = 0; i < paragraph.length(); i++) {
			c = paragraph.charAt(i);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				sb.append(c);
			} else {
				if (sb.length() > 0) {
					word = sb.toString().toLowerCase();
					sb.setLength(0);
					if (!bannedWords.contains(word)) {
						counts.putIfAbsent(word, 0);
						count = counts.get(word) + 1;
						counts.put(word, count);
						if (count > maxCount) {
							maxCount = count;
							mostFreqWord = word;
						}
					}
				}

			}
		}

		return mostFreqWord;

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
				compare = psi.word.compareTo(this.word);

			return compare;
		}

	}

	public List<String> topKFrequent(String[] words, int k) {
		if (words == null || words.length == 0)
			return new ArrayList<String>();

		List<String> mostFrequent = new ArrayList<String>();
		Map<String, PairStringInt> counts = new HashMap<String, PairStringInt>();
		for (int i = 0; i < words.length; i++) {
			counts.putIfAbsent(words[i], new PairStringInt(words[i], 0));
			counts.get(words[i]).count++;
		}

		PriorityQueue<PairStringInt> heap = new PriorityQueue<PairStringInt>();

		for (Map.Entry<String, PairStringInt> entry : counts.entrySet()) {
			heap.add(entry.getValue());
			if (heap.size() > k)
				heap.poll();
		}

		while (!heap.isEmpty()) {
			mostFrequent.add(0, heap.poll().word);
		}

		return mostFrequent;
	}

	public int numIslands(char[][] grid) {
		int numOfIslands = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '1') {
					numOfIslands++;
					dfsIsland(grid, i, j);
				}
			}
		}

		return numOfIslands;
	}

	private void dfsIsland(char[][] grid, int i, int j) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length)
			return;

		if (grid[i][j] == '1') {
			grid[i][j] = '2';
			dfsIsland(grid, i + 1, j);
			dfsIsland(grid, i - 1, j);
			dfsIsland(grid, i, j + 1);
			dfsIsland(grid, i, j - 1);
		}
	}

	public List<Integer> partitionLabels(String S) {
		List<Integer> values = new ArrayList<Integer>();
		if (S == null || S.length() == 0)
			return values;

		int[] counts = new int[26];

		for (int i = 0; i < S.length(); i++) {
			counts[S.charAt(i) - 'a']++;
		}

		Set<Character> isZero = new HashSet<Character>();

		int length = 0;
		char c = 'a';
		for (int i = 0; i < S.length(); i++) {
			c = S.charAt(i);
			length++;
			if (counts[c - 'a'] > 1) {
				isZero.add(c);
			} else {
				isZero.remove(c);
				if (isZero.isEmpty()) {
					values.add(length);
					length = 0;
				}

			}

			counts[c - 'a']--;
		}

		return values;
	}

	public String[] reorderLogFiles(String[] logs) {
		String[] reorderList = new String[logs.length];

		if (logs == null || logs.length <= 1)
			return reorderList;

		List<String> digitsLogs = new ArrayList<String>();

		PriorityQueue<PairString> wordsLogs = new PriorityQueue<PairString>();

		String compare = "";
		for (int i = 0; i < logs.length; i++) {
			compare = logs[i].substring(logs[i].indexOf(' ') + 1);
			if (compare.charAt(0) >= '0' && compare.charAt(0) <= '9')
				digitsLogs.add(logs[i]);
			else {
				wordsLogs.add(new PairString(logs[i], compare));
			}
		}

		int i = 0;
		while (!wordsLogs.isEmpty()) {
			reorderList[i] = wordsLogs.poll().first;
			i++;
		}
		for (int j = 0; j < digitsLogs.size(); j++) {
			reorderList[i] = digitsLogs.get(j);
			i++;
		}

		return reorderList;
	}

}
