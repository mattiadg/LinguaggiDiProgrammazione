public class ContoBancario{
	private double saldo;
	private String titolare;
	private int numeroConto;
	static int progressivo = 1000000;
	
	/** Costruttore
	 * @param titolare
	 */
	public ContoBancario(String titolare){
		this(titolare, 0.0);
	}
	/** Costruttore
	 * @param titolare, saldo
	 */
	public ContoBancario(String titolare, double saldo){
		this.titolare = titolare;
		this.saldo = saldo;
		this.numeroConto = progressivo++;
	}

	public void versamento(double somma){
		if(somma > 0){
			saldo+= somma;
		}
		else{
			System.out.println("Devi versare una somma positiva!");
		}
	}

	public double prelievo(double somma){
		if(somma > saldo){
			System.out.println("Non c'è abbastanza liquidità!");
			return -1;
		}
		else{
			saldo -= somma;
			return somma;
		}
	}

	public double getSaldo(){
		return saldo;
	}

	public void trasferimento(ContoBancario other, double qnt){
		if(qnt < this.saldo){
			other.versamento(qnt);
			this.prelievo(qnt);
		}
		else{
			System.out.println("Saldo insufficiente!");
		}
	}

	public static void main(String args[]){
		ContoBancario c1 = new ContoBancario("Pippo", 500.0);
		ContoBancario c2 = new ContoBancario("Pluto", 1209.5);
		c1.prelievo(1000.0);
		c2.trasferimento(c1, 400.0);
		System.out.println("Trasferimento effettuato");
		System.out.printf("Saldo Pippo: %f\n", c1.getSaldo());
		System.out.printf("Saldo Pluto: %f\n", c2.getSaldo());
	}
}
