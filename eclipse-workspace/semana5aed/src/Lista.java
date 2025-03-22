//import List.Node;

public class Lista<T> { //feito pela stora
	
	private class Node {
		public T item;
		public Node next;
	}
	
	private Node first;
	private Node last;
	private int size;

	public Lista() {
		// constructor
		first = null;
		last = null;
		size = 0;
	}

	public void add(T item) {
		// add item to the end of the list
		Node oldLast = last;
		
		last = new Node();
		last.item = item;
		
		if(first== null) //se a lista estava vazia
			first = last;
		else
			oldLast.next = last;
		size++;
	}

//	public T get(int index) {
//		// return the element at position index
//		return item;
//	}
//
//	public T remove(int index) {
//		// remove and return the element at position index
//	}
//
//	public boolean removeFirst(T item) {
//		// remove the first occurrence of item; return false if nothing was removed,
//		// true otherwise
//	}
//
//	public boolean removeLast(T item) {
//		// remove the last occurrence of item; return false if nothing was removed, true
//		// otherwise
//	}
//
//	public boolean removeAll(T item) {
//		// remove all occurrences of item; return false if nothing was removed, true
//		// otherwise
//	}

	public boolean isEmpty() {
		// is the list empty?
		return size == 0;//ou first == null
	}

	public boolean contains(T item) {
		// does the list contain item?
		Node current = first;
		
		while ( current != null) {
			if(item.equals(current.item))
				return true;
			current = current.next;
		}
		return false;
	}

	public int size() {
		// number of elements in the list
		return size;
	}


	
	public static void main(String[] args) {
		List<String> list = new List<String>();
		
		list.add("mesa");
		list.add("mesa");
		list.add("mesa");
		list.add("mesa");
		
		System.out.println(list.contains("mesa"));


	}


}