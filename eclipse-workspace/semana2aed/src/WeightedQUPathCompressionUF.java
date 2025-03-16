
public class WeightedQUPathCompressionUF {
	int[] pai;
	int[] sz;

	public WeightedQUPathCompressionUF(int N) {// constructor (N is the number of points)

		if (N < 0)
			throw new IllegalArgumentException("N invalido");

		sz = new int[N];

		pai = new int[N];
		for (int i = 0; i < N; i++) {
			pai[i] = i;
			sz[i] = 1; //nao sei se devia ser 0 ou 1 
		}
	}

	public int root(int p) {// root of p
		while (p !=pai[p] ) {
			
			pai[p] = pai[pai[p]];//opçao 2 dos slides
			
			p = pai[p];
		}
		return p;
	}

	public boolean connected(int p, int q) {// are p and q connected?
		return root(p) == root(q);

	}

	public void union(int p, int q) {// connect p and q

		int rp = root(p);
		int rq = root(q);

		if (rp == rq)
			return;
		if (sz[rp] < sz[rq]) {
			pai[rp] = rq;
			sz[rq] += sz[rp];
		} else {
			pai[rq] = rp;//pai do 3 passa a ser o 2
			sz[rp] += sz[rq];
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeightedQUPathCompressionUF q = new WeightedQUPathCompressionUF(5);
		q.union(2, 3);
		q.union(3, 4);
		System.out.println(q.connected(2, 3));
		System.out.println(q.connected(2, 4));
		System.out.println(q.connected(1, 3));


		System.out.println(q.root(2));
		System.out.println(q.root(3));
		System.out.println(q.root(4));
	}
	
	
	
//	Doubling Hypothesis
//
//	N		T(N)		ratio		lg(ratio)
//	250		0,000		0,000		-Infinity
//	500		0,000		Infinity		Infinity
//	1000		0,001		2,500		1,322
//	2000		0,000		0,200		-2,322
//	4000		0,001		3,000		1,585
//	8000		0,002		3,667		1,874
//	16000		0,003		1,455		0,541
//	32000		0,014		4,313		2,109
//	64000		0,011		0,826		-0,276
//	128000		0,011		0,982		-0,026
//
//	a = 0.01512276138409018
//	b = -0.02553509210713765
//
//	T(N) = 0.01512276138409018 * N^-0.02553509210713765
//
//	T(10^9) = 0.008400017262930921 seconds

}
