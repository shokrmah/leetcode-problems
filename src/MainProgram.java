import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AmazonPrepareNov2020 ap = new AmazonPrepareNov2020();
	
		

		List<PairInt> markings = new ArrayList<PairInt>();
		
		markings.add(new PairInt(0, 3));
		markings.add(new PairInt(0, 5));
		markings.add(new PairInt(0, 9));
		
		markings.add(new PairInt(1, 8));
		markings.add(new PairInt(1, 6));
		markings.add(new PairInt(1, 9));
		
		markings.add(new PairInt(2, 3));
		markings.add(new PairInt(2, 5));
		markings.add(new PairInt(2, 6));
		
		List<Integer> m = new ArrayList<Integer>();
		m.add(6);
		m.add(7);
		m.add(6);
		m.add(4);
		
		 System.out.println(ap.chemicalDeliverySystem(m ,3,9,markings));   

		
		
	}

}
