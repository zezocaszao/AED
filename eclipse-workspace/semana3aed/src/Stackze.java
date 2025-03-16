//versao ze
import java.util.Scanner;

public class Stackze {
	String[] stack;

	public Stackze() {
		// TODO Auto-generated constructor stub
		this.stack = new String[0];
	}

	public void push(String s) {
		String[] aux = stack;
		stack = new String[stack.length + 1];
		for (int i = 0; i < stack.length; i++) {
			if (i == stack.length) {
				stack[i] = s;
			}

			stack[i] = aux[i];
		}

	}

	public String pop() {
		return stack[stack.length];
	}

	public static void main(String[] args) {
		Stackze stackze = new Stackze();
		Scanner sc = new Scanner(System.in);
		while (true) {
			String word = sc.next();
			if (word.equals("end"))
				break;
			if (word.equals("-"))
				System.out.println(stackze.pop());
			else
				stackze.push(word);
		}
		sc.close();
	}

}
