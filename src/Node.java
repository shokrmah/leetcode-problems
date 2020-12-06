import java.util.List;

public class Node {
	public int val;
	
	public Node left;
	public Node right;
	public int data;
	
	
	public Node prev;
    public Node next;
    public Node child;
    public Node random;
    public List<Node> neighbors;
	
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
        next = null;
        random = null;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
