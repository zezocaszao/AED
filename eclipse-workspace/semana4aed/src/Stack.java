import java.util.Iterator;

public class Stack<T> implements Iterable<T>{
	//a stora fez em strings, depois fez em Ts(generico)
	//o garbagecollector limpa da memoria nós vazios, ao contrariode C.
	//arrays, estruturade dados que ja sao do java, sao iterable.
	private Node first;
	private int size;

	public Stack() {
		// TODO Auto-generated constructor stub
		first = null;
		size = 0;
	}
	
	//classe aninhada
	private class Node {
		public T item;
		public Node next;
	}
	
	public void push(T item) {
		Node oldFirst = first;
		
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		
		size++;
	}
	
	public T pop() {
		if(first == null)
			throw new IllegalStateException("ta vazio zezoca");
		
		T item = first.item;
		first= first.next;
		size--;
		
		return item;
	}
	
	public int size() {
		return size;//se o size == 0, tbm dá
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new StackIterator();
	}
	
	private class StackIterator implements Iterator<T>{

		private Node current = first;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			T item =current.item;
			current = current.next;
			return item;
		}
		
	}
	
	
	@Override
	public String toString() {
		return "Stack [first=" + first + ", size=" + size + "]";
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<String> s = new Stack<>();
		s.push("A");
		s.push("B");
		s.push("C");
		
		System.out.println("first: "+s.first.item);
		System.out.println("first.next: "+s.first.next.item);
		
		s.pop();
		System.out.println("novo first, depois de pop: "+s.first.item);

		
		for(String i: s){//Integer
			System.out.println(i);
		}
	}

	

	

}
