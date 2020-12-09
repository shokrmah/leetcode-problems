
public class PairString implements Comparable<PairString>{

	public String first;
	public String second;
	
	public PairString(String first, String second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public int compareTo(PairString ps) {
		// TODO Auto-generated method stub
		int compare = this.second.compareTo(ps.second);
		if(compare == 0)
			compare = this.first.compareTo(ps.second);
		return compare;
	}
}
