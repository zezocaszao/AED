
public class iris {
	private String[] q;
	private int first, last;
	
	public iris(){   //constructor 
		q = new String[1];
		first = -1; last = -1;
	}
	
	public void enqueue(String item){ //add item to the queue
		if(first == -1)
			first = 0;
		q[next(last)] = item;
		last++; 	
		if(size() == q.length)
			resize(q.length*2);
	}
	
	public String dequeue(){ //remove and return the least recently added item
		if(size() == 0)
			throw new IllegalStateException("Error: Queue underflow");
		String item = q[first];
		q[first] = null;
		if(first == last){
			first = -1;
		} else {
			first = next(first);
		}
		if(size() == (q.length/4))
			resize(q.length/2);
		return item;
	}
	
	public boolean isEmpty(){ //is the queue empty?
		return first == -1;
	}
	
	public int size(){ //number of items in the queue
		if(first == -1)
			return 0;
		else if(first <= last)
			return (last-first+1);
		else 
			return (q.length-first+last+1);
	}
	
	public void shift(){ //move the last element to the start of the queue
		String itemL = q[last];
		q[last] = null;
		last = previous(last);
		first = previous(first);
		q[first] = itemL;
	}
	
	
	private void resize(int capacity){ 
		String[] copy = new String[capacity];
		int j = first;
		for(int i = 0; i < size(); i++){
			copy[i] = q[j];
			j = next(j);
		}
		q = copy;		
	}
	
	private int next(int i){
		return ((i + 1) % q.length);
	}
	
	private int previous(int i){
		return ((q.length + i - 1) % q.length);
	}
	
	public static void main(String[] args){
		iris q = new iris();

//		q.enqueue("a");
//		q.enqueue("b");
//		q.enqueue("c");
//		System.out.println(q.dequeue());
//		System.out.println(q.size());
//		q.shift();
//		System.out.println(q.dequeue());
//		System.out.println(q.dequeue());
//		System.out.println(q.isEmpty());
		
		q.enqueue("1");
		q.enqueue("2");
		q.enqueue("3");
		q.enqueue("4");
		q.enqueue("5");
		// System.out.println(r.size() + " " + r.q.length);
		for (int i = q.first; i <= q.last; i++) {
			System.out.println("antes shift" + " " + q.q[i]);
		}
		
		q.shift();
		
		for (int i = q.first; i <= q.last; i++) {
			System.out.println("depois shift" + " " + q.q[i]);
		}
	}
}
