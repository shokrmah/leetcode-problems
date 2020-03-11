import java.util.HashMap;
import java.util.Map;


public class LRUCache {

	public class Pair implements Comparable<Pair>{
		int key;
		int repeated;
		@Override
		public int compareTo(Pair P) {
			// TODO Auto-generated method stub
			return this.repeated - P.repeated;
		}
		
	}
	
	Map<Integer, Integer> values;
	int capacity;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		values = new HashMap<Integer, Integer>((int) (capacity / 0.75) + 2);
	}

	public int get(int key) {
		if (values.containsKey(key))
			return values.get(key);

		return -1;
	}

	public void put(int key, int value) {

		if (values.size() == capacity) {
			
		}
		
		values.put(key, value);
	}
}
