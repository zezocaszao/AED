
public class QuickUnionUF {
	private int[] pai;

	public QuickUnionUF(int N) {// constructor (N is the number of points)
		if(N<0)
			throw new IllegalArgumentException("N invalido");
		
		pai = new int[N];
		for (int i = 0; i < N; i++)
			pai[i] = i;
	}

	public int root(int p) {
		while (pai[p] != p) {
			p = pai[p];
		}
		return p;// root of p
	}

	public boolean connected(int p, int q) {// are p and q connected?
		return root(p) == root(q);
	}

	public void union(int p, int q) {// connect p and q
//		int idp = pai[p];
//		int idq = pai[q];
//		
//		for (int i = 0; i < pai.length; i++) {
//			if (pai[i] == idp) {
//				pai[i] = idq;		
//			}
//		}
		int rp = root(p);
		int rq = root(q);
		pai[rp] = rq;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickUnionUF q = new QuickUnionUF(5);
		q.union(2, 3);
		q.union(3, 4);
		System.out.println(q.connected(2, 3));
		System.out.println(q.connected(2, 4));
		System.out.println(q.connected(1, 4));

		System.out.println(q.root(2));
	}
	
//	Doubling Hypothesis
//
//	N		T(N)		ratio		lg(ratio)
//	250		0,000		0,333		-1,585
//	500		0,000		1,000		0,000
//	1000		0,001		3,000		1,585
//	2000		0,000		0,333		-1,585
//	4000		0,002		8,000		3,000
//	8000		0,002		1,000		0,000
//	16000		0,012		7,500		2,907
//	32000		0,004		0,350		-1,515
//	64000		0,010		2,429		1,280
//	128000		0,013		1,235		0,305
//
//	a = 3.494734497452999E-4
//	b = 0.30485458152842093
//
//	T(N) = 3.494734497452999E-4 * N^0.30485458152842093
//
//	T(10^9) = 0.3908049017672652 seconds

}
