public class QueueOfStrings {
	String[] q;
	int first;
	int N;

	QueueOfStrings(int capacity) { // Cria uma fila vazia
		q = new String[capacity];
		first = 0;
		N = 0;
	}

	void enqueue(String item) { // Insere uma nova String na fila
		if (N == q.length) {
			// throw new IllegalStateException("zezoca ta cheio");
			// substituir para resize
			resize(q.length * 2);
		}
		q[convert(N + first)] = item;
		N++;
		// ou só q[convert(first+N++)]= item;

	}

	public String dequeue() { // Remove e devolte a String inserida amais tempo
		if (isEmpty()) {
			throw new IllegalStateException("zezoca ta vazio");
		}
		if (N == (q.length / 4)) {
			resize(q.length / 2);
		}
		String aux = q[first];
		q[first] = null;
		first = convert(first + 1);
		return aux;
	}

	boolean isEmpty() { // Verifica se a fila está vazia
		return first == N;
	}

	private void resize(int capacity) {// pode tar mal
		String[] copy = new String[capacity];
		for (int i = 0; i < N; i++)
			copy[i] = q[i];
		q = copy;
	}

	private int convert(int i) { // nao funciona para i<0
		return i % q.length;
	}

	public int size() {
		return N;
	}

	public static void main(String[] args) {

		QueueOfStrings q = new QueueOfStrings(3);
		System.out.println(q.q.length);
		System.out.println(q.size());
		q.enqueue("zezoca");

		q.enqueue("zezoca");

		q.enqueue("zezoca");
		
		q.enqueue("zezoca");
		System.out.println(q.size());

		q.enqueue("zezoca");
		System.out.println(q.size());

	}
}