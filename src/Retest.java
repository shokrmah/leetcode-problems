import java.util.*;
import java.util.Map.Entry;

public class Retest {

	public Node copyRandomList(Node head) {
		if (head == null)
			return null;

		Map<Integer, Node> myMap = new HashMap<Integer, Node>();
		Node newHead = copyNode(head);

		Node mover = head;

		Node moverNewHead = newHead;

		int index = 0;
		while (moverNewHead != null) {
			myMap.put(index, moverNewHead);
			index++;
			moverNewHead = moverNewHead.next;
		}

		moverNewHead = newHead;
		while (mover != null) {
			if (mover.random != null) {
				moverNewHead.random = myMap.get(mover.random.val);
			}

			mover = mover.next;
			moverNewHead = moverNewHead.next;
		}

		return newHead;
	}

	public Node copyNode(Node original) {
		if (original == null)
			return null;

		Node copy = new Node(original.val);
		copy.next = copyNode(original.next);

		return copy;
	}

	public boolean isSubtree(TreeNode s, TreeNode t) {
		return s != null && (equals(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t));
	}

	public boolean equals(TreeNode s, TreeNode t) {
		if (s == null && t == null)
			return true;
		if (s == null || t == null)
			return false;

		return s.val == t.val && equals(s.left, t.left) && equals(s.right, t.right);
	}

	public int orangesRotting(int[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;

		int timeNeeded = 0;

		Queue<int[]> rottenLocations = new LinkedList<int[]>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 2)
					rottenLocations.add(new int[] { i, j });
			}
		}

		int size = 0;
		int[] location;
		boolean isFresh = false;
		while (!rottenLocations.isEmpty()) {
			size = rottenLocations.size();
			isFresh = false;
			for (int i = 0; i < size; i++) {
				location = rottenLocations.poll();
				boolean check = checkNeighbours(grid, location[0], location[1], rottenLocations);
				if (check) {
					isFresh = true;
				}
			}
			if (isFresh)
				timeNeeded++;
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1)
					return -1;
			}
		}

		return timeNeeded;

	}

	private boolean checkNeighbours(int[][] grid, int i, int j, Queue<int[]> rottenLocations) {
		boolean isFresh = false;
		if (i - 1 >= 0 && grid[i - 1][j] == 1) {
			grid[i - 1][j] = 2;
			isFresh = true;
			rottenLocations.add(new int[] { i - 1, j });
		}

		if (j - 1 >= 0 && grid[i][j - 1] == 1) {
			grid[i][j - 1] = 2;
			isFresh = true;
			rottenLocations.add(new int[] { i, j - 1 });
		}

		if (i + 1 < grid.length && grid[i + 1][j] == 1) {
			grid[i + 1][j] = 2;
			isFresh = true;
			rottenLocations.add(new int[] { i + 1, j });
		}

		if (j + 1 < grid[i].length && grid[i][j + 1] == 1) {
			grid[i][j + 1] = 2;
			isFresh = true;
			rottenLocations.add(new int[] { i, j + 1 });
		}

		return isFresh;
	}

	public class PairInteger implements Comparable<PairInteger> {
		int value1;
		int value2;
		int distance;

		public PairInteger(int value1, int value2) {
			this.value1 = value1;
			this.value2 = value2;
			distance = (int) (Math.pow(value1, 2) + Math.pow(value2, 2));
		}

		@Override
		public int compareTo(PairInteger pi) {
			return pi.distance - this.distance;
		}
	}

	public int[][] kClosest(int[][] points, int K) {
		if (points == null || points.length == 0)
			return new int[][] { {} };

		int[][] kClosestPoints = new int[K][];

		PriorityQueue<PairInteger> heap = new PriorityQueue<PairInteger>();

		for (int i = 0; i < points.length; i++) {
			heap.add(new PairInteger(points[i][0], points[i][1]));
			if (heap.size() > K)
				heap.poll();
		}

		int index = 0;
		while (!heap.isEmpty()) {
			kClosestPoints[index] = new int[2];
			kClosestPoints[index][0] = heap.peek().value1;
			kClosestPoints[index][1] = heap.poll().value2;
			index++;
		}

		return kClosestPoints;
	}

	public String mostCommonWord(String paragraph, String[] banned) {
		if (paragraph == null || paragraph.length() == 0)
			return "";

		String mostCommon = "";

		Set<String> bannedSet = new HashSet<String>();

		for (String s : banned) {
			bannedSet.add(s);
		}

		StringBuilder sb = new StringBuilder();
		int maxCount = 0;

		char c;
		paragraph = paragraph.toLowerCase() + " ";
		String word;

		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		int count;
		for (int i = 0; i < paragraph.length(); i++) {
			c = paragraph.charAt(i);
			if (c >= 'a' && c <= 'z') {
				sb.append(c);
			} else {
				if (sb.length() > 0) {
					word = sb.toString();
					if (!bannedSet.contains(word)) {
						wordCount.putIfAbsent(word, 0);
						count = wordCount.get(word) + 1;
						wordCount.put(word, count);
						if (count > maxCount) {
							maxCount = count;
							mostCommon = word;
						}
					}
					sb.setLength(0);
				}

			}
		}

		return mostCommon;

	}

	public class StringIntPair implements Comparable<StringIntPair> {
		String word;
		int count;

		public StringIntPair(String word, int count) {
			this.word = word;
			this.count = count;
		}

		@Override
		public int compareTo(StringIntPair sip) {
			int compare = Integer.compare(this.count, sip.count);
			if (compare == 0)
				compare = sip.word.compareTo(this.word);

			return compare;
		}

	}

	public List<String> topKFrequent(String[] words, int k) {
		if (words == null || words.length == 0)
			return new ArrayList<String>();

		List<String> topWords = new ArrayList<String>();

		Map<String, StringIntPair> counts = new HashMap<String, StringIntPair>();
		for (int i = 0; i < words.length; i++) {
			counts.putIfAbsent(words[i], new StringIntPair(words[i], 0));
			counts.get(words[i]).count++;
		}

		PriorityQueue<StringIntPair> heap = new PriorityQueue<StringIntPair>();

		for (Map.Entry<String, StringIntPair> keyPair : counts.entrySet()) {
			heap.add(keyPair.getValue());
			if (heap.size() > k)
				heap.poll();
		}

		while (!heap.isEmpty()) {
			topWords.add(0, heap.poll().word);
		}

		return topWords;
	}

	public class StringPair implements Comparable<StringPair> {

		String identifier;
		String letterLog;
		int index;

		public StringPair(String identifier, String letterLog, int index) {
			this.identifier = identifier;
			this.letterLog = letterLog;
			this.index = index;
		}

		@Override
		public int compareTo(StringPair sp) {
			int compare = this.letterLog.compareTo(sp.letterLog);
			if (compare == 0)
				compare = this.identifier.compareTo(sp.identifier);

			return compare;
		}

	}

	public String[] reorderLogFiles(String[] logs) {
		if (logs == null || logs.length == 0)
			return new String[] {};

		String[] result = new String[logs.length];

		List<Integer> digitLogs = new ArrayList<Integer>();
		PriorityQueue<StringPair> letterLogs = new PriorityQueue<StringPair>();

		String[] split;
		for (int i = 0; i < logs.length; i++) {
			split = logs[i].split(" ", 2);
			if (split[1].charAt(0) >= '0' && split[1].charAt(0) <= '9') {
				digitLogs.add(i);
			} else {
				StringPair sp = new StringPair(split[0], split[1], i);
				letterLogs.add(sp);
			}
		}

		int index = 0;
		while (!letterLogs.isEmpty()) {
			result[index] = logs[letterLogs.poll().index];
			index++;
		}

		for (int i = 0; i < digitLogs.size(); i++) {
			result[index] = logs[digitLogs.get(i)];
			index++;
		}

		return result;
	}

	public List<Integer> partitionLabels(String S) {
		if (S == null || S.length() == 0)
			return null;

		List<Integer> result = new ArrayList<Integer>();

		int[] counts = new int[26];
		for (int i = 0; i < S.length(); i++) {
			counts[S.charAt(i) - 'a']++;
		}

		Set<Character> isSeen = new HashSet<Character>();

		char c;
		int numberOfChars = 0;
		for (int i = 0; i < S.length(); i++) {
			numberOfChars++;
			c = S.charAt(i);
			isSeen.add(c);
			counts[c - 'a']--;
			if (counts[c - 'a'] == 0) {
				isSeen.remove(c);
				if (isSeen.size() == 0) {
					result.add(numberOfChars);
					numberOfChars = 0;
				}

			}
		}

		return result;

	}

}
