import java.util.Scanner;
import java.util.ArrayList;

public class Biblioteca{
	ArrayList<Documento> biblio;

	/** Costruttore
	 * @param
	 */
	public Biblioteca(){
		biblio = new ArrayList<Documento>();
	}

	/*Stampa il contenuto della biblioteca */
	public void stampaBiblioteca(){
		for(Documento doc : biblio){
			System.out.println(doc.getInfo() + "\n");
		}
	}

	/*Inserisce un documento nella biblioteca*/
	public void inserisci(){
		Scanner scan = new Scanner(System.in);
		int scelta;
		System.out.println("Che tipo di documento vuoi inserire?");
		System.out.println("1)Libro");
		System.out.println("2)Dizionario");
		System.out.println("3)Quotidiano");
		System.out.print("Inserisci scelta: ");
		scelta = scan.nextInt();
		switch(scelta){
			case 1: 
					System.out.print("Inserire 1 se il libro può essere prestato, un qualsiasi altro numero altrimenti: ");
					scelta = scan.nextInt();
					if(scelta == 1)
						biblio.add(new LibroPrestabile());
					else
						biblio.add(new Libro()); 
					break;
			case 2: biblio.add(new Dizionario()); break;
			case 3: biblio.add(new Quotidiano()); break;
			default: System.out.println("Scelta non valida!");
		}
	}

	/*Modifica la locazione di un dato documento */
	public void modificaLocazione(){
		Scanner int_scan = new Scanner(System.in);
		Scanner str_scan = new Scanner(System.in);
		int scelta;
		System.out.print("Indice del documento da spostare: ");
		scelta = int_scan.nextInt();
		if(scelta >= 0 && scelta < biblio.size()){
			System.out.print("Nuova locazione: ");
			biblio.get(scelta).setLocazione(str_scan.nextLine());
		}
		else{
			System.out.println("Indice non valido!");
		}
	}

	/*Elimina un documento*/
	public void elimina(){
		Scanner int_scan = new Scanner(System.in);
		Scanner str_scan = new Scanner(System.in);
		int scelta;
		System.out.print("Indice del documento da spostare: ");
		scelta = int_scan.nextInt();
		if(scelta >= 0 && scelta < biblio.size()){
				biblio.remove(scelta);
				System.out.println("Eliminazione avvenuta con successo!");
		}
		else{
			System.out.println("Indice non valido!");
		}
	}

	/*Ricerca per locazione */
	public Documento ricercaLocazione(String loc){
		Documento res = null;
		for(Documento doc : biblio){
			if(doc.getLocazione().equals(loc)){
				res = doc;
				break;
			}
		}
		return(res);
	}

	/* Ricerca una stringa all'interno delle info dei documenti */
	public ArrayList<Documento> ricercaStringa(String str){
		ArrayList<Documento> l = new ArrayList<Documento>();
		for(Documento doc : biblio){
			if(doc.getInfo().contains(str)){
				System.out.println(doc.getInfo());				
				l.add(doc);
			}
		}
		return(l);
	}

	public void menu(){
		int scelta;
		Scanner scan = new Scanner(System.in);
		Scanner sscan = new Scanner(System.in);
		System.out.println("**************************************************");
		System.out.println("***				Menu biblioteca				   ***");
		System.out.println("**************************************************");
		System.out.println("1)Stampa elenco");
		System.out.println("2)Inserisci nuovo documento");
		System.out.println("3)Modifica locazione di un documento");
		System.out.println("4)Cancella un documento");
		System.out.println("5)Ricerca una stringa");
		System.out.println("6)Ricerca per locazione");
		System.out.println("0)Uscita");
		System.out.print("Inserisci scelta: ");
		scelta = scan.nextInt();
		System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		switch(scelta){
			case 0: System.out.println("Ciao ciao!"); System.exit(0); break;
			case 1: stampaBiblioteca(); break;
			case 2: inserisci(); break;
			case 3: modificaLocazione(); break;
			case 4: elimina(); break;
			case 5: System.out.print("Inserisci chiave di ricerca: "); ricercaStringa(sscan.nextLine()); break; 
			case 6: System.out.print("Inserisci locazione da cercare: "); ricercaStringa(sscan.nextLine()); break;
			default: System.out.println("Scelta non valida!");
		}
	}

	public static void main(String[] args){
		Biblioteca b = new Biblioteca();
		while(true){
			b.menu();
		}
	}
}
