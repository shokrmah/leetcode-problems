import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KthLargest {

	List<Integer> values;

	int kth;

	public KthLargest(int k, int[] nums) {
		values = new ArrayList<Integer>();
		kth = k;

		for (int i = 0; i < nums.length; i++) {
			values.add(nums[i]);
		}
		
		Collections.sort(values, Collections.reverseOrder());
	}

	public int add(int val) {

		insertItem(val, 0, values.size() - 1);
		return values.get(values.size() - kth + 1);
	}

	private void insertItem(int val, int start, int end) {

		if (start >= end) {
			values.add(start + 1, val);
			return;
		}

		int med = (start + end) / 2;
		if (values.get(med) == val) {
			values.add(med, val);
			return;
		} else if (values.get(med) > val) {
			insertItem(val, start, med - 1);
		} else {
			insertItem(val, med + 1, end);
		}

	}
}
