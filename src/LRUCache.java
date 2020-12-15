import java.util.HashMap;
import java.util.Map;


public class LRUCache {

	 class Node{
	        int key;
	        int val;
	        Node prev;
	        Node next;

	        public Node(int key, int val){
	            this.key = key;
	            this.val = val;
	        }
	    }
	    
	    int capacity;
	    Map<Integer,Node> myMap;
	    Node head, tail;
	    
	    public LRUCache(int capacity) {
	        this.capacity = capacity;
	        myMap = new HashMap<Integer,Node>();
	    }
	    
	    public int get(int key) {
	        Node node = myMap.get(key);
	        if(node == null)
	            return -1;
	        
	        myMap.remove(key);
	        delete(node);
	        
	        myMap.put(key, add(key, node.val));
	        return node.val;   
	    }
	    
	    public void put(int key, int value) {
	        Node node = myMap.get(key);
	        if(node!=null) {
	            myMap.remove(key);
	            delete(node);
	        }
	        if(myMap.size()==capacity){
	            myMap.remove(head.key);
	            delete(head);
	        }
	        myMap.put(key, add(key, value));
	    }
	    
	    public Node add(int key, int val){
	         Node node = new Node(key, val);
	        if(head==null){
	            head = node;
	            tail = node;
	        }
	        else{
	            tail.next = node;
	            node.prev = tail;
	            tail = node;
	        }
	        return node;
	    }
	    
	    public void delete(Node node){
	        if(node.next==null){    //if node is tail
	            Node prev = node.prev;
	            
	            if(prev!=null) 
	                prev.next = null;
	            
	              tail = prev;
	             
	            if(head==node) 
	                head = null;
	        }
	        else if(node.prev==null){   //if node is head
	            if(head==tail){
	                head = null;
	                tail = null;
	            }
	            else{
	                head.next.prev = null;
	                head = head.next;
	            }
	        }
	        else{
	            Node pre = node.prev;
	            Node nxt = node.next;
	            pre.next = nxt;
	            nxt.prev = pre;
	        }
	    }
}
