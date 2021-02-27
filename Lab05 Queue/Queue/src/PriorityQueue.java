import java.util.*;

public class PriorityQueue {
	MyQueue q;
	ArrayList<Integer> pq;

	public PriorityQueue(MyQueue q) {
		super();
		this.q = q;
		pq = new ArrayList<Integer>();
	}

	// implement this.
	public void push(int x) throws Exception {
		pq.add(x);
		Collections.sort(pq);

	}

	// implement this.
	public void pop() throws Exception {
		pq.remove(0);
	}

	// implement this
	public int top() throws Exception {
		return (int) pq.get(0);
	}

}
