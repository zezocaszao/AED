package stand;

public class Carro extends Veiculo{
	private final int numRodas = 4;
	
	public Carro(String m, int p, Data d) {
		super(m,p,d);
	}
	
	public int getNumRodas() {
		return this.numRodas;
	}
	
	@Override
	public String toString() {
		return "Carro: " + super.toString();
	}
}