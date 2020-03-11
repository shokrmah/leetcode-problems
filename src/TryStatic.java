
public class TryStatic {

	int myX;

	public TryStatic() {

	}

	public TryStatic(int myX) {
		this.myX = myX;
	}

	public TryStatic(int myX, String S) {

	}

	public void myVoid() {

	}

	public int myInt() {
		return 15;
	}

	public static TryStatic UpdateX(int myX) {
		return new TryStatic(myX);
	}

}
