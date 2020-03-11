import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class BloombergProblems {
//3[a2[c]]
	public String decodeString(String s) {

		Stack<String> myStack = new Stack<String>();

		StringBuilder tmpSb = new StringBuilder();

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			myStack.add("" + s.charAt(i));
		}

		while (!myStack.isEmpty()) {

		}

		return tmpSb.toString();
	}

	public int[][] candyCrush(int[][] board) {

		return null;
	}

	public List<String> invalidTransactions(String[] transactions) {
		if (transactions == null || transactions.length == 0)
			return new ArrayList<String>();

		List<String> result = new ArrayList<String>();

		Map<Integer, String> transMap = new HashMap<Integer, String>();
		List<Transaction> transList = new ArrayList<Transaction>();

		for (int i = 0; i < transactions.length; i++) {
			String[] values = transactions[i].split(",");

			Transaction tmp = new Transaction(i, values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]),
					values[3]);
			transList.add(tmp);
			transMap.put(i, transactions[i]);
		}

		Collections.sort(transList);

		for (int i = 0; i < transList.size(); i++) {
			for (int j = i + 1; j < transactions.length; j++) {
				if (Math.abs(transList.get(i).time - transList.get(j).time) <= 60) {
					if (transList.get(i).name.equals(transList.get(j).name)
							&& !transList.get(i).city.equals(transList.get(j).city)) {

						if (!transList.get(i).isAdded) {
							result.add(transMap.get(transList.get(i).id));
							transList.get(i).isAdded = true;
						}

						if (!transList.get(j).isAdded) {
							result.add(transMap.get(transList.get(j).id));
							transList.get(j).isAdded = true;
						}

					}
				} else
					break;

			}

			if (!transList.get(i).isAdded) {
				if (transList.get(i).amount > 1000)
					result.add(transMap.get(transList.get(i).id));
			}
		}

		return result;
	}

	public Node flatten(Node head) {

		if (head == null)
			return null;

		List<Node> toComplete = new ArrayList<Node>();

		Node mover = head;

		while (mover != null || toComplete.size() > 0) {

			if (mover.child != null) {
				Node tmp = mover.next;
				if (tmp != null)
					toComplete.add(0, tmp);
				mover.next = mover.child;
				mover = mover.next;
			} else {
				if (mover.next == null) {
					if (toComplete.size() > 0) {
						mover.next = toComplete.get(0);
						toComplete.remove(0);
					}

				}

				mover = mover.next;
			}
		}

		mover = head;
		while (mover.next != null) {
			mover.next.prev = mover;
			mover = mover.next;
			mover.child = null;
		}

		head.child = null;

		return head;
	}

	public int twoCitySchedCost(int[][] costs) {

		Arrays.sort(costs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Math.abs(o1[0] - o1[1]) - (Math.abs(o2[0] - o2[1]));
			}
		});

		int n = costs.length / 2;
		int countA = 0;
		int countB = 0;
		int total = 0;

		for (int i = 0; i < costs.length; i++) {
			if (countA < n && countB < n) {
				if (costs[i][0] < costs[i][1]) {
					countA++;
					total = total + costs[i][0];
				} else {
					countB++;
					total = total + costs[i][1];
				}
			} else if (countA < n)
				total = total + costs[i][0];
			else
				total = total + costs[i][1];
		}

		return total;
	}

	public class Transaction implements Comparable<Transaction> {
		int id;
		String name;
		int time;
		int amount;
		String city;
		boolean isAdded = false;

		public Transaction(int id, String name, int time, int amount, String city) {
			this.id = id;
			this.name = name;
			this.time = time;
			this.amount = amount;
			this.city = city;
		}

		@Override
		public int compareTo(Transaction t) {
			// TODO Auto-generated method stub
			return this.time - t.time;
		}

	}

}
