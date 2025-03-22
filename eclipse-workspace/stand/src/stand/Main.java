package stand;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		Stand stand = new Stand("Stand Maravilhoso");
		stand.lerVeiculos("veiculos.txt");
		System.out.println(stand);
		System.out.println("\nApenas os veiculos com preco inferior a 1000 €:");
// Completar linha seguinte usando expressão lambda
		List<Veiculo> veiculosBaratos = stand.filtrarVeiculo(v -> (v.getPreco() < 1000));
		veiculosBaratos.forEach(v -> System.out.println(v));
	}
}