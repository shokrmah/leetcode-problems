import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Problems {

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid.length == 0 && obstacleGrid[0].length == 0)
			return 1;
		if (obstacleGrid[0][0] == 1 || obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1)
			return 0;

//		List<Integer> iObstaclesIndexes = new ArrayList<Integer>();
//		List<Integer> jObstaclesIndexes = new ArrayList<Integer>();
//
//		for (int i = 0; i < obstacleGrid.length; i++) {
//			for (int j = 0; j < obstacleGrid[i].length; j++) {
//				if (obstacleGrid[i][j] == 1) {
//					iObstaclesIndexes.add(i);
//					iObstaclesIndexes.add(j);
//				}
//
//			}
//		}

		Set<String> visited = new HashSet<String>();

		return countOfPaths(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1, visited);
	}

	public int countOfPaths(int[][] obstacleGrid, int i, int j, Set<String> visited) {
		if (obstacleGrid[i][j] == 1)
			return 0;
		else if (i == 0 && j == 0)
			return 1;

		if (i > 0 && j > 0) {
			if (obstacleGrid[i - 1][j] == 0 && obstacleGrid[i][j - 1] == 0 && obstacleGrid[i - 1][j - 1] == 0) {
				visited.add("" + (i - 1) + "" + (j - 1));

				if (visited.contains("" + (i - 1) + "" + (j))) {
					return countOfPaths(obstacleGrid, i, j - 1, visited)
							+ countOfPaths(obstacleGrid, i - 1, j - 1, visited) * 2;
				} else if (visited.contains("" + (i) + "" + (j - 1))) {
					return countOfPaths(obstacleGrid, i - 1, j, visited)
							+ countOfPaths(obstacleGrid, i - 1, j - 1, visited) * 2;
				} else
					return countOfPaths(obstacleGrid, i - 1, j, visited) + countOfPaths(obstacleGrid, i, j - 1, visited)
							+ countOfPaths(obstacleGrid, i - 1, j - 1, visited) * 2;
			}

			else {
				if (visited.contains("" + (i - 1) + "" + (j))) {
					return countOfPaths(obstacleGrid, i, j - 1, visited);
				} else if (visited.contains("" + (i) + "" + (j - 1)))
					return countOfPaths(obstacleGrid, i - 1, j, visited);
				else
					return countOfPaths(obstacleGrid, i, j - 1, visited)
							+ countOfPaths(obstacleGrid, i - 1, j, visited);
			}

		}

		else if (i > 0) {
			for (int k = i; k >= 0; k--)
				if (obstacleGrid[k][j] == 1)
					return 0;
			return 1;
		} else {
			for (int k = j; k >= 0; k--)
				if (obstacleGrid[i][k] == 1)
					return 0;
			return 1;
		}

	}

	public int uniquePaths(int m, int n) {

		// accepted solution 3
		if (m == 1 || n == 1)
			return 1;
		else if (m == 2)
			return n;
		else if (n == 2)
			return m;

		int[] values = new int[m];
		Arrays.fill(values, 1);

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				values[j] = values[j] + values[j - 1];
			}
		}

		return values[m - 1];

//		// accepted solution 2
//		if (m == 1 || n == 1)
//			return 1;
//		else if (m == 2)
//			return n;
//		else if (n == 2)
//			return m;
//
//		int[][] matrix = new int[m + 1][n + 1];
//		for (int i = 1; i < matrix[0].length; i++) {
//			matrix[2][i] = i;
//		}
//
//		for (int i = 1; i < matrix.length; i++) {
//			matrix[i][2] = i;
//		}
//
//		for (int i = 3; i < matrix.length; i++) {
//			for (int j = 3; j < matrix[i].length; j++) {
//				matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
//			}
//		}
//
//		return matrix[m][n];

		// accepted solution 1
//		if (m == 1 || n == 1)
//			return 1;
//		else if (m == 2)
//			return n;
//		else if (n == 2)
//			return m;
//		
//		else
//			return uniquePaths(m, n - 1) + uniquePaths(m - 1, n);
	}

	public List<List<Integer>> groupThePeople(int[] groupSizes) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		ArrayList<Integer>[] counts = new ArrayList[501];

		for (int i = 0; i < groupSizes.length; i++) {
			if (counts[groupSizes[i]] == null)
				counts[groupSizes[i]] = new ArrayList<Integer>();
			counts[groupSizes[i]].add(groupSizes[i]);
		}

		int diff = 0;
		for (int i = 1; i < counts.length; i++) {
			if (counts[i] != null && counts[i].size() >= i) {
				diff = counts[i].size() - (counts[i].size() % i);
				if (diff > 0) {
					List<Integer> tmp = new ArrayList<Integer>();
					int stopper = 0;
					for (int j = 0; j < diff; j++) {
						stopper++;
						tmp.add(counts[i].get(j));
						if (stopper == i) {
							result.add(tmp);
							tmp = new ArrayList<Integer>();
							stopper = 0;
						}

					}
					// result.add(tmp);
				}

			}
		}

		return result;
	}

	public boolean checkPossibility(int[] nums) {

		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] > nums[i]) {
				int[] copy = new int[nums.length];
				System.arraycopy(nums, 0, copy, 0, nums.length);
				copy[i - 1] = nums[i];
				nums[i] = nums[i - 1];
				int startIndex = i - 1;
				if (startIndex > 1)
					startIndex--;
				else
					startIndex = 1;

				if (checkArray(nums, startIndex) || checkArray(copy, startIndex))
					return true;
				else
					return false;
			}
		}

		return true;
	}

	public boolean checkArray(int[] nums, int startindex) {
		for (int i = startindex; i < nums.length; i++) {
			if (nums[i - 1] > nums[i]) {
				return false;
			}
		}
		return true;
	}

	public int[] numberOfLines(int[] widths, String S) {
		int[] result = new int[2];
		int lastLineWeight = 0;
		int noOfLines = 1;

		for (int i = 0; i < S.length(); i++) {
			lastLineWeight = lastLineWeight + widths[S.charAt(i) - 'a'];
			if (lastLineWeight > 100) {
				lastLineWeight = widths[S.charAt(i) - 'a'];
				noOfLines++;
			} else if (lastLineWeight == 100) {
				if (i < S.length() - 1) {
					lastLineWeight = 0;
					noOfLines++;
				}

			}
		}

		result[0] = noOfLines;
		result[1] = lastLineWeight;
		return result;
	}

	public String dayOfTheWeek(int day, int month, int year) {
		String dateString = String.format("%d-%d-%d", year, month, day);
		Date date = null;

		try {
			date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
		} catch (Exception e) {
			e.getMessage();
		}

		return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);

	}

	public boolean leafSimilar(TreeNode root1, TreeNode root2) {
		List<Integer> values1 = new ArrayList<Integer>();
		List<Integer> values2 = new ArrayList<Integer>();
		getLeafs(root1, values1);
		getLeafs(root2, values2);

		if (values1.size() != values2.size())
			return false;

		for (int i = 0; i < values1.size(); i++) {
			if (values1.get(i) != values2.get(i))
				return false;
		}

		return true;

	}

	public void getLeafs(TreeNode root, List<Integer> values) {

		if (root.left == null && root.right == null)
			values.add(root.val);

		if (root.left != null)
			getLeafs(root.left, values);

		if (root.right != null)
			getLeafs(root.right, values);

	}

	public String[] findOcurrences(String text, String first, String second) {
		if (text.length() == 1)
			return new String[] {};

		List<String> words = new ArrayList<String>();

		int lengthOfSearch = first.length() + second.length() + 1;
		int index = text.indexOf(first + " " + second);
		String checkWord = "";
		while (index != -1) {
			if (index + lengthOfSearch + 1 >= text.length())
				break;
			else if (index > 0) {
				if (text.charAt(index - 1) != ' ') {
					index = text.indexOf(first + " " + second, index + 2);
				}
			}
			int endIndexNewWord = text.indexOf(' ', index + lengthOfSearch + 1);

			if (endIndexNewWord == -1) {
				words.add(text.substring(index + lengthOfSearch + 1, text.length()));
				break;
			} else {
				checkWord = text.substring(index + lengthOfSearch + 1, endIndexNewWord);
				words.add(checkWord);
			}

			if (checkWord.equals(first)) {
				index = text.indexOf(first + " " + second, index + lengthOfSearch);
			} else
				index = text.indexOf(first + " " + second, endIndexNewWord);

		}

		if (words.size() > 0) {
			String[] result = new String[words.size()];
			for (int i = 0; i < words.size(); i++) {
				result[i] = words.get(i);
			}
			return result;
		}

		return new String[] {};

	}

	public void reverseString(char[] s) {

		if (s.length <= 1)
			return;

		int startIndex = 0;
		int endIndex = s.length - 1;
		char tmp;
		while (startIndex < endIndex) {
			tmp = s[startIndex];
			s[startIndex] = s[endIndex];
			s[endIndex] = tmp;
			startIndex++;
			endIndex--;
		}
	}

	public int[] shortestToChar(String S, char C) {
		if (S.length() == 1)
			return new int[] { 0 };
		int[] result = new int[S.length()];

		int startIndex = S.indexOf(C);
		int endIndex = S.indexOf(C, startIndex + 1);

		for (int i = 0; i < result.length; i++) {
			if (i <= startIndex)
				result[i] = startIndex - i;
			else {
				if (endIndex == -1)
					result[i] = i - startIndex;
				else if (endIndex == i) {
					result[i] = 0;
					startIndex = endIndex;
					endIndex = S.indexOf(C, startIndex + 1);
				} else {
					result[i] = Math.min(i - startIndex, endIndex - 1);
				}
			}

		}

		return result;

	}

	public boolean divisorGame(int N) {
		return (N % 2 == 0) ? true : false;
	}

	public String removeDuplicates(String S) {
		if (S.length() == 1)
			return S;

		StringBuilder sb = new StringBuilder(S);

		for (int i = 1; i < sb.length(); i++) {
			if (sb.charAt(i) == sb.charAt(i - 1)) {
				sb.deleteCharAt(i);
				sb.deleteCharAt(i - 1);
				i = i - 2;
				if (i < 0)
					i = 0;
			}
		}

		return sb.toString();

	}

	public ListNode middleNode(ListNode head) {

		ListNode it = head;
		int count = 0;
		while (it != null) {
			count++;
			it = it.next;
		}

		int index = count / 2;
		count = 0;
		it = head;
		while (index != count) {
			count++;
			it = it.next;
		}

		return it;

	}

	public int numRookCaptures(char[][] board) {
		int noOfPawns = 0;

		int x = 0;
		int y = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 'R') {
					x = i;
					y = j;
				}
			}
		}

		// right
		for (int i = x; i < board.length; i++) {
			if (board[i][y] == 'p') {
				noOfPawns++;
				break;
			} else if (board[i][y] == 'B')
				break;
		}

		// left
		for (int i = x; i >= 0; i--) {
			if (board[i][y] == 'p') {
				noOfPawns++;
				break;
			} else if (board[i][y] == 'B')
				break;
		}

		// up
		for (int i = y; i < board.length; i++) {
			if (board[x][i] == 'p') {
				noOfPawns++;
				break;
			} else if (board[x][i] == 'B')
				break;
		}

		// down
		for (int i = x; i >= 0; i--) {
			if (board[x][i] == 'p') {
				noOfPawns++;
				break;
			} else if (board[x][i] == 'B')
				break;
		}

		return noOfPawns;
	}

	public List<List<Integer>> minimumAbsDifference(int[] arr) {

		List<List<Integer>> result = new ArrayList<List<Integer>>();

		Arrays.sort(arr);

		int minDiff = Integer.MAX_VALUE;

		for (int i = 1; i < arr.length; i++) {
			if (Math.abs(arr[i] - arr[i - 1]) <= minDiff) {

				if (Math.abs(arr[i] - arr[i - 1]) < minDiff)
					result.clear();

				minDiff = Math.abs(arr[i] - arr[i - 1]);
				List<Integer> pair = new ArrayList<Integer>();
				pair.add(arr[i - 1]);
				pair.add(arr[i]);
				result.add(pair);

			}

		}

		return result;
	}

	public int[] relativeSortArray(int[] arr1, int[] arr2) {
		int[] values = new int[1001];
		for (int i = 0; i < arr1.length; i++) {
			values[arr1[i]]++;
		}

		int[] result = new int[arr1.length];

		int count = 0;
		int index = 0;
		for (int i = 0; i < arr2.length; i++) {
			count = values[arr2[i]] + index;

			for (int j = index; j < count; j++) {
				result[j] = arr2[i];
				index++;
			}
			values[arr2[i]] = 0;
		}

		if (index == result.length)
			return result;
		for (int i = 0; i < values.length; i++) {
			count = values[i];
			if (count > 0) {
				count = count + index;
				for (int j = index; j < count; j++) {
					result[j] = i;
					index++;
					if (index == result.length)
						return result;
				}
			}

		}

		return result;
	}

	public TreeNode increasingBST(TreeNode root) {

		inOrder(root);

		TreeNode newRoot = new TreeNode(inOrderTree.get(0).val);
		TreeNode it = newRoot;
		for (int i = 1; i < inOrderTree.size(); i++) {
			it.right = new TreeNode(inOrderTree.get(i).val);
			it = it.right;
		}

		return newRoot;

	}

	List<TreeNode> inOrderTree = new ArrayList<TreeNode>();

	public void inOrder(TreeNode root) {
		if (root.left != null)
			inOrder(root.left);

		inOrderTree.add(root);

		if (root.right != null)
			inOrder(root.right);

	}

	public int countCharacters(String[] words, String chars) {
		int charsCount = 0;

		int[] referenceChars = new int[26];

		for (char c : chars.toCharArray()) {
			referenceChars[c - 'a']++;
		}

		int[] loopingMap = new int[26];

		System.arraycopy(referenceChars, 0, loopingMap, 0, 26);

		boolean isOK = true;
		for (int i = 0; i < words.length; i++) {
			if (words[i].length() > chars.length())
				continue;

			isOK = true;
			for (char c : words[i].toCharArray()) {
				if (loopingMap[c - 'a'] > 0) {
					loopingMap[c - 'a']--;
				} else {
					isOK = false;
					break;
				}

			}
			if (isOK)
				charsCount = charsCount + words[i].length();

			System.arraycopy(referenceChars, 0, loopingMap, 0, 26);
		}

		return charsCount;
	}

	public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {

		List<List<Integer>> results = new ArrayList<List<Integer>>();

		int value = 0;
		for (int x = 1; x <= z; x++) {
			for (int y = 1; y <= z; y++) {
				value = customfunction.f(x, y);
				if (value == z) {
					ArrayList<Integer> a1 = new ArrayList<Integer>();
					a1.add(x);
					a1.add(y);
					results.add(a1);
				} else if (value > z)
					break;
			}

		}

		return results;
	}

	public int heightChecker(int[] heights) {
		if (heights.length == 1)
			return 0;

		int noOfReorder = 0;
		int[] tmpArray = new int[heights.length];

		System.arraycopy(heights, 0, tmpArray, 0, heights.length);

		int index = 0;

		Arrays.sort(tmpArray);

		for (int i : tmpArray) {
			if (heights[index++] != i)
				noOfReorder++;
		}

		return noOfReorder;

	}

	public int minDeletionSize(String[] A) {

		if (A.length == 1)
			return 0;

		int countOfDeletions = 0;

		int noOfChars = A[0].length();

		for (int i = 0; i < noOfChars; i++) {
			for (int j = 1; j < A.length; j++) {
				if (A[j].charAt(i) < A[j - 1].charAt(i)) {
					countOfDeletions++;
					break;
				}
			}
		}

		return countOfDeletions;
	}

	public int[] diStringMatch(String S) {

		int size = S.length();
		int[] result = new int[size + 1];

		int minRef = 0;
		int maxRef = size;

		int index = 0;

		for (char c : S.toCharArray()) {
			if (c == 'I') {
				result[index] = minRef;
				minRef++;
				index++;
			} else {
				result[index] = maxRef;
				index++;
				maxRef--;
			}
		}

		result[index] = minRef;
		return result;

	}

	public int peakIndexInMountainArray(int[] A) {

		for (int i = 1; i < A.length - 1; i++) {
			if (A[i] > A[i - 1] && A[i] < A[i + 1])
				return i;
		}
		return 0;
	}

	public boolean canConstruct(String ransomNote, String magazine) {
		if (ransomNote.length() == 0)
			return true;
		if (ransomNote.length() > magazine.length())
			return false;

		int[] countOfLetters = new int[26];

		for (char c : magazine.toCharArray()) {
			countOfLetters[c - 'a'] = countOfLetters[c - 'a'] + 1;
		}

		for (char c : ransomNote.toCharArray()) {
			countOfLetters[c - 'a'] = countOfLetters[c - 'a'] - 1;
			if (countOfLetters[c - 'a'] == -1)
				return false;
		}
		return true;
	}

	public String toLowerCase(String str) {
		if (str == null || str.length() == 0)
			return str;

		StringBuilder sb = new StringBuilder();

		int diff = 'a' - 'A';
		int smallChar = 0;
		for (char c : str.toCharArray()) {
			if (c >= 'A' && c <= 'Z') {
				smallChar = c + diff;
				c = (char) smallChar;
				sb.append(c);
			}

			else
				sb.append(c);

		}

		return sb.toString();
	}

	public int sockMerchant(int n, int[] ar) {
		int pairs = 0;

		Set<Integer> isInside = new HashSet<Integer>();

		for (int i = 0; i < ar.length; i++) {
			if (isInside.contains(ar[i])) {
				pairs++;
				isInside.remove(ar[i]);
			} else
				isInside.add(ar[i]);
		}

		return pairs;
	}

	public String reverseWords(String s) {
		String[] splittedWords = s.split(" ");

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < splittedWords.length; i++) {
			if (i == splittedWords.length - 1)
				sb.append(reversWord(splittedWords[i]));
			else
				sb.append(reversWord(splittedWords[i])).append(" ");

		}

		return sb.toString();
	}

	public StringBuilder reversWord(String word) {
		char[] letters = word.toCharArray();

		int i = 0;
		int j = letters.length - 1;
		while (i < j) {
			char tmp = letters[i];
			letters[i] = letters[j];
			letters[j] = tmp;
			i++;
			j--;
		}
		StringBuilder sb = new StringBuilder();

		for (int j2 = 0; j2 < letters.length; j2++) {
			sb.append(letters[j2]);
		}

		return sb;
	}

	public boolean isUnivalTree(TreeNode root) {

		if (root.left != null && root.right != null)
			if (root.val != root.left.val || root.val != root.right.val)
				return false;
			else
				return isUnivalTree(root.left) && isUnivalTree(root.right);

		else if (root.left != null)
			if (root.val != root.left.val)
				return false;
			else
				return isUnivalTree(root.left);

		else if (root.right != null)
			if (root.val != root.right.val)
				return false;
			else
				return isUnivalTree(root.right);

		return true;

	}

	public int numUniqueEmails(String[] emails) {
		if (emails.length == 1)
			return 1;

		Set<String> hashMails = new HashSet<String>();
		int atIndex = -1;
		int plusIndex = -1;
		String start;
		for (int i = 0; i < emails.length; i++) {
			atIndex = emails[i].indexOf('@');
			start = emails[i].substring(0, atIndex).replace(".", "");
			plusIndex = start.indexOf('+');
			if (plusIndex != -1)
				start = start.substring(0, plusIndex);
			hashMails.add(start + emails[i].substring(atIndex));
		}

		return hashMails.size();
	}

	public List<Integer> postorder(Node root) {
		if (root == null)
			return new ArrayList<Integer>();

		List<Integer> result = new ArrayList<Integer>();
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> resultStack = new Stack<Node>();
		stack.push(root);
		resultStack.push(root);
		Node node;
		while (!stack.isEmpty()) {
			node = stack.pop();
			if (node.children != null)
				for (int i = node.children.size() - 1; i >= 0; i--) {
					stack.push(node.children.get(i));
					resultStack.push(node.children.get(i));
				}
		}

		while (!resultStack.isEmpty())
			result.add(resultStack.pop().val);
		return result;
	}

	List<Integer> arrResult = new ArrayList<Integer>();

	public List<Integer> preorder(Node root) {
		if (root == null)
			return arrResult;

		arrResult.add(root.val);

		for (int i = 0; i < root.children.size(); i++) {
			preorder(root.children.get(i));
		}

		return arrResult;

	}

	public TreeNode searchBST(TreeNode root, int val) {
		if (root == null)
			return null;

		if (root.val == val)
			return root;
		if (val < root.val)
			return searchBST(root.left, val);
		else
			return searchBST(root.right, val);
	}

	public int[] plusOne(int[] digits) {

		for (int i = digits.length - 1; i >= 0; i--) {
			if (digits[i] + 1 < 10) {
				digits[i] = digits[i] + 1;
				return digits;
			} else
				digits[i] = 0;
		}

		int[] result = new int[digits.length + 1];
		System.arraycopy(digits, 0, result, 1, digits.length);
		result[0] = 1;

		return result;
	}

	public int arrayPairSum(int[] nums) {
		if (nums.length == 0)
			return 0;

		Arrays.sort(nums);
		int sum = 0;
		for (int i = 0; i < nums.length; i = i + 2) {
			sum = sum + nums[i];
		}

		return sum;
	}

	public int hammingDistance(int x, int y) {
		int r = x ^ y;
		String s = Integer.toBinaryString(r);
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1')
				count++;
		}

		return count;

	}

	public int[] intersection(int[] nums1, int[] nums2) {
		if (nums1.length == 0 || nums2.length == 0)
			return new int[] {};

		HashSet<Integer> repeated = new HashSet<Integer>();
		HashSet<Integer> result = new HashSet<Integer>();
		for (int i = 0; i < nums1.length; i++) {
			repeated.add(nums1[i]);
		}

		for (int i = 0; i < nums2.length; i++) {
			if (repeated.contains(nums2[i]))
				result.add(nums2[i]);
		}

		int[] arr = new int[result.size()];
		int i = 0;

		Iterator<Integer> it = result.iterator();
		while (it.hasNext()) {
			arr[i] = it.next();
			i++;
		}

		return arr;

	}

	public int[] intersect(int[] nums1, int[] nums2) {

		if (nums1.length == 0 || nums2.length == 0)
			return new int[] {};

		Map<Integer, Integer> counts1 = new HashMap<Integer, Integer>();
		Map<Integer, Integer> counts2 = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums1.length; i++) {
			if (counts1.containsKey(nums1[i]))
				counts1.put(nums1[i], counts1.get(nums1[i]) + 1);
			else
				counts1.put(nums1[i], 1);
		}

		for (int i = 0; i < nums2.length; i++) {
			if (counts2.containsKey(nums2[i]))
				counts2.put(nums2[i], counts2.get(nums2[i]) + 1);
			else
				counts2.put(nums2[i], 1);
		}

		List<Integer> result = new ArrayList<Integer>();
		Iterator it = counts1.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> element = (Map.Entry<Integer, Integer>) it.next();
			int value = 0;
			if (counts2.containsKey(element.getKey())) {
				value = Math.min(element.getValue(), counts2.get(element.getKey()));
				for (int i = 0; i < value; i++) {
					result.add(element.getKey());
				}
			}

		}

		int[] arr = new int[result.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = result.get(i);
		}

		return arr;

	}

	public List<String> commonChars(String[] A) {

		Map<Character, Integer> repated = new HashMap<Character, Integer>();
		Map<Character, Integer> tmpRepated = new HashMap<Character, Integer>();

		List<Character> toRemove = new ArrayList<Character>();
		for (int i = 0; i < A[0].length(); i++) {
			if (repated.containsKey(A[0].charAt(i)))
				repated.put(A[0].charAt(i), repated.get(A[0].charAt(i)) + 1);
			else
				repated.put(A[0].charAt(i), 1);
		}

		for (int i = 1; i < A.length; i++) {
			for (int j = 0; j < A[i].length(); j++) {
				if (repated.containsKey(A[i].charAt(j))) {
					if (tmpRepated.containsKey(A[i].charAt(j)))
						tmpRepated.put(A[i].charAt(j), tmpRepated.get(A[i].charAt(j)) + 1);
					else
						tmpRepated.put(A[i].charAt(j), 1);

				}

			}

			Iterator it = repated.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				if (tmpRepated.containsKey(entry.getKey())) {
					repated.put((char) entry.getKey(),
							Math.min((int) entry.getValue(), tmpRepated.get(entry.getKey())));
				} else
					toRemove.add((char) entry.getKey());
				// repated.remove(entry.getKey());
			}
			tmpRepated = new HashMap<Character, Integer>();
			for (int j = 0; j < toRemove.size(); j++) {
				repated.remove(toRemove.get(j));
			}
			toRemove.clear();
		}

		List<String> result = new ArrayList<String>();

		Iterator it = repated.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String c = Character.toString((char) entry.getKey());
			for (int j = 0; j < (int) entry.getValue(); j++) {
				result.add(c);
			}
		}

		return result;

	}

	public boolean uniqueOccurrences(int[] arr) {
		if (arr.length == 1)
			return true;

		Map<Integer, Integer> occur = new HashMap<Integer, Integer>();

		for (int i = 0; i < arr.length; i++) {
			if (occur.containsKey(arr[i]))
				occur.put(arr[i], occur.get(arr[i]) + 1);
			else
				occur.put(arr[i], 1);
		}

		HashSet<Integer> repeated = new HashSet<Integer>();

		Iterator it = occur.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry element = (Map.Entry) it.next();
			int val = (int) element.getValue();
			if (repeated.contains(val))
				return false;
			else
				repeated.add(val);
		}

		return true;

	}

	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null)
			return t2;
		if (t2 == null)
			return t1;

		t1.val = t1.val + t2.val;
		t1.left = mergeTrees(t1.left, t2.left);
		t1.right = mergeTrees(t1.right, t2.right);
		return t1;

	}

	public boolean checkPerfectNumber(int num) {

		if (num == 1)
			return false;

		int sqrt = (int) Math.sqrt(num);

		int sum = 1;

		for (int i = 2; i <= sqrt; i++) {
			if (num % i == 0) {
				sum = sum + i + (num / i);
			}
		}

		return (sum == num);
	}

	public List<Integer> selfDividingNumbers(int left, int right) {

		List<Integer> result = new ArrayList<Integer>();

		int current = 0;
		boolean isValid = true;
		while (left <= right) {
			current = left;
			isValid = true;
			while (current > 0) {
				if ((current % 10) == 0 || left % (current % 10) != 0) {
					isValid = false;
					break;
				}

				current = current / 10;
			}
			if (isValid)
				result.add(left);
			left++;
		}

		return result;
	}

	public boolean judgeCircle(String moves) {

		if (moves.length() == 0)
			return true;
		if (moves.length() % 2 != 0)
			return false;

		int leftRightCount = 0;
		int upDownCount = 0;
		for (int i = 0; i < moves.length(); i++) {
			if (moves.charAt(i) == 'U')
				upDownCount++;
			else if (moves.charAt(i) == 'D')
				upDownCount--;
			else if (moves.charAt(i) == 'L')
				leftRightCount--;
			else
				leftRightCount++;
		}

		if (upDownCount == 0 && leftRightCount == 0)
			return true;

		return false;

	}

	public int repeatedNTimes(int[] A) {

		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		int N = A.length / 2;
		for (int i = 0; i < A.length; i++) {
			if (counts.containsKey(A[i])) {
				counts.put(A[i], counts.get(A[i]) + 1);
				if (counts.get(A[i]) == N)
					return A[i];
			}

			else
				counts.put(A[i], 1);

		}

		return 0;

	}

	public int[][] flipAndInvertImage(int[][] A) {

		for (int i = 0; i < A.length; i++) {

			// reverse
			int m = 0;
			int k = A[i].length - 1;
			while (m < k) {
				int tmp = A[i][m];
				A[i][m] = (A[i][k] == 1) ? 0 : 1;
				A[i][k] = (tmp == 1) ? 0 : 1;
				m++;
				k--;
			}
			if (A[i].length % 2 != 0) {
				int index = A[i].length / 2;
				A[i][index] = (A[i][index] == 1) ? 0 : 1;
			}
		}

		return A;
	}

	public int uniqueMorseRepresentations(String[] words) {

		if (words.length == 0)
			return 0;
		else if (words.length == 1)
			return 1;

		String[] transformation = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
				".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
				"--.." };

		HashSet<String> diff = new HashSet<String>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				sb.append(transformation[words[i].charAt(j) - 'a']);
			}
			diff.add(sb.toString());
			sb = new StringBuilder();
		}

		return diff.size();

	}

	public String removeOuterParentheses(String S) {

		if (S.length() == 0)
			return "";
		List<String> cutted = new ArrayList<String>();
		int count = 0;

		StringBuilder value = new StringBuilder();

		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == '(') {
				value.append('(');
				count++;
			} else {
				value.append(')');
				count--;
			}

			if (count == 0) {
				cutted.add(value.toString());
				value = new StringBuilder();
			}

		}

		for (int i = 0; i < cutted.size(); i++) {
			value.append(cutted.get(i).substring(1, cutted.get(i).length() - 1));
		}

		return value.toString();
	}

	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;

		int maxLevel = 0;
		int size = 0;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		TreeNode it;

		while (!queue.isEmpty()) {
			maxLevel++;
			size = queue.size();
			for (int i = 0; i < size; i++) {
				it = queue.poll();
				if (it.left != null)
					queue.add(it.left);
				if (it.right != null)
					queue.add(it.right);
			}
		}

		return maxLevel;

	}

	public int rangeSumBST(TreeNode root, int L, int R) {

		return SumNodes(root, L, R);
	}

	public int SumNodes(TreeNode root, int L, int R) {
		if (root == null)
			return 0;
		if (root.val >= L && root.val <= R)
			return root.val + SumNodes(root.left, L, R) + SumNodes(root.right, L, R);
		else if (root.val < L)
			return SumNodes(root.right, L, R);
		else
			return SumNodes(root.left, L, R);

	}

	public int getDecimalValue(ListNode head) {

		if (head == null)
			return 0;

		StringBuilder binary = new StringBuilder();
		while (head != null) {
			binary.append(head.val);
			head = head.next;
		}

		return Integer.parseInt(binary.toString(), 2);
	}

	public int findNumbers(int[] nums) {
		int result = 0;

		int mod = 10;
		int count = 1;
		for (int i = 0; i < nums.length; i++) {
			mod = 10;
			count = 1;
			while (nums[i] % mod != nums[i]) {
				count++;
				mod = mod * 10;
			}
			if (count % 2 == 0)
				result++;
		}

		return result;
	}

	public String addStrings(String num1, String num2) {
		if (num1.length() == 0)
			return num2;
		else if (num2.length() == 0)
			return num1;

		StringBuilder result = new StringBuilder();

		int[] resultArray = new int[Math.max(num1.length(), num2.length())];
		int resultIterator = 0;

		int firstNo, SecondNo, sum, remainder = 0;

		int num1Iterator = num1.length() - 1;
		int num2Iterator = num2.length() - 1;

		while (num1Iterator >= 0 && num2Iterator >= 0) {
			firstNo = num1.charAt(num1Iterator) - '0';
			SecondNo = num2.charAt(num2Iterator) - '0';

			sum = firstNo + SecondNo + remainder;
			resultArray[resultIterator] = (sum % 10);
			remainder = sum / 10;
			num1Iterator--;
			num2Iterator--;
			resultIterator++;
		}

		while (num1Iterator >= 0) {
			firstNo = num1.charAt(num1Iterator) - '0';
			sum = firstNo + remainder;
			resultArray[resultIterator] = (sum % 10);
			remainder = sum / 10;
			num1Iterator--;
			resultIterator++;
		}

		while (num2Iterator >= 0) {
			SecondNo = num2.charAt(num2Iterator) - '0';
			sum = SecondNo + remainder;
			resultArray[resultIterator] = (sum % 10);
			remainder = sum / 10;
			num2Iterator--;
			resultIterator++;
		}

		if (remainder == 1)
			result.append("1");

		for (int i = resultArray.length - 1; i >= 0; i--) {
			result.append(resultArray[i]);
		}

		return result.toString();

	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = new ListNode(0);

		ListNode iterator = result;

		int sum = 0;
		int remainder = 0;
		while (l1 != null && l2 != null) {
			sum = l1.val + l2.val + remainder;
			iterator.val = sum % 10;
			remainder = sum / 10;
			if (l1.next != null || l2.next != null) {
				iterator.next = new ListNode(0);
				iterator = iterator.next;
			}
			l1 = l1.next;
			l2 = l2.next;
		}

		while (l1 != null) {
			sum = l1.val + remainder;
			iterator.val = sum % 10;
			remainder = sum / 10;
			if (l1.next != null) {
				iterator.next = new ListNode(0);
				iterator = iterator.next;
			}

			l1 = l1.next;
		}

		while (l2 != null) {
			sum = l2.val + remainder;
			iterator.val = sum % 10;
			remainder = sum / 10;

			if (l2.next != null) {
				iterator.next = new ListNode(0);
				iterator = iterator.next;
			}

			l2 = l2.next;
		}

		if (remainder == 1)
			iterator.next = new ListNode(1);

		return result;
	}

	public int maxLevelSum(TreeNode root) {
		int maxLevel = 0;
		long maxValue = 0;
		long value = 0;
		int currentLevel = 1;

		Queue<TreeNode> nodes = new LinkedList<TreeNode>();

		nodes.add(root);
		int size = 0;

		TreeNode node;
		while (nodes.size() > 0) {
			size = nodes.size();
			for (int i = 0; i < size; i++) {
				node = nodes.poll();
				value = value + node.val;

				if (node.left != null)
					nodes.add(node.left);
				if (node.right != null)
					nodes.add(node.right);
			}

			if (maxValue < value) {
				maxValue = value;
				maxLevel = currentLevel;
			}
			currentLevel++;
			value = 0;
		}

		return maxLevel;
	}

	public class CustomFunction {
		public int f(int x, int y) {
			return 0;
		}
	}

	public int[] gardenNoAdj(int N, int[][] paths) {
		int[] result = new int[N];

		if (paths.length == 0) {
			for (int i = 0; i < N; i++)
				result[i] = 1;

			return result;
		}

		Node2[] graph = new Node2[N];
		for (int i = 0; i < graph.length; i++) {
			Node2 node = new Node2();
			// node.number = i+1;
			graph[i] = node;
		}

		for (int i = 0; i < paths.length; i++) {
			graph[paths[i][0] - 1].adjecentNodes.add(graph[paths[i][1] - 1]);
			graph[paths[i][1] - 1].adjecentNodes.add(graph[paths[i][0] - 1]);
		}

		graph[0].color = 1;
		result[0] = 1;

		for (int i = 1; i < graph.length; i++) {
			graph[i].color = getColor(graph[i].adjecentNodes, 1);
			result[i] = graph[i].color;
		}

		return result;
	}

	public int getColor(List<Node2> adjecentNodes, int tryingNumber) {

		for (int j = 0; j < adjecentNodes.size(); j++) {
			if (adjecentNodes.get(j).color == tryingNumber)
				return getColor(adjecentNodes, tryingNumber + 1);
		}

		return tryingNumber;

	}

	public int findJudge(int N, int[][] trust) {
		int result = -1;

		if (trust == null || trust.length == 0)
			return result;

		HashSet<Integer> NotJudge = new HashSet<Integer>();
		HashSet<Integer> MayJudge = new HashSet<Integer>();

		HashMap<Integer, Integer> trustList = new HashMap<Integer, Integer>();

		int count;

		for (int i = 0; i < trust.length; i++) {
			count = 1;
			NotJudge.add(trust[i][0]);
			MayJudge.remove(trust[i][0]);

			if (trustList.containsKey(trust[i][1])) {
				count = trustList.get(trust[i][1]) + 1;
				trustList.put(trust[i][1], count);
			}

			else
				trustList.put(trust[i][1], 1);

			if (count == N - 1 && !NotJudge.contains(trust[i][1])) {
				MayJudge.add(trust[i][1]);
			}

		}

		if (!MayJudge.isEmpty()) {
			return MayJudge.iterator().next();
		}

		return result;
	}

	public int countServers(int[][] grid) {
		int countOfVisited = 0;

		Map<Integer, Integer> rows = new HashMap<Integer, Integer>();
		Map<Integer, Integer> columns = new HashMap<Integer, Integer>();
		HashSet<Integer> alreadyRemoved = new HashSet<Integer>();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++)
				if (grid[i][j] == 1) {
					if (rows.containsKey(i))
						rows.put(i, rows.get(i) + 1);
					else
						rows.put(i, 1);

					if (columns.containsKey(j))
						columns.put(j, columns.get(j) + 1);
					else
						columns.put(j, 1);

				}
		}

		Iterator hmIterator = rows.entrySet().iterator();
		while (hmIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) hmIterator.next();
			int count = (int) mapElement.getValue();
			if (count > 1) {
				countOfVisited += count;
				if (columns.containsKey((int) mapElement.getKey())) {
					countOfVisited -= 1;
					System.out.println((int) mapElement.getKey());
					alreadyRemoved.add((int) mapElement.getKey());
				}

			}

		}

		Iterator ColumnIterator = columns.entrySet().iterator();
		while (ColumnIterator.hasNext()) {
			Map.Entry element = (Map.Entry) ColumnIterator.next();
			int count = (int) element.getValue();
			if (count > 1) {
				countOfVisited += count;

				if (rows.containsKey((int) element.getKey()) && !alreadyRemoved.contains((int) element.getKey())) {
					System.out.println((int) element.getKey());
					countOfVisited -= 1;
				}

			}

		}

		return countOfVisited;
	}

	public boolean canVisitAllRooms(List<List<Integer>> rooms) {

		boolean[] visited = new boolean[rooms.size()];
		visited[0] = true;
		Stack<Integer> myStack = new Stack<Integer>();
		myStack.push(0);

		while (!myStack.isEmpty()) {
			int room = myStack.pop();

			for (int i = 0; i < rooms.get(room).size(); i++) {
				if (!visited[rooms.get(room).get(i)]) {
					visited[rooms.get(room).get(i)] = true;
					myStack.push(rooms.get(room).get(i));
				}
			}

		}

		for (int i = 0; i < visited.length; i++)
			if (!visited[i])
				return false;

		return true;
	}
}
