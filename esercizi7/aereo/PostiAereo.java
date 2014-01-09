/* 
 * PostiAereo.java
 * Memorizza le informazioni sullo stato dei posti su un aereo.
 * Tiene conto, inoltre degli utenti prenotati
 */

package aereo;
import java.io.Serializable;

public class PostiAereo implements Serializable{
	private Posto[][] posti;
	private Passeggero[] passeggeri;
	private int numeroPasseggeri;

	public PostiAereo(int numeroFile, int postiPerFila){
		//Le file devono essere almeno una e i posti per fila un numero compreso tra 1 e 6
		if(numeroFile < 1 || postiPerFila < 1 || postiPerFila > 6){
			throw new IllegalArgumentException();
		}
		else{
			char[] maiuscole = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
			//Crea la look-up table dei nomi dei posti
			char[] nomePosto = new char[postiPerFila];
			for(int i = 0; i < postiPerFila; i++){
				nomePosto[i] = maiuscole[i];
			}
			//Inizializza gli arrays
			posti = new Posto[numeroFile][];
			for(int i = 0; i < numeroFile; i++){
				posti[i] = new Posto[postiPerFila];
				for(int j = 0; j < postiPerFila; j++){
					posti[i][j] = new Posto(i + 1, nomePosto[j]); 
				}
			}
			passeggeri = new Passeggero[numeroFile * postiPerFila];
			numeroPasseggeri = 0;
		}
	}
	
	/* Stampa l'elenco dei posti liberi */
	public void stampaPostiLiberi(){
		System.out.println("Elenco posti liberi:");
		for(int i = 0; i < posti.length; i++)
			for(int j = 0; j < posti[i].length; j++)
				if(posti[i][j].isFree())
					System.out.println(posti[i][j].toString());
		}

	/* Stampa l'elenco dei passeggeri */
	public void stampaPasseggeri(){
		System.out.println("\n\n\n\n\nElenco passeggeri:\n");
		if(numeroPasseggeri == 0){
			System.out.println("\n\n\n\n\n\nNon ci sono passeggeri!\n\n\n\n\n\n\n");
		}
		else{
			for(int i = 0; i < numeroPasseggeri; i++){
				System.out.println(passeggeri[i].toString());
			}
			System.out.println("\n\n\n\n\n\n");
		}
	}

	/* Aggiunge un passeggero */
	public int aggiungiPasseggero(Passeggero p){
		passeggeri[numeroPasseggeri++] = p;
		return numeroPasseggeri - 1;
	}

	/* Assegna un posto a un passeggero */
	public void assegnaPosto(int indicePasseggero, int fila, int numPosto) throws ArrayIndexOutOfBoundsException, AlreadyOccupiedException{
		passeggeri[indicePasseggero].assegnaPosto(posti[fila][numPosto]);
	}

	/* Rimuove il posto assegnato a un passeggero */
	public void rimuoviPosto(int indicePasseggero){
		try{
			passeggeri[indicePasseggero].liberaPosto();
			passeggeri[indicePasseggero] = null;
			numeroPasseggeri--;
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Indice passeggero non valido!");
		}
		catch(NotOccupiedException e){
			System.out.println("Il posto è già libero!");
		}
	}
}
