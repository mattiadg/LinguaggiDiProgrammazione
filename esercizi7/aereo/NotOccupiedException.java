/* Eccezione lanciata quando si prova a liberare un posto non occupato*/
package aereo;

public class NotOccupiedException extends RuntimeException{
	
	public NotOccupiedException(){
		super("Il posto è già libero!");
	}

	public NotOccupiedException(String msg){
		super(msg);
	}
}
