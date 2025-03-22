package stand;

public class Mota extends Veiculo {
	private final int numRodas = 2;

	public Mota(String m, int p, Data d) {
		super(m, p, d);
	}

	public int getNumRodas() {
		return this.numRodas;
	}

	@Override
	public String toString() {
		return "Mota: " + super.toString();
	}
}