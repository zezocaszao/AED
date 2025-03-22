public class exponencial {
	public static int cena(int a,int b) {
		if(b==0)
			return 1;
		
		return a*cena(a, b - 1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(cena(2,4));
	}

}
