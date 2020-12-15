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

	
    public int[] prisonAfterNDays(int[] cells, int N) {

        HashMap<String, Integer> seen = new HashMap<>();
        boolean isFastForwarded = false;

        // step 1). run the simulation with hashmap
        while (N > 0) {
            if (!isFastForwarded) {
                String s = this.cellsToBitmap2(cells);
                if (seen.containsKey(s)) {
                    // the length of the cycle is seen[state_key] - N 
                	// N mod one cycle
                    N %= seen.get(s) - N;
                    isFastForwarded = true;
                } else
                    seen.put(s, N);
            }
            // check if there is still some steps remained,
            // with or without the fast-forwarding.
            if (N > 0) {
                N -= 1;
                cells = this.nextDay(cells);
            }
        }
        return cells;
    }
    
    protected String cellsToBitmap2(int[] cells) {
        StringBuilder sb = new StringBuilder();
        for (int cell : cells) {
            sb.append(cell);
        }
        return sb.toString();
    }

    protected int[] nextDay(int[] cells) {
        int[] newCells = new int[cells.length];
        newCells[0] = 0;
        for (int i = 1; i < cells.length - 1; i++)
            newCells[i] = (cells[i - 1] == cells[i + 1]) ? 1 : 0;
        newCells[cells.length - 1] = 0;
        return newCells;
    }

	
	
	public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
		// create a logs list ["user", "time", "page"]
		List<List<String>> logs = new ArrayList<>();
		for (int i = 0; i < username.length; i++) {
			List<String> log = new ArrayList<>();
			log.add(username[i]);
			log.add(String.valueOf(timestamp[i]));
			log.add(website[i]);
			logs.add(log);
		}

		// sort logs list by the time, index 1 is time
		Collections.sort(logs, (l1, l2) -> Integer.valueOf(l1.get(1)) - Integer.valueOf(l2.get(1)));

		// create user -> List<page> map
		Map<String, List<String>> users = new HashMap<>();
		for (int i = 0; i < logs.size(); i++) {
			List<String> log = logs.get(i);
			List<String> pageList = users.getOrDefault(log.get(0), new ArrayList<>());
			pageList.add(log.get(2));
			users.put(log.get(0), pageList);
		}

		// create a 3-sequence -> count map
		Map<String, Integer> counts = new HashMap<>();
		int maxCount = 0;
		// keep tracking of the most frequent one
		String freqString = "";
		for (String user : users.keySet()) {
			Set<String> seqs = getAllSequence(users.get(user));
			// user visits less than 3 pages
			if (seqs == null)
				continue;
			for (String seq : seqs) {
				int count = counts.getOrDefault(seq, 0);
				counts.put(seq, ++count);
				if (count > maxCount) {
					freqString = seq;
					maxCount = count;
				} else if (count == maxCount) {
					// pick word with small lexical order
					freqString = seq.compareTo(freqString) < 0 ? seq : freqString;
				}
			}
		}
		return Arrays.asList(freqString.split(","));
	}

	// given a list of pages, return all the 3-sequence for a single user
	// use set to avoid duplicated 3-sequence for the same user
	// pick each page that comes after the previous one
	// join 3-pages by "," and add to the set
	// NOTE: Do not use Regex symbol such as ". * + ?"!!!!
	private Set<String> getAllSequence(List<String> list) {
		int len = list.size();
		// can not form a 3-sequence
		if (len < 3)
			return null;
		Set<String> set = new HashSet<>();
		for (int i = 0; i < len - 2; i++) {
			for (int j = i + 1; j < len - 1; j++) {
				for (int k = j + 1; k < len; k++) {
					StringBuilder sb = new StringBuilder(list.get(i));
					sb.append(',');
					sb.append(list.get(j));
					sb.append(',');
					sb.append(list.get(k));
					set.add(sb.toString());
				}
			}
		}
		return set;
	}

	public class PairStringInteger {
		String word;
		int level;

		public PairStringInteger(String word, int level) {
			this.word = word;
			this.level = level;
		}
	}

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {

		// Since all words are of same length.
		int L = beginWord.length();

		// Dictionary to hold combination of words that can be formed,
		// from any given word. By changing one letter at a time.
		Map<String, List<String>> allComboDict = new HashMap<>();

		wordList.forEach(word -> {
			for (int i = 0; i < L; i++) {
				// Key is the generic word
				// Value is a list of words which have the same intermediate generic word.
				String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
				List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
				transformations.add(word);
				allComboDict.put(newWord, transformations);
			}
		});

		// Queue for BFS
		Queue<PairStringInteger> Q = new LinkedList<PairStringInteger>();
		Q.add(new PairStringInteger(beginWord, 1));

		// Visited to make sure we don't repeat processing same word.
		Map<String, Boolean> visited = new HashMap<>();
		visited.put(beginWord, true);

		while (!Q.isEmpty()) {
			PairStringInteger node = Q.remove();
			String word = node.word;
			int level = node.level;
			for (int i = 0; i < L; i++) {

				// Intermediate words for current word
				String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

				// Next states are all the words which share the same intermediate state.
				for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
					// If at any point if we find what we are looking for
					// i.e. the end word - we can return with the answer.
					if (adjacentWord.equals(endWord)) {
						return level + 1;
					}
					// Otherwise, add it to the BFS Queue. Also mark it visited
					if (!visited.containsKey(adjacentWord)) {
						visited.put(adjacentWord, true);
						Q.add(new PairStringInteger(adjacentWord, level + 1));
					}
				}
			}
		}

		return 0;
	}

	public int maximalSquare(char[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return 0;

		int maxSquare = 0;

		int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];

		int rows = matrix.length;
		int cols = matrix[0].length;

		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= cols; j++) {
				if (matrix[i - 1][j - 1] == '1') {
					dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
					maxSquare = Math.max(maxSquare, dp[i][j]);
				}
			}
		}

		return maxSquare * maxSquare;
	}

	public int findCircleNum(int[][] M) {
		if (M == null || M.length == 0)
			return 0;

		int circleCount = 0;

		int[] visited = new int[M.length];

		for (int i = 0; i < M.length; i++) {
			if (visited[i] == 0) {
				dfsFriends(M, visited, i);
				circleCount++;
			}
		}

		return circleCount;
	}

	private void dfsFriends(int[][] M, int[] visited, int i) {
		for (int j = 0; j < M.length; j++) {
			if (M[i][j] == 1 && visited[j] == 0) {
				visited[j] = 1;
				dfsFriends(M, visited, j);
			}
		}
	}

	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

		List<List<Integer>> criticalConnectionsList = new ArrayList<List<Integer>>();

		if (connections == null || connections.size() == 0)
			return criticalConnectionsList;

		// construct a graph based on the nodes in the edges (connections)
		List<Integer>[] nodesConnectionsGraph = new ArrayList[n];

		for (int i = 0; i < connections.size(); i++) {
			if (nodesConnectionsGraph[connections.get(i).get(0)] == null)
				nodesConnectionsGraph[connections.get(i).get(0)] = new ArrayList<Integer>();

			if (nodesConnectionsGraph[connections.get(i).get(1)] == null)
				nodesConnectionsGraph[connections.get(i).get(1)] = new ArrayList<Integer>();

			nodesConnectionsGraph[connections.get(i).get(0)].add(connections.get(i).get(1));
			nodesConnectionsGraph[connections.get(i).get(1)].add(connections.get(i).get(0));

		}

		// populate timestamps array using tarjan's algorithm
		int[] timestamps = new int[n];
		helper(nodesConnectionsGraph, 0, 0, 1, timestamps, criticalConnectionsList);

		return criticalConnectionsList;
	}

	private int helper(List<Integer>[] nodesConnectionsGraph, int curr, int parent, int ts, int[] timestamps,
			List<List<Integer>> criticalConnectionsList) {
		timestamps[curr] = ts;
		for (int nextNode : nodesConnectionsGraph[curr]) {
			// The next node ignores parent node
			if (nextNode == parent)
				continue;

			// If next nodes have already been traversed, set the timestamp of
			// current node to minimum of all next nodes
			if (timestamps[nextNode] > 0)
				timestamps[curr] = Math.min(timestamps[curr], timestamps[nextNode]);
			else
				// else, set the timestamp of current node to minimium of all it's children.
				timestamps[curr] = Math.min(timestamps[curr],
						helper(nodesConnectionsGraph, nextNode, curr, ts + 1, timestamps, criticalConnectionsList));

			// As defined by Tarjan's algorithm, if the timestamp of the current node is
			// already
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

		if (time == null || time.length == 0)
			return 0;

		int[] mods = new int[61];

		int countOfPairs = 0;
		int mod = 0;
		for (int i = 0; i < time.length; i++) {
			mod = time[i] % 60;
			countOfPairs = countOfPairs + mods[60 - mod];
			if (mod == 0)
				mods[60]++;
			else
				mods[mod]++;
		}

		return countOfPairs;
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
