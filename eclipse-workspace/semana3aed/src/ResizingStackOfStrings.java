
public class ResizingStackOfStrings {
	String[] s;
	int N;

	public ResizingStackOfStrings(int capacity) {
		// TODO Auto-generated constructor stub
		s = new String[capacity];
		N = 0;
	}
	
	public void push(String h) {
		if (N == s.length)
			resize(2 * s.length);
		
		s[N++] = h;
	}
	
	public String pop() {
		if(N == 0) {
			throw new IllegalStateException("zezoca ta vazio");
		}
		if(N <= (0.25*s.length)) {
			resize(s.length/2);
		}
		String n = s[--N];
		s[N] = null;
		return n;
	}
	
	private void resize(int capacity) {
		String[] copy = new String[capacity];
		for (int i = 0; i < N; i++)
		copy[i] = s[i];
		s = copy;
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResizingStackOfStrings l = new ResizingStackOfStrings(4);
		l.push("ze");
		l.push("ze");
		l.push("ze");
		l.push("ze");
		l.push("ze");
		l.push("ze");
		System.out.println(l.N + " "+ l.s.length );

		l.pop();
		l.pop();
		l.pop();
		l.pop();
		l.pop();

		System.out.println(l.N + " "+ l.s.length );
		
	}

}
