package stand;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.io.File;

public class Stand {
	private List<Veiculo> veiculos;
	private String nomeStand;

	public Stand(String s) {
		this.nomeStand = s;
		this.veiculos = new ArrayList<>();
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public String getNomeStand() {
		return nomeStand;
	}

	public void lerVeiculos(String fileName) {
		try {
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNext()) {
				String s = scanner.nextLine();
				String[] v = s.split(";");
				String m = v[0];
				int p = Integer.parseInt(v[1]);
				Data d = new Data(v[2]);
				if (Integer.parseInt(v[3]) == 2)
					veiculos.add(new Mota(m, p, d));
				else
					veiculos.add(new Carro(m, p, d));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("Erro a abrir ficheiro");
		}
	}

	public Stand(List<Veiculo> veiculos, String nomeStand) {
		super();
		this.veiculos = veiculos;
		this.nomeStand = nomeStand;
	}

	public int totalValor() {
		int total = 0;
		for (Veiculo v : veiculos)
			total = total + v.getPreco();
		return total;
	}

	public List<Veiculo> filtrarVeiculo(Predicate<Veiculo> p) {
		List<Veiculo> l = new ArrayList<Veiculo>();
		for (Veiculo v : veiculos) {
			if (p.test(v))
				l.add(v);
		}
		return l;
	}

	@Override
	public String toString() {
		String s = getNomeStand() + ";";
		for (Veiculo v : veiculos)
			s = s + v.toString() + ";";
		s = s + totalValor();
		return s;
	}
}