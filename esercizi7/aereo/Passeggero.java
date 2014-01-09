/* Passeggero.java
 * rappresenta un passeggero che ha prenotato un posto sull'aereo
 * Contiene i dati anagrafici del passeggero, nonchè il posto assegnato 
 */
package aereo;
public class Passeggero{
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private Posto postoAssegnato;

	public Passeggero(String n, String c, String cf){
		this.nome = n;
		this.cognome = c;
		if(cf.length() == 16)
			this.codiceFiscale = cf;
		else 
			throw new NotValidCodiceFiscaleException();
	}

	public void assegnaPosto(Posto p){
		try{
			p.occupa();	//Può lanciare un AlreadyOccupiedException
		}
		catch(AlreadyOccupiedException e){
			System.out.println("Posto già occupato!");
			return;
		}
		this.postoAssegnato = p;
	}

	public void liberaPosto(){
		try{
			postoAssegnato.libera();	//Può lanciare una NotOccupiedException
		}
		catch(NotOccupiedException e){
			System.out.println("Il posto è già libero!");
			return;
		}
		this.postoAssegnato = null;
	}

	public String toString(){
		return nome + " " + cognome + " " + codiceFiscale + " " + postoAssegnato.toString();
	}
}
