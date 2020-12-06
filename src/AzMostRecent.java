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
