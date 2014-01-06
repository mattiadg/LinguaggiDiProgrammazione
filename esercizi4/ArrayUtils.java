public class ArrayUtils{
	
	/* Stampa un array di interi passato in input e poi va a capo */
	public static void stampaArray(int[] v){
		for(int i = 0; i < v.length; i++){
			System.out.printf("%d ", v[i]);
		}
		System.out.printf("\n");
	}

	/* Calcola la media di un array di interi */
	public static double mediaValori(int[] v){
		int somma = 0;
		double media;
		for(int i = 0; i < v.length; i++){
			somma += v[i];
		}
		media = somma * (1.0 / v.length);
		return media;
	}

	/* Restituisce un array con la sequenza di fibonacci di lunghezza n */
	public static int[] seqFibonacci(int n){
		if(n > 0){
			int[] fibo = new int[n];
			fibo[0] = 1;
			for(int i = 1; i < n; i++){
				if(i == 1){
					fibo[1] = 1;
				}
				else{
					fibo[i] = fibo[i-1] + fibo[i-2];
				}
			}
			return fibo;
		}
		else{
			System.out.println("N deve essere un numero positivo!");
			return null;
		}
	}

	/* Restituisce solo i valori pari compresi nell'array v */
	public static int[] findPari(int[] v){
		int[] tmp = new int[v.length];
		int j = 0;
		for(int i = 0; i < v.length; i++){
			if(v[i] % 2 == 0){
				tmp[j++] = v[i];
			}
		}
		int[] pari = new int[j];
		System.arraycopy(tmp, 0, pari, 0, j);
		return pari;
	}

	/* Restituisce un array ordinato contenuto i valori singoli di v.
	 * Implementato con il bucket sort, vale solo per array con valori 
	 * compresi tra 0 e 99.
	 */
	public static int[] eliminaDuplicati(int[] v){
		int[] bucket = new int[100];
		int cnt = 0;
		for(int val:v){
			bucket[val]++;
			if(bucket[val] == 1)
				cnt++;
		}
		int[] res = new int[cnt];
		int l = 0;
		for(int i = 0; i < 100; i++){
			if(bucket[i] > 0){
				res[l++] = i;
			}
		}
		return res;
	}

	/* Metodo per provare i metodi statici definiti sopra */
	public static void main(String args[]){
		int[] v = {4, 6, 2, 9, 11, 2, 4, 3, 8, 7, 12};
		System.out.println("Stampo v:...");
		stampaArray(v);
		System.out.printf("Media: %f\n", mediaValori(v));
		System.out.println("Fibonacci(0):");
		seqFibonacci(0);
		System.out.println("Fibonacci(2):");
		stampaArray(seqFibonacci(2));
	    System.out.println("Fibonacci(10):");
		stampaArray(seqFibonacci(10));
		System.out.println("Solo i valori pari:");
		stampaArray(findPari(v));
		System.out.println("Ordinato e senza duplicati");
		stampaArray(eliminaDuplicati(v));
	}
}
