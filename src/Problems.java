import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

	public int rob(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		
		if (nums.length == 1) {
			return nums[0];
		}
		
		if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}

		int values[] = new int[nums.length];
		values[0] = nums[0];
		values[1] = Math.max(nums[0], nums[1]);

		for (int i = 2; i < nums.length; i++) {
			values[i] = Math.max(nums[i] + values[i - 2], values[i - 1]);
		}

		return values[values.length - 1];
	}

	public boolean isPowerOfFour(int num) {
		if (num < 1)
			return false;
		while (num % 4 == 0)
			num = num / 4;

		return num == 1;
	}

	public boolean isPowerOfTwo(int n) {
		if (n < 1)
			return false;
		while (n % 2 == 0)
			n = n / 2;

		return n == 1;
	}

	public boolean isPowerOfThree(int n) {
		if (n < 1)
			return false;

		while (n % 3 == 0)
			n = n / 3;

		return n == 1;
	}

	public int getSum(int a, int b) {
		while (b != 0) {
			int carry = a & b;
			a ^= b;
			b = (int) carry << 1;
		}
		return a;

	}

	public int maxSubArray(int[] nums) {

		int max_so_far = Integer.MIN_VALUE;
		int max_ending_here = 0;

		for (int i = 0; i < nums.length; i++) {
			max_ending_here = max_ending_here + nums[i];
			if (max_so_far < max_ending_here)
				max_so_far = max_ending_here;
			if (max_ending_here < 0)
				max_ending_here = 0;
		}

		return max_so_far;
	}

	public int hammingWeight(int n) {
		String binary = Integer.toBinaryString(n);
		int countOfOnes = 0;

		for (char c : binary.toCharArray()) {
			if (c == '1')
				countOfOnes++;
		}

		return countOfOnes;
	}

	public boolean isHappy(int n) {
		if (n <= 0)
			return false;
		if (n == 1)
			return true;

		Set<Integer> checkRepeated = new HashSet<Integer>();

		int newNumber = 0;
		while (n != 1) {
			newNumber = 0;
			while (n > 0) {
				newNumber = newNumber + (int) Math.pow(n % 10, 2);
				n = n / 10;
			}
			if (newNumber == 1)
				return true;

			if (checkRepeated.contains(newNumber))
				return false;

			checkRepeated.add(newNumber);
			n = newNumber;

		}

		return true;

	}

	public int maxProfit1(int[] prices) {

		if (prices.length <= 1)
			return 0;

		int max_so_far = Integer.MIN_VALUE;
		int max_ending_here = 0;

		for (int i = 1; i < prices.length; i++) {
			max_ending_here = max_ending_here + prices[i] - prices[i - 1];
			if (max_so_far < max_ending_here)
				max_so_far = max_ending_here;
			if (max_ending_here < 0)
				max_ending_here = 0;
		}

		if (max_so_far < 0)
			return 0;

		return max_so_far;
	}

	public List<List<Integer>> generate(int numRows) {

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (numRows <= 0)
			return result;
		List<Integer> tmp = new ArrayList<Integer>();
		tmp.add(1);

		result.add(tmp);
		if (numRows == 1)
			return result;

		tmp = new ArrayList<Integer>();
		tmp.add(1);
		tmp.add(1);
		result.add(tmp);
		int j = 0;
		int k = 1;

		List<Integer> prev = new ArrayList<Integer>();

		for (int i = 3; i <= numRows; i++) {
			tmp = new ArrayList<Integer>();
			j = 1;
			tmp.add(1);
			prev = result.get(i - 2);
			while (j < (i / 2)) {
				tmp.add(prev.get(j) + prev.get(j - 1));

				j++;
			}
			if (i % 2 != 0) {
				k = 2;
				tmp.add((i / 2), prev.get(j - 1) + prev.get(j));
				j++;
			} else
				k = 1;
			while (j < i) {
				tmp.add(tmp.get(j - k));
				j++;
				k = k + 2;
			}

			result.add(tmp);
		}

		return result;
	}

	public int firstUniqChar(String s) {
		if (s.length() == 0)
			return -1;
		if (s.length() == 1)
			return 0;

		HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			counts.putIfAbsent(s.charAt(i), 0);
			counts.put(s.charAt(i), counts.get(s.charAt(i)) + 1);
		}

		for (int i = 0; i < s.length(); i++) {
			if (counts.get(s.charAt(i)) == 1)
				return i;
		}
		return -1;
	}

	public int titleToNumber(String s) {
		int result = 0;
		int j = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			result = result + ((s.charAt(i) - 'A' + 1) * (int) Math.pow(26, j));
			j++;
		}
		return result;
	}

	public int maxProfit(int[] prices) {
		if (prices.length == 0 || prices.length == 1)
			return 0;

		int maxprofit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1])
				maxprofit += prices[i] - prices[i - 1];
		}
		return maxprofit;
	}

	public int romanToInt(String s) {
		Map<String, Integer> symbolsValues = new HashMap<String, Integer>();
		symbolsValues.put("I", 1);
		symbolsValues.put("V", 5);
		symbolsValues.put("X", 10);
		symbolsValues.put("L", 50);
		symbolsValues.put("C", 100);
		symbolsValues.put("D", 500);
		symbolsValues.put("M", 1000);
		symbolsValues.put("IV", 4);
		symbolsValues.put("IX", 9);
		symbolsValues.put("XL", 40);
		symbolsValues.put("XC", 90);
		symbolsValues.put("CD", 400);
		symbolsValues.put("CM", 900);

		int value = 0;
		for (int i = 0; i < s.length(); i++) {
			if (i + 1 < s.length()) {
				String search = "" + s.charAt(i) + s.charAt(i + 1);
				if (symbolsValues.containsKey(search)) {
					value = value + symbolsValues.get(search);
					i++;
					continue;
				}
			}
			value = value + symbolsValues.get("" + s.charAt(i));

		}

		return value;
	}

	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums.length == 0)
			return null;
		if (nums.length == 1)
			return new TreeNode(nums[0]);

		return createBST(nums, 0, nums.length - 1);

	}

	public TreeNode createBST(int[] nums, int leftIndex, int rightIndex) {
		if (leftIndex > rightIndex) {
			return null;
		}

		int midIndex = (leftIndex + rightIndex) / 2;
		TreeNode node = new TreeNode(nums[midIndex]);

		node.left = createBST(nums, leftIndex, midIndex - 1);

		node.right = createBST(nums, midIndex + 1, rightIndex);

		return node;

	}

	public boolean containsDuplicate(int[] nums) {
		Set<Integer> isFound = new HashSet<Integer>();

		for (int i = 0; i < nums.length; i++) {
			if (isFound.contains(nums[i]))
				return true;

			isFound.add(nums[i]);
		}

		return false;

	}

	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length())
			return false;

		int[] counts = new int[26];
		for (int i = 0; i < s.length(); i++) {
			counts[s.charAt(i) - 'a']++;
			counts[t.charAt(i) - 'a']--;
		}

		for (int i : counts) {
			if (i != 0)
				return false;
		}

		return true;

	}

	public int majorityElement(int[] nums) {

		if (nums.length == 0)
			return 0;

		int major = nums[0];
		int countOfMajor = 1;

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == major)
				countOfMajor++;
			else {
				countOfMajor--;
				if (countOfMajor == 0) {
					major = nums[i];
					countOfMajor = 1;
				}

			}
		}

		return major;
	}

	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}

	public void moveZeroes(int[] nums) {
		if (nums.length == 0 || nums.length == 1)
			return;

		int i = 0;

		int countOfZeros = 0;

		for (int k = 0; k < nums.length; k++) {
			if (nums[k] != 0) {
				nums[i] = nums[k];
				i++;
			} else
				countOfZeros++;
		}

		for (int k = nums.length - 1; k >= nums.length - countOfZeros; k--) {
			nums[k] = 0;
		}

	}

	public ListNode reverseList(ListNode head) {
		if (head == null)
			return null;

		Stack<ListNode> reverse = new Stack<ListNode>();
		while (head != null) {
			reverse.add(head);
			head = head.next;
		}

		ListNode newHead = reverse.pop();
		ListNode it = newHead;
		while (!reverse.isEmpty()) {
			it.next = reverse.pop();
			it = it.next;

		}

		it.next = null;

		while (newHead != null) {
			System.out.println(newHead.val);
			newHead = newHead.next;
		}

		return newHead;

	}

	public List<String> fizzBuzz(int n) {
		if (n < 1)
			return new ArrayList<String>();

		List<String> result = new ArrayList<String>();

		String toInsert = "";
		for (int i = 1; i <= n; i++) {
			toInsert = "";
			if (i % 3 == 0)
				toInsert = "Fizz";
			if (i % 5 == 0)
				toInsert += "Buzz";

			if (toInsert.length() == 0)
				toInsert = "" + i;

			result.add(toInsert);
		}

		return result;
	}

	public int strStr(String haystack, String needle) {
		if (needle.equals(""))
			return 0;
		if (needle.length() > haystack.length())
			return -1;
		int searchSize = needle.length();

		for (int i = 0; i < haystack.length() - searchSize + 1; i++) {
			if (haystack.substring(i, i + searchSize).equals(needle))
				return i;
		}

		return -1;
	}

	public boolean isPalindrome(String s) {
		if (s.length() == 0)
			return true;
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))
				sb.append(c);
			else if (c >= 'A' && c <= 'Z')
				sb.append(Character.toLowerCase(c));
		}

		int i = 0;
		int j = sb.length() - 1;
		while (i < j) {
			if (sb.charAt(i) != sb.charAt(j))
				return false;
			i++;
			j--;
		}

		return true;
	}

	public List<String> topKFrequent(String[] words, int k) {
		List<String> result = new ArrayList<String>(k);
		if (words.length == 0)
			return result;

		Map<String, Integer> counts = new HashMap<String, Integer>();

		for (int i = 0; i < words.length; i++) {
			counts.putIfAbsent(words[i], 0);
			counts.put(words[i], counts.get(words[i]) + 1);
		}

		List<String> wordsByCount = new ArrayList<String>(counts.keySet());

		Collections.sort(wordsByCount,
				(word1, word2) -> counts.get(word1).equals(counts.get(word2)) ? word1.compareTo(word2)
						: counts.get(word2) - counts.get(word1));

		for (int i = 0; i < k; i++) {
			result.add(wordsByCount.get(i));
		}

		return result;
	}

	public String[] findWords(String[] words) {
		int[] characterRow = new int[26];
		characterRow[0] = 1;
		characterRow[1] = 2;
		characterRow[2] = 2;
		characterRow[3] = 1;
		characterRow[4] = 0;
		characterRow[5] = 1;
		characterRow[6] = 1;
		characterRow[7] = 1;
		characterRow[8] = 0;
		characterRow[9] = 1;
		characterRow[10] = 1;
		characterRow[11] = 1;
		characterRow[12] = 2;
		characterRow[13] = 2;
		characterRow[14] = 0;
		characterRow[15] = 0;
		characterRow[16] = 0;
		characterRow[17] = 0;
		characterRow[18] = 1;
		characterRow[19] = 0;
		characterRow[20] = 0;
		characterRow[21] = 2;
		characterRow[22] = 0;
		characterRow[23] = 2;
		characterRow[24] = 0;
		characterRow[25] = 2;
		List<String> resultList = new ArrayList<String>();

		int rowNum = -1;
		boolean isAccepted = true;

		for (int i = 0; i < words.length; i++) {
			isAccepted = true;
			if (words[i].length() > 0) {
				rowNum = characterRow[Character.toLowerCase(words[i].charAt(0)) - 'a'];
				for (int j = 1; j < words[i].length(); j++) {
					if (rowNum != characterRow[Character.toLowerCase(words[i].charAt(j)) - 'a']) {
						isAccepted = false;
						break;
					}
				}
			}

			if (isAccepted)
				resultList.add(words[i]);
		}

		String[] resultArr = new String[resultList.size()];
		for (int i = 0; i < resultArr.length; i++) {
			resultArr[i] = resultList.get(i);
		}

		return resultArr;

	}

	public int dominantIndex(int[] nums) {
		if (nums.length == 1)
			return 0;

		int maxValue = Integer.MIN_VALUE;
		int maxValueIndex = -1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > maxValue) {
				maxValue = nums[i];
				maxValueIndex = i;
			}
		}

		for (int i = 0; i < nums.length; i++) {
			if (i != maxValueIndex) {
				if (nums[i] * 2 > maxValue)
					return -1;
			}
		}

		return maxValueIndex;
	}

	public int[] sumZero(int n) {
		if (n <= 0)
			return new int[] {};
		if (n == 1)
			return new int[] { 0 };

		int[] result = new int[n];

		for (int i = 0; i < result.length - 1; i = i + 2) {
			result[i] = i + 1;
			result[i + 1] = (i + 1) * -1;
		}

		return result;

	}

	public boolean isSymmetric(TreeNode root) {
		return checkSym(root, root);
	}

	public boolean checkSym(TreeNode tLeft, TreeNode tRight) {
		if (tLeft == null && tRight == null)
			return true;
		if (tLeft == null || tRight == null)
			return false;
		return (tLeft.val == tRight.val) && checkSym(tLeft.right, tRight.left) && checkSym(tLeft.left, tRight.right);
	}

	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;

		Queue<TreeNode> nodes = new LinkedList<TreeNode>();

		nodes.add(root);

		TreeNode node = null;

		int depth = 0;
		int count = 0;
		while (!nodes.isEmpty()) {
			count = nodes.size();
			for (int i = 0; i < count; i++) {
				node = nodes.poll();
				if (node.left == null && node.right == null)
					return depth;
				if (node.left != null)
					nodes.add(node.left);
				if (node.right != null)
					nodes.add(node.right);
			}
			depth++;
		}
		return depth;

	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {

		List<List<Integer>> results = new ArrayList<List<Integer>>();

		if (root == null)
			return results;

		Queue<TreeNode> nodes = new LinkedList<TreeNode>();

		nodes.add(root);

		TreeNode node = null;

		List<Integer> level;
		int count = 0;
		while (!nodes.isEmpty()) {
			count = nodes.size();
			level = new ArrayList<Integer>();
			for (int i = 0; i < count; i++) {

				node = nodes.poll();
				if (node.left != null)
					nodes.add(node.left);

				if (node.right != null)
					nodes.add(node.right);

				level.add(node.val);

			}
			results.add(0, level);

		}

		return results;

	}

	public boolean isCousins(TreeNode root, int x, int y) {

		if (root.val == x || root.val == y)
			return false;

		Queue<TreeNode> nodes = new LinkedList<TreeNode>();

		nodes.add(root);

		TreeNode node = null;
		int nodeXLevel = -1;
		int nodeYLevel = -1;
		TreeNode xParent = null;
		TreeNode yParent = null;

		int level = 0;
		int count = 0;

		while (!nodes.isEmpty()) {
			count = nodes.size();
			for (int i = 0; i < count; i++) {

				node = nodes.poll();

				if (node.left != null) {
					if (node.left.val == x) {
						nodeXLevel = level;
						xParent = node;
					}

					if (node.left.val == y) {
						nodeYLevel = level;
						yParent = node;
					}
					nodes.add(node.left);
				}

				if (node.right != null) {
					if (node.right.val == x) {
						nodeXLevel = level;
						xParent = node;
					}

					if (node.right.val == y) {
						nodeYLevel = level;
						yParent = node;
					}
					nodes.add(node.right);
				}

				if (nodeXLevel != -1 && nodeYLevel != -1) {
					if (nodeXLevel == nodeYLevel && xParent != yParent)
						return true;
					else
						return false;
				}

			}

			level++;
		}

		return false;
	}

	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		if (n == 0)
			return true;

		if (n == 1 && flowerbed.length == 1 && flowerbed[0] == 0)
			return true;

		if (flowerbed.length > 1 && flowerbed[0] == 0 && flowerbed[1] == 0) {
			flowerbed[0] = 1;
			n--;
			if (n == 0)
				return true;
		}

		for (int i = 1; i < flowerbed.length - 1; i++) {

			if (flowerbed[i - 1] == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
				n--;
				if (n == 0)
					return true;
				flowerbed[i] = 1;
			}

		}

		if (flowerbed.length > 1 && flowerbed[flowerbed.length - 1] == 0 && flowerbed[flowerbed.length - 2] == 0) {
			n--;
			if (n == 0)
				return true;
		}

		return false;
	}

	public int lengthOfLastWord(String s) {
		if (s.trim().length() == 0)
			return 0;
		String[] words = s.split(" ");
		if (words.length > 0) {
			return words[words.length - 1].length();
		}

		return 0;
	}

	public boolean isPerfectSquare(int num) {
		double b = Math.sqrt(num);
		if (b == (int) b)
			return true;

		return false;
	}

	public boolean judgeSquareSum(int c) {
		for (long a = 0; a * a <= c; a++) {
			double b = Math.sqrt(c - a * a);
			if (b == (int) b)
				return true;
		}
		return false;

	}

	public int mySqrt(int x) {
		if (x == 1)
			return 1;
		int sqrtMin = 0;
		int sqrtMax = x;

		long value = 0;
		while (sqrtMin < sqrtMax) {
			value = (sqrtMax + sqrtMin) / 2;
			if (value * value == x)
				return (int) value;
			else if (value * value > x) {
				if (sqrtMax == value)
					return (int) value;
				sqrtMax = (int) value;
			} else {
				if (sqrtMin == value)
					return (int) value;
				sqrtMin = (int) value;
			}
		}

		return (int) value;

	}

	public int[] replaceElements(int[] arr) {
		int maxValueToRight = Integer.MIN_VALUE;
		int maxValueIndex = -1;
		for (int i = 0; i < arr.length; i++) {
			if (i == arr.length - 1) {
				arr[i] = -1;
				break;
			}
			if (i >= maxValueIndex) {
				maxValueToRight = Integer.MIN_VALUE;
				maxValueIndex = -1;
				for (int j = i + 1; j < arr.length; j++) {
					if (arr[j] >= maxValueToRight) {
						maxValueToRight = arr[j];
						maxValueIndex = j;
					}

				}
			}

			arr[i] = maxValueToRight;
		}

		return arr;
	}

	public TreeNode constructMaximumBinaryTree(int[] nums) {
		return makeTree(nums, 0, nums.length);
	}

	public TreeNode makeTree(int[] nums, int startIndex, int endIndex) {
		if (startIndex == endIndex)
			return null;

		int maxValIndex = Integer.MIN_VALUE;
		for (int i = startIndex; i < endIndex; i++) {
			if (nums[i] > maxValIndex)
				maxValIndex = nums[i];
		}
		TreeNode root = new TreeNode(nums[maxValIndex]);
		root.left = makeTree(nums, startIndex, maxValIndex);
		root.right = makeTree(nums, maxValIndex + 1, endIndex);
		return root;
	}

	public TreeNode bstToGst(TreeNode root) {
		sumRightTree(root, 0);
		return root;
	}

	public int sumRightTree(TreeNode root, int sum) {
		if (root == null)
			return sum;

		int sumR = sumRightTree(root.right, sum);

		root.val = sumR + root.val;
		return sumRightTree(root.left, root.val);
	}

	public int deepestLeavesSum(TreeNode root) {

		if (root == null)
			return 0;
		int[] levels = new int[10001];

		sumMaxLevelLeaves(root, levels, 0);

		for (int i = levels.length - 1; i >= 0; i--) {
			if (levels[i] > 0)
				return levels[i];
		}

		return 0;
	}

	public void sumMaxLevelLeaves(TreeNode root, int[] levels, int level) {
		if (root.left == null && root.right == null) {
			levels[level] = levels[level] + root.val;
			return;
		}
		if (root.left != null)
			sumMaxLevelLeaves(root.left, levels, level + 1);

		if (root.right != null)
			sumMaxLevelLeaves(root.right, levels, level + 1);

	}

	public List<Integer> findDuplicates(int[] nums) {
		int[] availableNums = new int[nums.length + 1];
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			availableNums[nums[i]]++;
		}

		for (int i = 1; i < availableNums.length; i++) {
			if (availableNums[i] > 1)
				result.add(i);
		}

		return result;
	}

	public List<Integer> findDisappearedNumbers(int[] nums) {
		boolean[] availableNums = new boolean[nums.length + 1];
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			availableNums[nums[i]] = true;
		}

		for (int i = 1; i < availableNums.length; i++) {
			if (!availableNums[i])
				result.add(i);
		}

		return result;
	}

	public boolean canWinNim(int n) {
		if (n % 4 == 0)
			return false;
		return true;
	}

	public int maxDistToClosest(int[] seats) {
		int dist = 0;
		int maxDistance = 1;
		boolean isFirstOne = true;

		for (int i = 0; i < seats.length; i++) {
			if (seats[i] == 1) {
				if (isFirstOne) {
					isFirstOne = false;
				} else {
					if (dist % 2 == 0)
						dist = dist / 2;
					else
						dist = (dist + 1) / 2;
				}

				if (dist > maxDistance)
					maxDistance = dist;
				dist = 0;
			} else
				dist++;
		}

		if (dist > maxDistance)
			maxDistance = dist;

		return maxDistance;
	}

	public int[] fairCandySwap(int[] A, int[] B) {
		int sumA = 0;
		int sumB = 0;
		Set<Integer> bItems = new HashSet<Integer>();
		for (int i = 0; i < A.length; i++) {
			sumA = sumA + A[i];
		}
		for (int i = 0; i < B.length; i++) {
			sumB = sumB + B[i];
			bItems.add(B[i]);
		}
		int diff = (sumB - sumA) / 2;

		for (int x : A) {
			if (bItems.contains(x + diff))
				return new int[] { x, x + diff };
		}

		return new int[] {};
	}

	public int singleNumber2(int[] nums) {
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			counts.putIfAbsent(nums[i], 0);
			counts.put(nums[i], counts.get(i) + 1);
			if (counts.get(i) == 3)
				counts.remove(nums[i]);
		}

		Iterator<Map.Entry<Integer, Integer>> itr = counts.entrySet().iterator();
		return itr.next().getKey();

	}

	public int search(int[] nums, int target) {
		return searchBinary(nums, target, 0, nums.length - 1, nums.length / 2);
	}

	public int searchBinary(int[] nums, int target, int i, int j, int pivot) {
		if (j < i)
			return -1;
		else if (nums[pivot] == target)
			return pivot;
		else if (nums[pivot] > target)
			return searchBinary(nums, target, i, pivot - 1, (i + i) / 2);
		else
			return searchBinary(nums, target, pivot + 1, j, (j + i) / 2);

	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;

		ListNode resultNode = null;
		if (l1.val <= l2.val) {
			resultNode = new ListNode(l1.val);
			l1 = l1.next;
		}

		else {
			resultNode = new ListNode(l2.val);
			l2 = l2.next;
		}

		ListNode l3Iterator = resultNode;

		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				l3Iterator.next = new ListNode(l1.val);
				l3Iterator = l3Iterator.next;
				l1 = l1.next;
			} else {
				l3Iterator.next = new ListNode(l2.val);
				l3Iterator = l3Iterator.next;
				l2 = l2.next;
			}
		}

		while (l1 != null) {
			l3Iterator.next = new ListNode(l1.val);
			l3Iterator = l3Iterator.next;
			l1 = l1.next;
		}

		while (l2 != null) {
			l3Iterator.next = new ListNode(l2.val);
			l3Iterator = l3Iterator.next;
			l2 = l2.next;
		}

		return resultNode;
	}

	public int largestSumAfterKNegations(int[] A, int K) {
		Arrays.sort(A);
		int sum = 0;
		int index = -1;
		for (int i = 0; i < A.length; i++) {
			if (K == 0)
				break;
			if (A[i] <= 0) {
				A[i] = A[i] * -1;
				K--;
			} else {
				index = i;
				break;
			}

		}

		if (K > 0 && K % 2 != 0)
			if (index == 0) {
				A[index] = A[index] * -1;
			} else {
				if (index + 1 < A.length) {
					int min = Math.min(Math.min(A[index - 1], A[index]), A[index + 1]);
					if (A[index - 1] == min)
						A[index - 1] = A[index - 1] * -1;
					else if (A[index] == min)
						A[index] = A[index] * -1;
					else
						A[index + 1] = A[index + 1] * -1;
				} else {
					if (A[index] <= A[index - 1])
						A[index] = A[index] * -1;
					else
						A[index - 1] = A[index - 1] * -1;
				}

			}

		for (int i : A) {
			sum = sum + i;
		}
		return sum;
	}

	public boolean lemonadeChange(int[] bills) {
		int noOfFives = 0;
		int noOfTens = 0;

		for (int i = 0; i < bills.length; i++) {
			if (bills[i] == 5)
				noOfFives++;
			else if (bills[i] == 10) {
				noOfTens++;
				noOfFives--;
			} else {
				if (noOfTens > 0) {
					noOfTens--;
					noOfFives--;

				} else {
					noOfFives = noOfFives - 3;
				}
			}

			if (noOfFives < 0)
				return false;
		}

		return true;
	}

	public char findTheDifference(String s, String t) {

		char result = 0;
		for (char c : s.toCharArray()) {
			result = (char) (result ^ c);
		}
		for (char c : t.toCharArray()) {
			result = (char) (result ^ c);
		}
		return ' ';

	}

	public int missingNumber(int[] nums) {
		if (nums.length == 0)
			return 0;

		int summationOfArray = 0;
		int sumAll = 0;
		for (int i = 0; i <= nums.length; i++) {
			sumAll = sumAll + i;
			if (i < nums.length)
				summationOfArray = summationOfArray + nums[i];
		}

		return sumAll - summationOfArray;
	}

	public int singleNumber(int[] nums) {

		int result = nums[0];
		for (int i = 1; i < nums.length; i++) {
			result = result ^ nums[i];
		}

		return result;
	}

	public int lastStoneWeight(int[] stones) {
		Arrays.sort(stones);

		for (int i = stones.length - 1; i > 0; i--) {
			stones[i - 1] = stones[i] - stones[i - 1];
			reArrangeArray(stones, i - 1);
		}

		return stones[0];
	}

	public void reArrangeArray(int[] stones, int index) {
		int tmp = 0;
		for (int i = index; i > 0; i--) {
			if (stones[i] < stones[i - 1]) {
				// swap
				tmp = stones[i];
				stones[i] = stones[i - 1];
				stones[i - 1] = tmp;
			} else
				return;
		}
	}

	public int sumRootToLeaf(TreeNode root) {

		if (root == null)
			return 0;

		StringBuilder binaryBuilder = new StringBuilder();
		List<Integer> values = new ArrayList<Integer>();

		calcBinarySumamtion(root, values, binaryBuilder);

		int sum = 0;
		for (Integer value : values) {
			sum = sum + value;
		}
		return sum;
	}

	public void calcBinarySumamtion(TreeNode node, List<Integer> values, StringBuilder binaryBuilder) {

		binaryBuilder.append(node.val);
		if (node.left == null && node.right == null) {
			// binaryBuilder.append(node.val);
			System.out.println(binaryBuilder.toString());
			values.add(Integer.parseInt(binaryBuilder.toString(), 2));
			binaryBuilder.deleteCharAt(binaryBuilder.length() - 1);
			return;
		}

		if (node.left != null) {
			calcBinarySumamtion(node.left, values, binaryBuilder);
		}

		if (node.right != null) {
			calcBinarySumamtion(node.right, values, binaryBuilder);
		}
		binaryBuilder.deleteCharAt(binaryBuilder.length() - 1);
	}

	public int[][] transpose(int[][] A) {

		int[][] transposedA = new int[A[0].length][A.length];
		for (int i = 0; i < transposedA.length; i++) {
			for (int j = 0; j < transposedA[i].length; j++) {
				transposedA[i][j] = A[j][i];
			}
		}

		return transposedA;
	}

	public int findComplement(int num) {
		String binary = Integer.toBinaryString(num);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < binary.length(); i++) {
			if (binary.charAt(i) == '0')
				sb.append(1);
			else
				sb.append(0);
		}

		return Integer.parseInt(sb.toString(), 2);
	}

	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> values = new ArrayList<Integer>();

		if (root == null)
			return values;
		// using recursion
		// postTraversalTree(root, values);
		// solve it using dfs
		Stack<TreeNode> dfs = new Stack<TreeNode>();
		dfs.add(root);
		Set<TreeNode> visited = new HashSet<TreeNode>();

		TreeNode top = null;
		while (!dfs.isEmpty()) {
			while (dfs.peek().left != null && !visited.contains(dfs.peek().left)) {
				dfs.add(dfs.peek().left);
			}

			if (dfs.peek().right != null && !visited.contains(dfs.peek().right)) {
				dfs.add(dfs.peek().right);
				continue;
			}

			if (dfs.peek().left == null && dfs.peek().right == null) {
				top = dfs.pop();
				visited.add(top);
				values.add(top.val);
			}

		}

		return values;
	}

	public void postTraversalTree(TreeNode root, List<Integer> values) {
		if (root == null)
			return;
		postTraversalTree(root.left, values);

		postTraversalTree(root.right, values);
		values.add(root.val);
	}

	public List<Integer> inorderTraversal(TreeNode root) {

		List<Integer> values = new ArrayList<Integer>();

		if (root == null)
			return values;

		// using recursion
		// getTreeValues(root, values);
		// solve it using DFS
		Stack<TreeNode> dfs = new Stack<TreeNode>();
		dfs.add(root);
		TreeNode top = null;
		while (!dfs.isEmpty()) {

			while (dfs.peek().left != null)
				dfs.add(dfs.peek().left);

			top = dfs.pop();
			values.add(top.val);
			while (top.right == null && !dfs.isEmpty()) {
				top = dfs.pop();
				values.add(top.val);
			}
			if (top.right != null)
				dfs.add(top.right);

		}

		return values;
	}

	public int minDiffInBST(TreeNode root) {

		List<Integer> values = new ArrayList<Integer>();

		getTreeValues(root, values);

		int minDiff = Integer.MAX_VALUE;
		for (int i = 1; i < values.size(); i++) {
			if (values.get(i) - values.get(i - 1) < minDiff)
				minDiff = values.get(i) - values.get(i - 1);
		}
		return minDiff;
	}

	public void getTreeValues(TreeNode root, List<Integer> values) {
		if (root == null)
			return;
		getTreeValues(root.left, values);
		values.add(root.val);
		getTreeValues(root.right, values);
	}

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid.length == 0 && obstacleGrid[0].length == 0)
			return 1;
		if (obstacleGrid[0][0] == 1 || obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1)
			return 0;

		obstacleGrid[0][0] = 1;
		long[][] mem = new long[obstacleGrid.length][obstacleGrid[0].length];

		for (int i = 0; i < mem.length; i++) {
			for (int j = 0; j < mem[i].length; j++) {
				mem[i][j] = obstacleGrid[i][j];
			}
		}

		for (int i = 1; i < mem.length; i++) {
			mem[i][0] = (mem[i][0] == 0 && mem[i - 1][0] == 1) ? 1 : 0;
		}

		for (int i = 1; i < mem[0].length; i++) {
			mem[0][i] = (mem[0][i] == 0 && mem[0][i - 1] == 1) ? 1 : 0;
		}

		for (int i = 1; i < mem.length; i++) {
			for (int j = 1; j < mem[i].length; j++) {
				if (mem[i][j] == 0) {
					mem[i][j] = mem[i - 1][j] + mem[i][j - 1];
				} else {
					mem[i][j] = 0;
				}
			}
		}

		return (int) mem[mem.length - 1][mem[0].length - 1];
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
