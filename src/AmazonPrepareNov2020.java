import java.util.ArrayList;
import java.util.Arrays;
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
import java.util.TreeSet;

public class AmazonPrepareNov2020 {

	public int twoSumUniquePairs(int[] numbers, int target) {
		if (numbers == null || numbers.length == 0)
			return 0;

		int count = 0;

		Map<Integer, Boolean> numbersMap = new HashMap<Integer, Boolean>();

		for (int i = 0; i < numbers.length; i++) {
			if (numbersMap.containsKey(target - numbers[i])) {
				if (!numbersMap.get(target - numbers[i])) {
					count++;
					numbersMap.put(numbers[i], true);
					numbersMap.put(target - numbers[i], true);
				}
			} else {
				numbersMap.put(numbers[i], false);
			}
		}

		return count;
	}

	public List<String> FetchItemsDisplay(Map<String, int[]> items, int sortParameter, int sortOrder, int itemsPerPage,
			int pageNumber) {
		if (items == null || items.size() == 0 || sortParameter > 2 || sortParameter < 0 || sortOrder > 1
				|| sortOrder < 0 || itemsPerPage <= 0 || pageNumber < 0)
			return new ArrayList<String>();

		List<String> itemsResult = new ArrayList<String>();

		PriorityQueue<List<String>> pq = new PriorityQueue<List<String>>(new Comparator<List<String>>() {
			public int compare(List<String> a, List<String> b) {
				// ASCENDING
				if (sortOrder == 0) {
					if (sortParameter == 0)
						return a.get(sortParameter).compareTo(b.get(sortParameter));
					else
						return Integer.valueOf(a.get(sortParameter)) - Integer.valueOf(b.get(sortParameter));
				} else { // DESCENDING
					if (sortParameter == 0)
						return b.get(sortParameter).compareTo(a.get(sortParameter));
					else
						return Integer.valueOf(b.get(sortParameter)) - Integer.valueOf(a.get(sortParameter));
				}
			}
		});

		// ADD HASHMAPS KEY-VALUE AS LIST<STRING> INTO PRIORITYQUEUE
		for (Map.Entry<String, int[]> item : items.entrySet()) {
			pq.add(Arrays.asList(String.valueOf(item.getKey()), String.valueOf(item.getValue()[0]),
					String.valueOf(item.getValue()[1])));
		}

		if (pageNumber > 0) {
			int itemNumber = itemsPerPage * (pageNumber);
			while (!pq.isEmpty() && itemNumber > 0) {
				pq.poll();
				itemNumber--;
			}
		}
		while (!pq.isEmpty() && itemsPerPage > 0) {
			itemsResult.add(pq.poll().get(0));
			itemsPerPage--;
		}

		return itemsResult;
	}

	
	public int chemicalDeliverySystem(List<Integer> requiremets, int flaskTypes,
            int totalMarks, List<PairInt> markings) {
		Map<Integer, TreeSet<Integer>> flaskMarkings = new HashMap<>();
		for (PairInt p : markings) {
			flaskMarkings.putIfAbsent(p.first, new TreeSet<>());
			flaskMarkings.get(p.first).add(p.second);
		}
		int minWaste = Integer.MAX_VALUE;
		int minWasteIndex = -1;
		for (int i = 0; i < flaskTypes; i++) {
			TreeSet<Integer> tempSet = flaskMarkings.get(i);
			int wasteAmnt = 0;
			boolean breaked = false;
			for (Integer req : requiremets) {
				Integer nextHigherMark = tempSet.ceiling(req);
				if (nextHigherMark != null)
					wasteAmnt += (nextHigherMark - req);
				else {
					breaked = true;
					break;
				}
			}
			if (!breaked && minWaste > wasteAmnt) { // since we iterating from i=0 if some case where
													// minWaste and wasteAmnt are same we do not care.
													// as min i will win which in this case is already winning.
				minWaste = wasteAmnt;
				minWasteIndex = i;
			}
		}

		return minWasteIndex;
	}

	public int multiprocessorSystem(int[] ability, long processes) {
		// could be o(n) time by using array and fill it with abilities
		// and loop from the end to the begining

		if (ability == null || ability.length == 0)
			return 0;

		int time = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		for (int i = 0; i < ability.length; i++) {
			pq.add(ability[i]);
		}

		int top = 0;
		while (processes > 0) {
			top = pq.poll();
			if (top == 1) {
				time = time + (int) processes;
				break;
			}
			processes = processes - top;
			top = top / 2;
			if (top > 0)
				pq.add(top);

			time++;
		}

		return time;
	}

	public int[] Turnstile(int[] times, int[] directions) {
		if (times == null || directions == null || times.length == 0 || times.length != directions.length)
			return new int[] {};

		int[] result = new int[times.length];

		Queue<Integer> entry = new LinkedList<Integer>();
		Queue<Integer> exit = new LinkedList<Integer>();
		for (int i = 0; i < times.length; i++) {
			if (directions[i] == 0)
				entry.add(i);
			else
				exit.add(i);
		}

		int time = 0;
		int prev = -1;
		int entryTime = 0, exitTime = 0;
		while (!entry.isEmpty() || !exit.isEmpty()) {
			if (!entry.isEmpty()) {
				if (times[entry.peek()] < time)
					entryTime = time;
				else
					entryTime = times[entry.peek()];
			} else
				entryTime = Integer.MAX_VALUE;

			if (!exit.isEmpty()) {
				if (times[exit.peek()] < time)
					exitTime = time;
				else
					exitTime = times[exit.peek()];
			} else
				exitTime = Integer.MAX_VALUE;

			if (Math.min(entryTime, exitTime) > time) {
				time = Math.min(entryTime, exitTime);
				prev = -1;
				continue;
			}

			if (entryTime == exitTime) {
				if (prev == -1 || prev == 1) {
					prev = 1;
					result[exit.remove()] = time;
				} else {
					prev = 0;
					result[entry.remove()] = time;
				}
			} else if (entryTime < exitTime) {
				prev = 0;
				result[entry.remove()] = time;
			} else {
				prev = 1;
				result[exit.remove()] = time;
			}
			time++;
		}

		return result;

	}

	public int utilaztionCheck(int[] avgUtilizations, int instances) {
		if (avgUtilizations == null || avgUtilizations.length == 0)
			return instances;

		for (int i = 0; i < avgUtilizations.length; i++) {
			if (avgUtilizations[i] < 25 && instances != 1) {
				instances = (int) Math.ceil((instances / 2.0));
				i = i + 10;
			} else if (avgUtilizations[i] > 60) {
				long multiply = instances * 2;
				if (multiply < 200000000) {
					instances = (int) multiply;
				}
				i = i + 10;
			}
		}

		return instances;
	}

	public class WordCount implements Comparable<WordCount> {
		public String word;
		public int count;

		public WordCount(String word, int count) {
			this.word = word;
			this.count = count;
		}

		@Override
		public int compareTo(WordCount wc1) {
			int r = Integer.compare(this.count, wc1.count);
			if (r == 0)
				r = this.word.compareTo(wc1.word);

			return r;
		}

	}

	public List<String> topKfrequentWord(List<String> reviews, List<String> keywords, int k) {
		List<String> topKWords = new ArrayList<String>();

		if (reviews == null || reviews.size() == 0)
			return topKWords;

		if (keywords.size() <= 1) {
			return keywords;
		}

		Map<String, WordCount> keywordMap = new HashMap<String, WordCount>();

		for (int i = 0; i < keywords.size(); i++) {
			keywordMap.put(keywords.get(i), new WordCount(keywords.get(i), 0));
		}

		String[] words;

		for (int i = 0; i < reviews.size(); i++) {
			words = reviews.get(i).toLowerCase().replaceAll("[^a-zA-Z ]", "").split(" ");

			for (int j = 0; j < words.length; j++) {
				if (keywordMap.containsKey(words[j]))
					keywordMap.get(words[j]).count++;
			}

		}

		PriorityQueue<WordCount> pq = new PriorityQueue<WordCount>();

		for (Map.Entry<String, WordCount> keyValues : keywordMap.entrySet()) {
			pq.add(keyValues.getValue());
			if (pq.size() > k) {
				pq.poll();
			}
		}

		while (!pq.isEmpty()) {
			topKWords.add(0, pq.poll().word);
		}

		return topKWords;

	}

	public int RoboticsChallange(String[] ops) {

		Stack<Integer> stack = new Stack<Integer>();
		int totalScore = 0;
		for (String op : ops) {
			if (op.equals("+")) {
				int top = stack.pop();
				int newtop = top + stack.peek();
				stack.push(top);
				stack.push(newtop);
				totalScore = totalScore + stack.peek();
			} else if (op.equals("Z")) {
				totalScore = totalScore - stack.peek();
				stack.pop();
			} else if (op.equals("X")) {
				stack.push(2 * stack.peek());
				totalScore = totalScore + stack.peek();
			} else {
				stack.push(Integer.valueOf(op));
				totalScore = totalScore + stack.peek();
			}

		}

		return totalScore;

	}

	public int numIslands(char[][] grid) {
		int islandsCount = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '1') {
					dfs(grid, i, j);
					islandsCount++;
				}
			}
		}

		return islandsCount;
	}

	public void dfs(char[][] grid, int i, int j) {
		if (i >= grid.length || i < 0 || j >= grid[i].length || j < 0)
			return;

		if (grid[i][j] == '1') {
			grid[i][j] = '2';
			dfs(grid, i + 1, j);
			dfs(grid, i - 1, j);
			dfs(grid, i, j + 1);
			dfs(grid, i, j - 1);

		}
	}

	public String mostCommonWord(String paragraph, String[] banned) {
		Map<String, Integer> counts = new HashMap<String, Integer>();
		Set<String> bannedSet = new HashSet<String>();
		for (String bannedWord : banned) {
			bannedSet.add(bannedWord);
		}

		String[] result = new String[] { "" };
		int[] max = new int[] { -1 };

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < paragraph.length(); i++) {
			if ((paragraph.charAt(i) >= 'a' && paragraph.charAt(i) <= 'z')
					|| (paragraph.charAt(i) >= 'A' && paragraph.charAt(i) <= 'Z')) {
				sb.append(paragraph.charAt(i));
			} else {
				UpdateValues(sb, counts, bannedSet, max, result);
			}
		}

		if (sb.length() > 0) {
			UpdateValues(sb, counts, bannedSet, max, result);
		}

		return result[0];
	}

	private void UpdateValues(StringBuilder sb, Map<String, Integer> counts, Set<String> bannedSet, int[] max,
			String[] result) {
		String word = sb.toString().toLowerCase();
		sb.setLength(0);
		if (word.length() > 0 && !bannedSet.contains(word)) {
			counts.putIfAbsent(word, 0);
			int count = counts.get(word) + 1;
			counts.put(word, count);
			if (count > max[0]) {
				max[0] = count;
				result[0] = word;
			}
		}
	}

	public List<String> SubstringK(String text, int k) {
		// int result = 0;

		List<String> result = new ArrayList<String>();
		if (text == null || text.length() == 0 || k == 0)
			return result;

		Set<String> words = new HashSet<String>();

		int[] chars = new int[27];

		int i = 0;
		int windowSize = 0;
		String word = "";
		while (i < text.length()) {
			chars[text.charAt(i) - 'a']++;
			windowSize++;
			if (windowSize == k) {
				word = text.substring(i - k + 1, i + 1);
				if (!words.contains(word) && isDistinict(chars)) {
					result.add(word);
					words.add(word);
				}
				chars[word.charAt(0) - 'a']--;
				windowSize--;
			}
			i++;
		}

		return result;
	}

	public boolean isDistinict(int[] chars) {
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] > 1)
				return false;
		}

		return true;
	}

	public int CutOfRank(int[] scores, int cutRank) {
		if (scores == null || scores.length == 0 || cutRank == 0)
			return 0;

		int[] counts = new int[101];

		for (int i = 0; i < scores.length; i++) {
			counts[scores[i]]++;
		}

		int result = 0;
		int rank = 1;
		for (int i = counts.length - 1; i >= 1; i--) {
			if (rank > cutRank)
				break;
			result = result + counts[i];
			rank = rank + counts[i];
		}

		return result;

	}

	public List<String> TransactionsLogs(List<String> logs, int threshold) {
		List<String> ids = new ArrayList<String>();

		if (logs == null || logs.size() == 0)
			return ids;

		Map<String, Integer> counts = new HashMap<String, Integer>();

		String[] log;

		for (int i = 0; i < logs.size(); i++) {
			log = logs.get(i).split(" ");

			counts.putIfAbsent(log[0], 0);
			counts.put(log[0], counts.get(log[0]) + 1);

			if (!log[0].equals(log[1])) {
				counts.putIfAbsent(log[1], 0);
				counts.put(log[1], counts.get(log[1]) + 1);
			}
		}

		for (Map.Entry<String, Integer> countMap : counts.entrySet()) {
			if (countMap.getValue() >= threshold)
				ids.add(countMap.getKey());
		}

		return ids;

	}

	public class PairPercentage implements Comparable<PairPercentage> {
		public int index;
		public int fiveCounts;
		public int totalReviews;
		private float percentage;

		public float getpercentage() {
			return percentage;
		}

		public void updatePercentage() {
			percentage = (((float) fiveCounts + 1) / ((float) totalReviews + 1))
					- ((float) fiveCounts / (float) totalReviews);
		}

		public PairPercentage(int index, int fiveCounts, int totalReviews) {
			this.index = index;
			this.fiveCounts = fiveCounts;
			this.totalReviews = totalReviews;
			updatePercentage();
		}

		@Override
		public int compareTo(PairPercentage pp1) {
			int result = Float.compare(pp1.percentage, this.percentage);
			if (result == 0)
				result = Integer.compare(this.fiveCounts, pp1.fiveCounts);
			return result;
		}
	}

	public int FiveStarSellers(int[][] products, int threshold) {
		if (products == null || products.length == 0)
			return 0;

		int min = 0;

		float currentPercentage = 0;

		PriorityQueue<PairPercentage> heap = new PriorityQueue<PairPercentage>();

		for (int i = 0; i < products.length; i++) {
			PairPercentage pp = new PairPercentage(i, products[i][0], products[i][1]);
			heap.add(pp);
			currentPercentage = currentPercentage + ((float) products[i][0] / (float) products[i][1]);
		}

		if ((currentPercentage / products.length) * 100 >= threshold)
			return min;

		while ((currentPercentage / products.length) * 100 < threshold) {
			PairPercentage pp = heap.poll();
			currentPercentage = currentPercentage + pp.percentage;
			pp.fiveCounts++;
			pp.totalReviews++;
			pp.updatePercentage();
			heap.add(pp);
			min++;

		}

		return min;

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

	public List<Integer> ItemsInContainers(String text, int[] startIndeces, int[] endIndeces) {
		List<Integer> counts = new ArrayList<Integer>();
		if (text == null || text.length() == 0)
			return counts;

		int start = -1, end = -1;
		int currentCount = 0;
		int totalCount = 0;
		boolean isMarkFound = false;
		for (int i = 0; i < endIndeces.length; i++) {
			start = startIndeces[i] - 1;
			end = endIndeces[i] - 1;

			while (start <= end && end < text.length()) {
				if (text.charAt(start) == '*') {
					if (isMarkFound)
						currentCount++;
				} else {
					if (isMarkFound) {
						totalCount = totalCount + currentCount;
						currentCount = 0;
					} else {
						isMarkFound = true;
					}
				}
				start++;
			}

			counts.add(totalCount);
			currentCount = 0;
			totalCount = 0;
			isMarkFound = false;
		}

		List<Integer> locations = new ArrayList<Integer>();

		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '|') {
				locations.add(i);
			}
		}

		if (locations.size() <= 1) {
			for (int i = 0; i < endIndeces.length; i++) {
				counts.add(0);
			}
		} else {

			int index = -1;

			int starCount = 0;
			for (int i = 0; i < endIndeces.length; i++) {
				index = findStartIndex(locations, startIndeces[i] - 1, 0, locations.size() - 1);
				if (index == -1)
					index++;
				while (index < locations.size() && locations.get(index) < startIndeces[i] - 1)
					index++;
				while (index + 1 < locations.size()) {
					if (locations.get(index + 1) <= endIndeces[i] - 1) {
						starCount = starCount + (locations.get(index + 1) - locations.get(index) - 1);
					} else {
						break;
					}
					index++;
				}
				counts.add(starCount);
				starCount = 0;
			}
		}
		return counts;
	}

	public int findStartIndex(List<Integer> locations, int target, int startIndex, int endIndex) {
		if (endIndex >= startIndex) {
			int mid = (startIndex + endIndex) / 2;

			if (locations.get(mid) == target)
				return mid;
			else if (locations.get(mid) < target)
				return findStartIndex(locations, target, mid + 1, endIndex);
			else
				return findStartIndex(locations, target, startIndex, mid - 1);
		}

		return endIndex;
	}

	public int AmazonMusicPairs(int[] numbers) {
		if (numbers == null || numbers.length == 0)
			return 0;

		int[] mods = new int[61];

		int count = 0;

		int mod = -1;
		for (int i = 0; i < numbers.length; i++) {
			mod = numbers[i] % 60;

			count = count + mods[(60 - mod) % 60];

			mods[mod]++;
		}

		return count;
	}

	public int AmazonFreshPromotion(List<List<String>> codeList, List<String> order) {
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
						// inject here that I can add to queue

						if (codeList.get(listOneIndex).get(listTwoIndex) == order.get(k)
								|| codeList.get(listOneIndex).get(listTwoIndex).equals("anything")) {
							k++;
							listTwoIndex++;
							if (k == order.size() && listOneIndex == codeList.size() - 1
									&& listTwoIndex == codeList.get(listOneIndex).size())
								return 1;
						} else
							break;

					} else
						return 1;
				}
			}

		}

		return 0;

	}

	public int AmazonFulfillmentBuilder(int[] ropes) {
		int cost = 0, totalCost = 0;
		if (ropes == null || ropes.length == 0)
			return totalCost;

		if (ropes.length == 1)
			return ropes[0];

		Queue<Integer> heap = new PriorityQueue<Integer>();

		for (int i = 0; i < ropes.length; i++) {
			heap.add(ropes[i]);
		}

		while (heap.size() > 1) {
			cost = heap.poll() + heap.poll();
			heap.add(cost);
			totalCost = totalCost + cost;
		}

		return totalCost;
	}

	// Aircraft Route Optimization same idea
	public List<int[]> AircraftRout(List<int[]> forward, List<int[]> backword, int maxDistance) {

		List<int[]> result = new ArrayList<int[]>();

		if (forward == null || backword == null || forward.size() == 0 || backword.size() == 0)
			return result;

		Collections.sort(forward, (i, j) -> j[1] - i[1]);
		Collections.sort(backword, (i, j) -> j[1] - i[1]);

		int i = 0;
		int j = backword.size() - 1;
		int minDiff = Integer.MAX_VALUE;

		int sum = -1;
		while (i < forward.size()) {
			sum = forward.get(i)[1] + backword.get(j)[1];
			if (sum > maxDistance)
				i++;
			else {
				if (maxDistance - sum == minDiff) {
					if (forward.get(i)[0] > result.get(0)[0])
						result.add(new int[] { forward.get(i)[0], backword.get(j)[0] });
					else
						result.add(0, new int[] { forward.get(i)[0], backword.get(j)[0] });
				} else if (maxDistance - sum < minDiff) {
					result.clear();
					result.add(new int[] { forward.get(i)[0], backword.get(j)[0] });
					minDiff = maxDistance - sum;
				}

				j--;
				if (j < 0)
					break;
			}

		}

		return result;

	}

	public List<int[]> primeAirRoute(List<int[]> firstPairs, List<int[]> secondPairs, int target) {

		List<int[]> result = new ArrayList<int[]>();

		if (firstPairs == null || secondPairs == null || firstPairs.size() == 0 || secondPairs.size() == 0)
			return result;

		Collections.sort(firstPairs, (i, j) -> j[1] - i[1]);
		Collections.sort(secondPairs, (i, j) -> j[1] - i[1]);

		int i = 0;
		int j = secondPairs.size() - 1;
		int minDiff = Integer.MAX_VALUE;

		int sum = -1;
		while (i < firstPairs.size()) {
			sum = firstPairs.get(i)[1] + secondPairs.get(j)[1];
			if (sum > target)
				i++;
			else {
				if (target - sum == minDiff) {
					if (firstPairs.get(i)[0] > result.get(0)[0])
						result.add(new int[] { firstPairs.get(i)[0], secondPairs.get(j)[0] });
					else
						result.add(0, new int[] { firstPairs.get(i)[0], secondPairs.get(j)[0] });
				} else if (target - sum < minDiff) {
					result.clear();
					result.add(new int[] { firstPairs.get(i)[0], secondPairs.get(j)[0] });
					minDiff = target - sum;
				}

				j--;
				if (j < 0)
					break;
			}

		}

		return result;
	}
}
