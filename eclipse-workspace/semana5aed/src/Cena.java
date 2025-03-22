import java.util.Stack;

public class Cena {

    public static void main(String[] args) {
        int n = 50;
        Stack<Integer> stack = new Stack<>();

        // Converte o número para binário e empilha os restos
        while (n > 0) {
            stack.push(n % 2);
            n = n / 2;
        }

        // Desempilha e imprime os dígitos binários
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }

        System.out.println();
    }
}
