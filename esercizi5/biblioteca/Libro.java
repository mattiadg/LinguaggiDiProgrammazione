import java.util.Scanner;
import java.util.Arrays;

public class Libro extends Documento{
	private int id;
	private Autore[] autori;
	private String titolo;
	private double prezzo;
	private String casaEditrice;
	private String locazione;
	static int progressive = 1000;

	/** Costruttore
	 * @param
	 */
	public Libro(){
		Scanner string_s = new Scanner(System.in);
		Scanner double_s = new Scanner(System.in);
		Scanner int_s = new Scanner(System.in);
		int n;
		this.id = progressive++;
		System.out.print("Titolo: ");
		this.titolo = string_s.nextLine();
		System.out.print("Numero autori: ");
		n = int_s.nextInt();
		this.autori = new Autore[n];
		System.out.println("Inserisci autori:");
		for(int i = 0; i < n; i++)
			this.autori[i] = new Autore();
		System.out.print("Prezzo: ");
		this.prezzo = double_s.nextDouble();
		System.out.print("Casa editrice: ");
		this.casaEditrice = string_s.nextLine();
		System.out.print("Locazione: ");
		this.locazione = string_s.nextLine();
	}

	public String getTitolo(){
		return titolo;
	}

	public String getAutore(){
		return Arrays.toString(autori);
	}

	public String toString(){
		String str = "Titolo: " + titolo;
		str = str + "\nAutori: " + stampaVettore(autori);
		str = str + "Prezzo: " + prezzo + "€\n";
		str = str + "Editore: " + casaEditrice;
		str = str + "\nId: " + id + "\n";
		return str;
	}

	/** Implementa getInfo */
	public String getInfo(){
		return "Tipologia: Libro\nLocazione: " + locazione + "\n" + this.toString();
	}

	public String getLocazione(){
		return this.locazione;
	}

	private String stampaVettore(Autore[] aut){
		String str = "";
		for(Autore a : aut){
			str = str + a.toString();
		}
		return str;
	}
}
