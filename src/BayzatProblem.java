
public class BayzatProblem {

	public int NumberOfCollectingBalls(int n) {
		
		if(n == 0)
			return 0;
		if(n==1)
			return 1;
		
		//save the 2 previous numbers to use to calculate for the new one
		int prevPrevCount = 1; //initial case for 1 ball
		int prevCount = 2; //initial case for 2 balls
		
		int newCount =0;
		for (int i = 3; i <= n; i++) {
			newCount = prevPrevCount + prevCount;
			
			prevPrevCount = prevCount;
			prevCount = newCount;
		}
		
		return newCount;
	}
}
