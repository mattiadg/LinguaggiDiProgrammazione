/* Posto.java 
 * rappresenta un posto singolo su un aereo
 * ciascun posto deve poter dire se è libero o meno e cambiare il suo stato di libero o occupato
 */
package aereo;
import java.io.Serializable;

public class Posto implements Serializable{
	private boolean occupato;
	int fila;
	char pos;

	public Posto(int r, char p){
		this.occupato = false;
		this.fila = r;
		this.pos = p;
	}
	
	public boolean isFree(){
		return !occupato;
	}

	public void libera(){
		if(this.occupato)
			occupato = false;
		else
			throw  new NotOccupiedException();
	}

	public void occupa(){
		if(!this.occupato)
			occupato = true;
		else
			throw new AlreadyOccupiedException();
	}

	public String toString(){
		return fila + " " + pos;
	}
}
