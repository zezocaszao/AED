
public class QuickFindUF {
	private int[] id;

	public QuickFindUF(int N) {// constructor (N is the number of points)

		if (N <= 0) {
			throw new IllegalArgumentException("Valor de N invalido");
		}
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}

	public boolean connected(int p, int q) { // are p and q connected?
		return id[p] == id[q];		// 2 -> 1 
	}

	public void union(int p, int q) { // connect p and q
		int idp = id[p];
		int idq = id[q];

		for (int i = 0; i < id.length; i++) {
			if (id[i] == idp) {
				id[i] = idq;		//2*N + 2 -> N (linear)
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		QuickFindUF q = new QuickFindUF(4);
		q.union(2, 3);
		System.out.println(q.connected(2, 3));
		System.out.println(q.connected(2, 1));
		
	}
	
//	Doubling Hypothesis
//
//	N		T(N)		ratio		lg(ratio)
//	250		0,000		0,000		-Infinity
//	500		0,000		Infinity		Infinity
//	1000		0,001		1,500		0,585
//	2000		0,001		1,000		0,000
//	4000		0,000		0,667		-0,585
//	8000		0,001		2,000		1,000
//	16000		0,002		2,750		1,459
//	32000		0,004		1,727		0,788
//	64000		0,005		1,211		0,276
//	128000		0,006		1,348		0,431
//
//	a = 3.91788443712717E-5
//	b = 0.43063435432986236
//
//	T(N) = 3.91788443712717E-5 * N^0.43063435432986236
//
//	T(10^9) = 0.7932227565925969 seconds

}
