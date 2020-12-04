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
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Problems {

//	Given a array of numbers representing the stock prices of a company in 
	//chronological order, write a function that calculates the maximum 
//	profit you could have made from buying and selling that stock once. 
	//You must buy before you can sell it. 
//
//	For example, given [9, 11, 8, 5, 7, 10], you should return 5, 
	//since you could buy the stock at 5 dollars and sell it at 10 dollars. 

	// 1254

	// 4532

	public int nextGreaterElement(int n) {
		if (n <= 0)
			return -1;

		Integer[] numbers = new Integer[(int) (Math.log10(n) + 1)];

		int digit = 0;
		int tmpN = n;
		int index = 0;
		while (tmpN > 0) {
			digit = tmpN % 10;
			tmpN = tmpN / 10;
			numbers[index++] = digit;
		}

		int currentEnd = 1;
		while (currentEnd < numbers.length) {
			int result = greaterInteger(numbers, currentEnd);
			if (result > n)
				return result;

			currentEnd++;
		}

		return -1;

	}

	public int greaterInteger(Integer[] numbers, int endIndex) {
		int value = -1;
		int index1 = -1;
		int index2 = -1;
		for (int i = 0; i <= endIndex; i++) {
			value = numbers[i];
			for (int j = i + 1; j <= endIndex; j++) {
				if (numbers[j] < value) {
					index1 = i;
					index2 = j;
					break;
				}

			}
			if (index1 != -1)
				break;
		}

		if (index1 == -1)
			return -1;
		else {
			int tmp = numbers[index1];
			numbers[index1] = numbers[index2];
			numbers[index2] = tmp;

			Arrays.sort(numbers, 0, index2, Collections.reverseOrder());
			StringBuilder sb = new StringBuilder();
			for (int i = numbers.length - 1; i >= 0; i--) {
				sb.append(numbers[i]);
			}

			try {
				int result = Integer.parseInt(sb.toString());
				return result;
			} catch (NumberFormatException e) {
				return -1;
			}

		}
	}

	public int reverseBits(int n) {

		char[] binary = Integer.toBinaryString(n).toCharArray();

		int i = 0;
		int j = binary.length - 1;

		char c;
		while (i < j) {
			c = binary[i];
			binary[i] = binary[j];
			binary[j] = c;
			i++;
			j--;
		}

		String result = new String(binary);

		return Integer.parseInt(result, 2);
	}

	public int[] twoSumSorted(int[] numbers, int target) {
		// still this solltion works twoSum

		int i = 0;
		int j = numbers.length - 1;
		while (i < j) {
			if (numbers[i] + numbers[j] == target)
				return new int[] { i + 1, j + 1 };
			else if (numbers[i] + numbers[j] > target)
				j--;
			else
				i++;
		}

		return null;
	}

	public int[] twoSum(int[] nums, int target) {

		Map<Integer, Integer> myIndices = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			if (myIndices.containsKey((target - nums[i])))
				return new int[] { myIndices.get(target - nums[i]), i };
			else
				myIndices.put(nums[i], i);
		}
		return null;
	}

	public int maxProfit(int [] stockValues) {
		if(stockValues == null || stockValues.length == 0)
			return 0;
		
		int maxProfitUntilNow = -1;
		int maxProfitUntilhere = 0;

		for (int i = 1; i < stockValues.length; i++) {
			maxProfitUntilhere = maxProfitUntilhere + stockValues[i] - stockValues[i - 1];
			if (maxProfitUntilNow < maxProfitUntilhere)
				maxProfitUntilNow = maxProfitUntilhere;
			if (maxProfitUntilhere < 0)
				maxProfitUntilhere = 0;
		}

		return maxProfitUntilNow;
		
			}
	
	
	
	public int[] findErrorNums(int[] nums) {
		int[] count = new int[nums.length + 1];
		int dup = 0;
		for (int i = 0; i < nums.length; i++) {
			count[nums[i]]++;
			if (count[nums[i]] == 2)
				dup = nums[i];
		}

		int[] result = new int[2];
		result[0] = dup;

		for (int i = 1; i < count.length; i++) {
			if (count[i] == 0) {
				result[1] = i;
				break;
			}

		}

		return result;
	}

	public int findBottomLeftValue(TreeNode root) {
		if (root == null)
			return -1;

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		int mostLeftVal = 0;
		int size = 0;
		TreeNode tn = null;
		while (!q.isEmpty()) {
			size = q.size();
			mostLeftVal = q.peek().val;
			for (int i = 0; i < size; i++) {
				tn = q.poll();
				if (tn.left != null)
					q.add(tn.left);
				if (tn.right != null)
					q.add(tn.right);
			}
		}

		return mostLeftVal;
	}
	
	
//	Given a sorted array of strings that is interspersed with empty springs, write a function to find the location of a given string.
//
//	Example:
//	Input: ball, {“at”, “”, “”, “”, “ball”, “”, “”, “car”, “”, “”, “dad”,””, “”}
//	Output: 4
//	Given a sorted array of strings that is interspersed with empty springs, write a function to find the location of a given string.

	public int findString(String[] words, String key) {

//		for (int i = 0; i < words.length; i++) {
//			if (words[i].equals(key)) {
//				return i;
//			}
//		}
//
//		return -1;
		
		return binarySearchWord(words, key, 0, words.length - 1);
	}

	public int binarySearchWord(String[] words, String key, int startIndex, int endIndex) {
		if (startIndex > endIndex)
			return -1;

		int keyIndex = (startIndex + endIndex) / 2;

		if (words[keyIndex].equals(key))
			return keyIndex;
		else if (words[keyIndex].length() == 0) {
			// loop to the left
			boolean isFoundNewIndex = false;
			for (int i = keyIndex - 1; i >= startIndex; i--) {
				if (words[i].length() != 0) {
					if (words[i].equals(key))
						return i;
					else {
						keyIndex = i;
						isFoundNewIndex = true;
						break;
					}

				}
			}
			if (!isFoundNewIndex) {
				for (int i = keyIndex + 1; i < endIndex; i++) {
					if (words[i].length() != 0) {
						if (words[i].equals(key))
							return i;
						else {
							keyIndex = i;
							isFoundNewIndex = true;
							break;
						}

					}

				}
				if (!isFoundNewIndex)
					return -1;

			}
		}
		
		if(compareString(words[keyIndex], key) == 1) {
			return binarySearchWord(words, key, startIndex, keyIndex - 1);
		}
		else
			return binarySearchWord(words, key, keyIndex + 1, endIndex);

	}
	
	public int compareString(String word, String Key) {
		for (int i = 0; i < word.length(); i++) {
			if(word.charAt(i) < Key.charAt(i))
				return -1;
			else if(word.charAt(i) > Key.charAt(i))
				return 1;
		}
		return 0;
	}

	public TreeNode insertIntoBST(TreeNode root, int val) {
		if (root == null) {
			return new TreeNode(val);
		}

		TreeNode node = root;

		while (node != null) {
			if (val < node.val) {
				if (node.left == null) {
					node.left = new TreeNode(val);
					break;
				} else
					node = node.left;
			} else {
				if (node.right == null) {
					node.right = new TreeNode(val);
					break;
				} else
					node = node.right;
			}
		}

		return root;
	}



	public int maxNumberOfBalloons(String text) {
		int[] counts = new int[26];
		for (int i = 0; i < text.length(); i++) {
			counts[text.charAt(i) - 'a']++;
		}
		int countOfBallons = 0;
		// balloon
		counts['l' - 'a'] = counts['l' - 'a'] / 2;
		counts['o' - 'a'] = counts['o' - 'a'] / 2;

		countOfBallons = Math.min(Math.min(Math.min(Math.min(counts['a' - 'a'], counts['b' - 'a']), counts['l' - 'a']),
				counts['o' - 'a']), counts['n' - 'a']);

		return countOfBallons;
	}

	public String[] uncommonFromSentences(String A, String B) {
		Map<String, Integer> counts = new HashMap<String, Integer>();
		String[] arr = A.split(" ");
		String[] arr2 = B.split(" ");

		int i = 0;
		int length = Math.min(arr.length, arr2.length);

		for (; i < length; i++) {
			counts.put(arr[i], counts.getOrDefault(arr[i], 0) + 1);
			counts.put(arr2[i], counts.getOrDefault(arr2[i], 0) + 1);
		}

		if (length == arr.length) {
			for (; i < arr2.length; i++) {
				counts.put(arr2[i], counts.getOrDefault(arr2[i], 0) + 1);

			}
		} else {
			for (; i < arr.length; i++) {
				counts.put(arr[i], counts.getOrDefault(arr[i], 0) + 1);
			}
		}

		List<String> listResult = new ArrayList<String>();
		for (Map.Entry<String, Integer> item : counts.entrySet()) {
			if (item.getValue() == 1) {
				listResult.add(item.getKey());
			}

		}
		String[] result = new String[listResult.size()];

		for (int j = 0; j < result.length; j++) {
			result[j] = listResult.get(j);
		}

		return result;
	}

	public int maxArea(int[] height) {
		if (height.length < 2)
			return 0;

		int i = 0;
		int j = height.length - 1;

		int maxArea = 0;
		while (i < j) {
			maxArea = Math.max(Math.min(height[i], height[j]) * (j - i), maxArea);
			if (height[i] < height[j])
				i++;
			else
				j--;
		}

		return maxArea;

	}

	public int findKthLargest(int[] nums, int k) {
		if (nums.length == 0)
			return 0;

		Arrays.sort(nums);

		return nums[nums.length - k];
	}

	public List<List<String>> groupAnagrams(String[] strs) {

		List<List<String>> result = new ArrayList<List<String>>();
		if (strs.length == 0)
			return result;

		List<String> tmp = new ArrayList<String>();
		boolean found = false;

		String compare = "";
		for (int i = 0; i < strs.length; i++) {
			char[] word = strs[i].toCharArray();
			Arrays.sort(word);
			compare = new String(word);
			if (i == 0) {
				tmp.add(compare);
				tmp.add(strs[i]);
				result.add(tmp);
			} else {
				found = false;
				for (int j = 0; j < result.size(); j++) {
					if (result.get(j).size() > 0) {
						if (result.get(j).get(0).equals(compare)) {
							result.get(j).add(strs[i]);
							found = true;
							break;
						}

					}

				}
				if (!found) {
					tmp = new ArrayList<String>();
					tmp.add(compare);
					tmp.add(strs[i]);
					result.add(tmp);
				}
			}
		}
		for (int i = 0; i < result.size(); i++) {
			result.get(i).remove(0);
		}

		return result;
	}

	public int kthSmallest(int[][] matrix, int k) {
		if (matrix.length == 0 || matrix[0].length == 0)
			return 0;

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a1, a2) -> a1 - a2);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				pq.add(matrix[i][j]);
			}
		}
		while (k > 1) {
			pq.poll();
			k--;
		}
		return pq.poll();
	}

	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null)
			return null;

		ListNode faster = head;
		ListNode slower = head;
		boolean isCycle = false;
		while (faster != null && slower != null && faster.next != null) {
			slower = slower.next;
			faster = faster.next.next;
			if (slower == faster) {
				isCycle = true;
				break;
			}

		}

		if (!isCycle)
			return null;

		while (head != faster) {
			head = head.next;
			faster = faster.next;
		}

		return faster;
	}

	public int findDuplicate(int[] nums) {
		// leetcode solution
		int tortoise = nums[0];
		int hare = nums[0];
		do {
			tortoise = nums[tortoise];
			hare = nums[nums[hare]];
		} while (tortoise != hare);

		int ptr1 = nums[0];
		int ptr2 = tortoise;
		while (ptr1 != ptr2) {
			ptr1 = nums[ptr1];
			ptr2 = nums[ptr2];
		}

		return ptr1;
	}

	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		Map<Long, Integer> AplusBcounts = new HashMap<Long, Integer>();

		int result = 0;
		long key = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				key = A[i] + B[j];
				AplusBcounts.put(key, AplusBcounts.getOrDefault(key, 0) + 1);
			}

		}

		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < D.length; j++) {
				key = 0 - (C[i] + D[j]);
				result += AplusBcounts.getOrDefault(key, 0);
			}

		}

		return result;
	}

	public void rotate(int[][] matrix) {
		if (matrix.length <= 0)
			return;

		int length = matrix[0].length;
		for (int i = 0; i < matrix.length; i++) {
			int j = 0;
			int k = length - 1;
			int tmp = 0;
			while (j < k) {
				tmp = matrix[i][j];
				matrix[i][j] = matrix[i][k];
				matrix[i][k] = tmp;
				j++;
				k--;
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			int tmp = 0;
			for (int j = 0; j < length - i; j++) {
				tmp = matrix[i][j];
				matrix[i][j] = matrix[length - j - 1][length - i - 1];
				matrix[length - j - 1][length - i - 1] = tmp;
			}
		}

	}

	public int kthSmallest(TreeNode root, int k) {
		List<Integer> valuesSorted = new ArrayList<Integer>();
		findKthElement(root, valuesSorted);
		return valuesSorted.get(k - 1);
	}

	public void findKthElement(TreeNode node, List<Integer> valuesSorted) {

		if (node == null)
			return;

		findKthElement(node.left, valuesSorted);

		valuesSorted.add(node.val);

		findKthElement(node.right, valuesSorted);

	}

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(new ArrayList<Integer>());

		int newNumber = -1;
		List<Integer> newEntry;
		int size = 0;
		for (int i = 0; i < nums.length; i++) {
			newNumber = nums[i];
			size = result.size();
			for (int j = 0; j < size; j++) {
				newEntry = new ArrayList<Integer>();
				newEntry.addAll(result.get(j));
				newEntry.add(newNumber);
				result.add(newEntry);
			}
		}

		return result;
	}

	public int[] productExceptSelf(int[] nums) {
		int[] productToLeft = new int[nums.length];
		int[] productToRight = new int[nums.length];

		int[] result = new int[nums.length];
		productToLeft[0] = 1;
		productToRight[nums.length - 1] = 1;

		for (int i = 1; i < nums.length; i++)
			productToLeft[i] = nums[i - 1] * productToLeft[i - 1];

		for (int i = nums.length - 2; i >= 0; i--)
			productToRight[i] = nums[i + 1] * productToRight[i + 1];

		for (int i = 0; i < nums.length; i++)
			result[i] = productToRight[i] * productToLeft[i];

		return result;

	}

	public List<Integer> topKFrequent(int[] nums, int k) {
		List<Integer> result = new ArrayList<Integer>();
		if (nums.length == 0)
			return result;

		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			counts.putIfAbsent(nums[i], 0);
			counts.put(nums[i], counts.get(nums[i]) + 1);
		}

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((n1, n2) -> counts.get(n1) - counts.get(n2));

		for (Integer i : counts.keySet()) {
			pq.add(i);
			if (pq.size() > k)
				pq.poll();
		}

		while (!pq.isEmpty())
			result.add(0, pq.poll());

		// Collections.reverse(result);

		return result;

	}

	public int countPrimes(int n) {
		boolean prime[] = new boolean[n + 1];
		Arrays.fill(prime, true);

		for (int p = 2; p * p <= n; p++) {

			if (prime[p] == true) {
				for (int i = p * 2; i <= n; i += p)
					prime[i] = false;
			}
		}

		int count = 0;
		for (int i = 2; i < prime.length; i++) {
			if (prime[i])
				count++;
		}

		return count;
	}

	public int reverse(int x) {
		int result = 0;
		while (x != 0) {
			int firstNum = x % 10;
			x /= 10;
			if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && firstNum > 7))
				return 0;
			if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && firstNum < -8))
				return 0;
			result = result * 10 + firstNum;
		}
		return result;
	}

	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		if (strs.length == 1)
			return strs[0];

		int i = 0;
		StringBuilder sb = new StringBuilder();
		char c;
		while (i >= 0) {
			if (strs[0].length() > i) {
				c = strs[0].charAt(i);
			} else
				return sb.toString();

			for (int j = 1; j < strs.length; j++) {
				if (strs[j].length() > i) {
					if (strs[j].charAt(i) != c)
						return sb.toString();
				} else
					return sb.toString();

			}
			sb.append(c);
			i++;
		}

		return sb.toString();
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null)
			return null;

		ListNode itA = headA;
		ListNode itB = headB;
		while (itA != itB) {
			if (itA == null)
				itA = headB;
			else
				itA = itA.next;
			if (itB == null)
				itB = headA;
			else
				itB = itB.next;
		}

		return itA;
	}

	public boolean isPalindrome(ListNode head) {
		if (head == null)
			return true;
		List<Integer> myList = new ArrayList<Integer>();

		while (head != null) {
			myList.add(head.val);
			head = head.next;
		}

		int i = 0;
		int j = myList.size() - 1;
		while (i < j) {
			if (!myList.get(i).equals(myList.get(j)))
				return false;
			i++;
			j--;
		}

		return true;

	}

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = m - 1;
		int j = n - 1;
		int k = m + n - 1;
		while (i >= 0 && j >= 0) {
			if (nums1[i] >= nums2[j]) {
				nums1[k] = nums1[i];
				i--;
			} else {
				nums1[k] = nums2[j];
				j--;
			}

			k--;
		}

		while (j >= 0) {
			nums1[k] = nums2[j];
			j--;
			k--;
		}

		while (i >= 0) {
			nums1[k] = nums1[i];
			i--;
			k--;
		}

	}

	public int trailingZeroes(int n) {
		if (n < 5)
			return 0;

		long count = 0;
		long divider = 5;
		while (n >= divider) {
			count = count + (n / divider);
			divider = divider * 5;
		}
		return (int) count;
	}

	public long factorial(int n) {

		if (n <= 2)
			return n;

		return n * factorial(n - 1);

	}

	public boolean isValid(String s) {

		if (s.length() == 0)
			return true;
		if (s.length() % 2 != 0)
			return false;

		Stack<Character> myStack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if (c == '(' || c == '{' || c == '[')
				myStack.push(c);
			else {
				if (!myStack.isEmpty()) {
					char c2 = myStack.pop();
					if (!((c2 == '(' && c == ')') || (c2 == '[' && c == ']') || (c2 == '{' && c == '}')))
						return false;
				} else
					return false;
			}
		}
		if (!myStack.isEmpty())
			return false;
		return true;
	}

	public boolean hasCycle(ListNode head) {

		if (head == null || head.next == null)
			return false;

		ListNode faster = head.next;
		ListNode slower = head;
		while (faster != null) {
			if (slower == faster)
				return true;
			slower = slower.next;
			if (faster.next == null)
				return false;
			faster = faster.next.next;
		}

		return false;
	}

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

	public int maxProfit2(int[] prices) {
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

}
				
				
				