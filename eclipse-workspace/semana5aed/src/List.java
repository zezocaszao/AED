//import List.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class List<T> implements Iterable<T> {
	private class Node {
		public T item;
		public Node next, prev;
	}

	private Node first;
	private Node last;
	private int size;

	public List() {
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

		if (first == null) {
			first = last;
		} else {
			oldLast.next = last;
			last.prev = oldLast;
		}
		size++;
	}

	public T get(int index) {
		// return the element at position index
		if (index <= 0 || index > size()) {
			throw new IllegalArgumentException("tas aquem ou tas alem");
		}
		Node aux = first;
		for (int n = 1; n != index; n++) {
			aux = aux.next;
		}
		return aux.item;
	}

	public T remove(int index) {
		// remove and return the element at position index
		if (index <= 0 || index > size()) {
			throw new IllegalArgumentException("tas alem ou tas aquem");
		}
		Node n = first;

		for (int i = 1; i < index ; i++) {
			// System.out.println(n.item);
			n = n.next;

		}
		Node aux = n;
		if (n != last && n != first) {
			n.prev.next = n.next;
			n.next.prev = n.prev;

		} else if (n == last) {
			last = n.prev;

		} else if (n == first) {
			first = n.next;
		}
		n.item = null;
		size--;
		return aux.item;
	}

	public boolean removeFirst(T item) {
		// remove the first occurrence of item; return false if nothing was removed,
		// true
		if (isEmpty()) {
			throw new IllegalStateException("ta vazio ze");
		}
		
		int aux = size();

		Node n = first;
		int i = 1;
		while (true) {

			if (n.item.equals(item)) {
				remove(i);
				break;
			} else {
				n = n.next;
				i++;
				
			}
		}
		// System.out.println("primeiro i: " +i);
		
		//System.out.println("segundo i: " + i);
		//System.out.println("size" + size());

		if (aux == size()) {
			return false;
		}
//			throw new IllegalArgumentException("nao vi zezoca");
		return true;

	}

	public boolean removeLast(T item) {
		// remove the last occurrence of item; return false if nothing was removed, true
		// otherwise
		if (isEmpty()) {
			throw new IllegalStateException("ta vazio ze");
		}
		Node n = last;
		int i = size();
		while (n.item != item) {
			// System.out.println("xd");
			n = n.prev;
			i--;
		}
		remove(i);
		if (i != size()) {
			return true;
		}
//			throw new IllegalArgumentException("nao vi zezoca");
		return false;

	}

	public boolean removeAll(T item) {
		if (isEmpty()) {
			throw new IllegalStateException("ta vazio ze");
		}
		boolean removed = false;
		Node current = first;

		while (current != null) {
			if (current.item.equals(item)) {
				Node nextNode = current.next;

				if (current == first) {
					first = current.next;
					if (first != null) {
						first.prev = null;
					}
				} else if (current == last) {
					last = current.prev;
					if (last != null) {
						last.next = null;
					}
				} else {
					current.prev.next = current.next;
					current.next.prev = current.prev;
				}

				size--;
				removed = true;
				current = nextNode; // Avança para o próximo nó
			} else {
				current = current.next; // Avança para o próximo nó apenas se não for removido
			}
		}

		return removed;
	}

	public boolean isEmpty() {
		// is the list empty?
		return size == 0;
	}

	public boolean contains(T item) {
		// does the list contain item?
		if (isEmpty()) {
			throw new IllegalStateException("ta vazio ze");
		}
		Node current = first;

		while (current != null) {
			if (item.equals(current.item))
				return true;
			current = current.next;
		}
		return false;
	}

	public int size() {
		// number of elements in the list
		return size;

	}

	public boolean isPalindrome() {
		// is the list a palindrome*?
//		if(isEmpty())
//			throw new IllegalStateException("fodase bro vai dormir");
		if (size() == 1)
			return true;
		Node j = last;
		for (Node i = first; i != last; i = i.next) {
			if (i.item.equals(j.item))
				j = j.prev;
			else
				return false;
			if (i == j)
				break;
		}
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node current = first;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException("nao ha zezoca");
				}
				T item = current.item;
				current = current.next;
				return item;
			}

		};
	}

	public static void main(String[] args) {
		List ze = new List();
		ze.add("ze1");// 1
		ze.add("ze");// 2
		ze.add("ze");// 3
		ze.add("ze4");// 4
		ze.add("ze1");// 5
		System.out.println(ze.isPalindrome());
		System.out.println("tamanho: " + ze.size());
		System.out.println("contem ze1? :  " + ze.contains("ze1"));
		ze.removeFirst("ze");
		System.out.println("tamanho depois de removeFirst: " + ze.size());
System.out.println("segundo : " + ze.first.next);
//		ze.removeLast("ze1");
//		System.out.println("tamanho depois de removeLast: " + ze.size());
//		for (int i = 1; i <= ze.size(); i++) {
//			System.out.println(ze.get(i));
//		}
//		 ze.removeAll("ze1");
//		 System.out.println("tamanho depois de removeAll: " + ze.size());

		for (int i = 1; i <= ze.size(); i++) {
			System.out.println(ze.get(i));
		}

	}
}