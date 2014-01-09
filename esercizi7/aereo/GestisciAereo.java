/* 
 * GestisciAereo.java
 * Classe di prova per le classi PostiAereo, Posto e Passeggero
 */
package aereo;

import java.util.Scanner;
import java.io.*;

public class GestisciAereo{
	
		public static void menu(PostiAereo pa){
			int scelta = 0;
			Scanner int_scan = new Scanner(System.in);
			while(true){
				System.out.println("***********************");
				System.out.println("*     Menu aereo      *");
				System.out.println("***********************");
				System.out.println("1)Stampa posti liberi");
				System.out.println("2)Stampa lista passeggeri");
				System.out.println("3)Assegna posto a un passeggero");
				System.out.println("4)Cancella prenotazione");
				System.out.println("5)Carica da file");
				System.out.println("6)Salva");
				System.out.println("0)Esci");
				try{
					scelta = int_scan.nextInt();
				}
				catch(Exception e){
					System.out.println("Scelta non valida!");
				}
				switch(scelta){
					case 1: pa.stampaPostiLiberi(); break;
					case 2:
						pa.stampaPasseggeri();
						break;
					case 3:
						Scanner str_scan = new Scanner(System.in); //Scanner per le stringhe
						String nome = null, cognome = null, cf = null;	//Dati del passeggero
						Passeggero p = null;
						int indicePasseggero = 0, numeroFila = 0, numeroPosto = 0; //indici per pa.assegnaPosto()
						boolean ok = true; //Serve per ripetere l'inserimento di dati errati
						try{	//Legge i dati del passeggero
							System.out.print("Nome: ");
							nome = str_scan.nextLine();
							System.out.print("Cognome: ");
							cognome = str_scan.nextLine();
							System.out.print("Codice fiscale: ");
							cf = str_scan.nextLine();
						}
						catch(Exception e){
							System.out.println("Dati inseriti non validi!");
						}
						/* Crea il nuovo passeggero e lo aggiunge all'elenco */
						try{
							p = new Passeggero(nome, cognome, cf);
						}
						catch(NotValidCodiceFiscaleException e){
							System.out.println(e.getMessage());
							break;
						}
						indicePasseggero = pa.aggiungiPasseggero(p);
						do{
							ok = true;
							System.out.println("Inserisci Fila: ");
							try{	//Legge i dati del posto da assegnare
								numeroFila = int_scan.nextInt();
								System.out.println("Inserisci numero posto: ");
								numeroPosto = int_scan.nextInt();
							}catch(Exception e){
								System.out.println("Dati inseriti non validi!");
							}
							try{
								pa.assegnaPosto(indicePasseggero, numeroFila - 1, numeroPosto - 1);
							}catch(ArrayIndexOutOfBoundsException e){
								System.out.println("Numero di fila o di posto non valido!");
								ok = false;
							}catch(AlreadyOccupiedException e){
								System.out.println("Il posto è già occupato!");
								ok = false;
							}
						}while(ok == false);
						break;
					case 4:		//Rimozione del posto di un passeggero
						int index = 0;
						System.out.print("Indice del passeggero: ");
						try{
							index = int_scan.nextInt();
						} catch(Exception e){
							System.out.println("Tipo di dato non valido!");
						}
						pa.rimuoviPosto(index);
						break;
					case 5:	//Carica aereo da file
						Scanner scan = new Scanner(System.in);
						System.out.print("File da caricare: ");
						ObjectInputStream in = null;
						try{
							in = new ObjectInputStream(new FileInputStream(scan.nextLine()));
						}catch(FileNotFoundException e){
							System.out.println("Il file non esiste!");
						}
						catch(IOException e){
							System.out.println("Si è verificato un errore in lettura!");
						}
						try{
							pa = (PostiAereo) in.readObject();
						}catch(Exception e){ //Se avvengono eccezioni in lettura vuol dire che il file non contiene un oggetto serializzato
							System.out.println("Contenuto del file non valido!");
						}
						break;
					case 6:	//Salva i dati in un file
						Scanner newscan = new Scanner(System.in);
						String filename;
						ObjectOutputStream out = null;
						System.out.print("Nome del file da scrivere: ");
						filename = newscan.nextLine();
						try{
							out = new ObjectOutputStream(new FileOutputStream(filename));
						}catch(Exception e){
							System.out.println("Errore nell'apertura del file!");
						}
						try{
							out.writeObject(pa);
						}catch(IOException e){
							System.out.println("Non è stato possibile scrivere su file!");
						}
						break;
					case 0:		//Uscita
						System.out.println("Arrivederci!");
						System.exit(0);
						break;
					default:
						System.out.println("Scelta non valida!");
				}
			}
		}

		public static void main(String args[]){
			PostiAereo pa = new PostiAereo(60, 5);
			while(true){
				menu(pa);
			}
		}	
}
