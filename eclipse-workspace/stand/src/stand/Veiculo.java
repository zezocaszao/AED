package stand;

public abstract class Veiculo {
	private String matricula;
	private int preco;
	private Data dataCompra;

	public Veiculo(String m, int p, Data d) {
		this.matricula = m;
		this.preco = p;
		this.dataCompra = d;
	}

	public String getMatricula() {
		return matricula;
	}

	public int getPreco() {
		return preco;
	}

	public Data getDataCompra() {
		return dataCompra;
	}

	public abstract int getNumRodas();

	@Override
	public String toString() {
		String s = getMatricula() + ";" + getPreco() + ";" + getDataCompra() + ";" + getNumRodas();
		return s;
	}
}