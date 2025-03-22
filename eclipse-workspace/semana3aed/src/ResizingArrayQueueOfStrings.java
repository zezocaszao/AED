public class ResizingArrayQueueOfStrings {  //este codigo deu 0, porque o construtor devia estar inicializado a -1 no first e no last. mas funciona bem
	private String[] q;
	private int first;
	private int last;

	public ResizingArrayQueueOfStrings(int capacity) { // constructor
		q = new String[capacity];
		first = 0;
		last = 0;
	}

	public void enqueue(String item) { // add item to the queue

		if (size() == q.length) {
			resize(q.length * 2);
			// System.out.println("resizezao");
		}

		if (first == last) {
			q[first] = item;
			last++;
			// System.out.println("caso1");
		} else if (q[last] == null && !(first == last)) {
			q[last] = item;
			// System.out.println("caso2");
		} else {
			last++;
			q[last] = item;
			// System.out.println("caso3");
		}

//		last++;
//		q[last] = item;

	}

	public String dequeue() { // remove and return the least recently added item
		if (isEmpty()) {
			throw new IllegalStateException("fila vazia");
		}

		String aux = q[first];
		q[first] = null;
		first++;

		if (first == last) {
			first = 0;
			last = 0;
		}

		if (size() > 0 && size() == (q.length / 4)) {
			resize(q.length / 2);
		}

		return aux;
	}

	public boolean isEmpty() { // is the queue empty?
		return last == first;
	}

	public int size() { // number of items in the queue
		if (isEmpty()) {
			return 0;
		} else {
			return last - first + 1;
		}
	}

	public void shift() { // move the last element to the start of the queue
		String u = q[last];

		for (int i = last; i != first; i--) {
			q[i] = q[i - 1];
		}

		q[first] = u;
		
	}

	public void resize(int i) {
		// TODO Auto-generated method stub
		//System.out.println("resisado");
		int l = 0;
		String[] copy = new String[i];
		for (int c = first; c < last + 1; c++) {
			copy[c - first] = q[c];
			l++;
			// System.out.println("Array: " + q[c]);
		}

		q = copy;
		last = l;
		first = 0;

	}

	public static void main(String[] args) {
		ResizingArrayQueueOfStrings r = new ResizingArrayQueueOfStrings(4);
		// System.out.println(r.size() + " " + r.q.length);

		r.enqueue("0");
		r.enqueue("1");
		r.enqueue("2");
		r.enqueue("3");
		r.enqueue("4");

		for (int i = r.first; i <= r.last; i++) {
			System.out.println("enqueue:" + " " + r.q[i]);
		}

		System.out.println(r.size() + " size length " + r.q.length);
//
//		System.out.println("first : " + r.first);
//
//		r.dequeue();
//		System.out.println(" 1dfirst : " + r.first);
//		System.out.println(r.size() + " size length " + r.q.length);
//
//		r.dequeue();
//		System.out.println("2d first : " + r.first);
//		System.out.println(r.size() + " size length " + r.q.length);
//
//		r.dequeue();
//		System.out.println("3dfirst : " + r.first);
//		System.out.println(r.size() + " size length " + r.q.length);

//		r.dequeue();
//		r.dequeue();
//		r.dequeue();
//		for (int i = r.first; i <= r.last - 1; i++) {
//			System.out.println("dequeue:" + " " + r.q[i]);
//		}
		System.out.println("first:" + " " + r.q[r.first]);
		System.out.println("last:" + " " + r.q[r.last]);
		r.shift();
		System.out.println("first:" + " " + r.q[r.first]);
		System.out.println("last:" + " " + r.q[r.last]);
		for (int i = r.first; i <= r.last; i++) {
			System.out.println("shift:" + " " + r.q[i]);
		}

	}
}
