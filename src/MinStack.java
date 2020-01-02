import java.util.LinkedList;

public class MinStack {

	LinkedList<Integer> myStack;
	int min = Integer.MAX_VALUE;

	/** initialize your data structure here. */
	public MinStack() {
		myStack = new LinkedList<Integer>();
	}

	public void push(int x) {
		myStack.add(0, x);

		if (x < min)
			min = x;
	}

	public void pop() {
		if (!isEmpty()) {
			boolean minChanged = false;
			if (min == top()) {
				min = Integer.MAX_VALUE;
				minChanged = true;
			}

			myStack.remove(0);
			if(minChanged)
				min = calcMin();
		}

	}

	public int top() {
		if (!isEmpty())
			return myStack.get(0);
		return -1;
	}

	public int getMin() {

		return min;
	}

	public boolean isEmpty() {
		return (myStack.size() > 0) ? false : true;
	}

	public int calcMin() {
		if (isEmpty())
			return Integer.MAX_VALUE;

		int minlocal = Integer.MAX_VALUE;
		for (int i = 0; i < myStack.size(); i++) {
			if (myStack.get(i) < minlocal)
				minlocal = myStack.get(i);
		}
		
		return minlocal;
	}

}
