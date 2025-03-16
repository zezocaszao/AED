
public class Stack {
	String[] s;
	int N;

	public Stack(int capacity) {
		// TODO Auto-generated constructor stub
		s = new String[capacity];
		N = 0;
	}
	
	public void push(String h) {
		if(N == s.length) {
			throw new IllegalStateException("zezoca ta cheio");
		}
		
		s[N++] = h;
	}
	
	public String pop() {
		if(N == 0) {
			throw new IllegalStateException("zezoca ta vazio");
		}
		String n = s[--N];
		s[N] = null;
		return n;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack l = new Stack(4);
		l.push("ze");
		l.push("ze");
		l.push("ze");
		l.push("ze");
		l.pop();
		System.out.println(l.N);
		
	}

}
