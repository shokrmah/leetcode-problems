import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class RandomProblems {

	public List<String> letterCombinations(String digits) {
		if(digits == null || digits.length() == 0)
			return new ArrayList<String>();
	
		Map<Character,List<String>> telephoneMap = new HashMap<Character, List<String>>();
		telephoneMap.put('2', new ArrayList<String>(Arrays.asList("a", "b", "c")));
		telephoneMap.put('3', new ArrayList<String>(Arrays.asList("d", "e", "f")));
		telephoneMap.put('4', new ArrayList<String>(Arrays.asList("g", "h", "i")));
		telephoneMap.put('5', new ArrayList<String>(Arrays.asList("j", "k", "l")));
		telephoneMap.put('6', new ArrayList<String>(Arrays.asList("m", "n", "o")));
		telephoneMap.put('7', new ArrayList<String>(Arrays.asList("p", "q", "r","s")));
		telephoneMap.put('8', new ArrayList<String>(Arrays.asList("t", "u", "v")));
		telephoneMap.put('9', new ArrayList<String>(Arrays.asList("w", "x", "y", "z")));
		
		List<String> result = new ArrayList<String>();
		calculateCombinations(digits.toCharArray(),result, telephoneMap);
		
		
		return result;
	}
	
	public void calculateCombinations(char[] digits, List<String> result,Map<Character,List<String>> telephoneMap) {
		for (int i = 0; i < digits.length; i++) {
			
		}
	}
	

	public void sortColors(int[] nums) {

		if (nums == null || nums.length == 0)
			return;

		int zeros = 0;
		int ones = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0)
				zeros++;
			else if (nums[i] == 1)
				ones++;
		}

		for (int i = 0; i < nums.length; i++) {
			if (zeros > 0) {
				nums[i] = 0;
				zeros--;
			} else if (ones > 0) {
				nums[i] = 1;
				ones--;
			} else
				nums[i] = 2;
		}
	}

	public int getKth(int lo, int hi, int k) {

		Map<Integer, Integer> countOfStepsMap = new HashMap<Integer, Integer>();
		countOfStepsMap.put(1, 0);
		PriorityQueue<ValueCount> queue = new PriorityQueue<ValueCount>();

		int count = 0;
		for (int i = lo; i <= hi; i++) {
			count = stepsCount(i, countOfStepsMap);
			countOfStepsMap.put(i, count + 1);
			queue.add(new ValueCount(i, count + 1));
			if (queue.size() > k)
				queue.poll();
		}

		return queue.poll().val;
	}

	public int stepsCount(int val, Map<Integer, Integer> countOfStepsMap) {
		if (val == 1)
			return 0;

		if (val % 2 == 0)
			val = val / 2;
		else
			val = (val * 3) + 1;

		if (countOfStepsMap.containsKey(val))
			return countOfStepsMap.get(val);

		countOfStepsMap.put(val, stepsCount(val, countOfStepsMap) + 1);

		return countOfStepsMap.get(val);
	}

	public int minCostClimbingStairs(int[] cost) {
		if (cost == null || cost.length < 2)
			return 0;
		else if (cost.length == 2)
			return Math.min(cost[0], cost[1]);

		int minCost = 0;

		int i = 0;
		while (i < cost.length - 2) {
			if (cost[i] + cost[i + 2] < cost[i + 1]) {
				minCost = minCost + cost[i] + cost[i + 2];
				i = i + 3;
			} else {
				minCost = minCost + cost[i + 1];
				i = i + 2;
			}
		}

		return minCost;
	}

	public class ValueCount implements Comparable<ValueCount> {

		int val;
		int count;

		public ValueCount(int val, int count) {
			this.val = val;
			this.count = count;
		}

		@Override
		public int compareTo(ValueCount vc) {
			// TODO Auto-generated method stub
			if (this.count == vc.count)
				return vc.val - this.val;
			else
				return vc.count - this.count;
		}

	}
}
