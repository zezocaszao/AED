import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
	private Node first;
	private Node last;
	private int size;

	public Queue() {
		// constructor
		first = null;
		last = null;
		// first.next = last;
		size = 0;
	}

	// classe aninhada / nested
	private class Node {
		public T item;
		public Node next;
		public Node previous; // ta double linked

	}

	public void enqueue(T item) {
		// add item to the queue
		Node old = last;

		if (isEmpty()) {
			first = new Node();
			first.item = item;
			last = first;
		} else {
			last = new Node();
			last.item = item;
			old.next = last;
			last.previous = old;

		}

		size++;
	}

	public T dequeue() {
		// remove and return the least recently added item
		if (isEmpty())
			throw new IllegalStateException("ta vazio zezoca");

		T aux = first.item;
		// Node aux = first;
		first = first.next;
		size--;

		return aux;
	}

	public boolean isEmpty() {
		// is the queue empty?
		return first == null;// se o size == 0, tbm dá
	}

	public int size() {
		// number of items in the queue
		return size;
	}

	public void shift() {
		// move the last element to the start of the queue
		// last tem q passar a ser o next do first, e a refenciado last tem q ser
		// atualizado
		// podes acrescentar um no previous.
		if (isEmpty())
			throw new IllegalStateException("ta vazio zezoca");
		
		if(size() == 1)
			throw new IllegalStateException("ta so com 1 zezoca");

		

		//1
		Node penultimo = last.previous;
		
		first.previous = last;
		last.next = first;
		last.previous = null;
		penultimo.next = null;
		
		
		first = last;
		last = penultimo;

		
	}

	public Iterator<T> iterator() {
		return new QueueIterator();
		// return the iterator for this queue
	}

	private class QueueIterator implements Iterator<T> {

		private Node current = first;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			T item = current.item;
			current = current.next;
			return item;
		}

	}

	public static void main(String[] args) {
		Queue<String> q = new Queue<>();
		q.enqueue("A");
		q.enqueue("B");
		q.enqueue("C");
//		q.enqueue("D");
//		q.enqueue("E");
		
		System.out.println("Initial queue: ");
		for (String i : q) {
			System.out.println(i);
		}
//		System.out.println("first: " + q.first.item);
//		System.out.println("first.next: " + q.first.next.item);
//		System.out.println("last: " + q.last.item);
//		System.out.println("last.previous: " + q.last.previous.item);
		System.out.println("Dequeue: " + q.dequeue());
//		System.out.println("Dequeue: " + q.dequeue());
		q.shift();
		System.out.println("Shift!");
		System.out.println("After shift: ");
		for (String i : q) {
			System.out.println(i);
		}
		System.out.println("Dequeue: " + q.dequeue());
		
		System.out.println("Remaining elements in queue: ");
		for (String i : q) {
			System.out.println(i);
		}
	}
}